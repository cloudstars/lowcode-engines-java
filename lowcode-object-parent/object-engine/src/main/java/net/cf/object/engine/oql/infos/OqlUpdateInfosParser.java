
package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.sql.*;
import net.cf.object.engine.util.OqlUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * OQL更新语句解析器
 * <p>
 * 职责：用于将一条OQL更新语句解析成本表的更新与子表的更新
 */
public class OqlUpdateInfosParser extends AbstractOqInfosParser<OqlUpdateStatement, OqlUpdateInfos> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;

    /**
     * 主模型关联的更新语句
     */
    private final OqlUpdateStatement masterOqlStmt = new OqlUpdateStatement();

    /**
     * 子模型关联的删除语句
     * <p>
     * delete from detailObject where masterPrimaryField = #{masterPrimaryField}
     */
    private final Map<XObject, OqlDeleteStatement> detailObjectDeleteStmtMap = new HashMap<>();

    /**
     * 子模型关联的删除语句
     * <p>
     * delete from detailObject where masterPrimaryField = #{masterPrimaryField} and primaryField not in (#{primaryFields})
     */
    private final Map<XObject, OqlDeleteStatement> detailObjectDeleteNotInStmtMap = new HashMap<>();

    /**
     * 子模型关联的单条删除参数表（当子模型有删除数据时）
     */
    private final Map<XObject, Map<String, Object>> detailObjectDeleteParamMapMap = new HashMap<>();

    /**
     * 子模型关联的批量删除参数表（当子模型有删除数据时）
     * <p>
     * delete from detailObject where masterPrimaryField = #{masterPrimaryField}
     */
    private final Map<XObject, List<Map<String, Object>>> detailObjectDeleteParamMapsMap = new HashMap<>();

    /**
     * 子模型关联的批量删除参数表（当子模型有删除数据时）
     * <p>
     * delete from detailObject where masterPrimaryField = #{masterPrimaryField} and primaryField not in (#{primaryFields})
     */
    private final Map<XObject, List<Map<String, Object>>> detailObjecDeletetNotInParamMapsMap = new HashMap<>();

    /**
     * 子模型关联的插入语句
     * <p>
     * 不含自增主键时：insert into detailObject(primaryFieldd1, d2, ..., masterField) values (#{primaryField}, #{d1}, #{d2}, ..., #{masterField})
     * 含自增主键时：insert into detailObject(d1, d2, ..., masterField) values (#{d1}, #{d2}, ..., #{masterField})
     */
    private final Map<XObject, OqlInsertStatement> detailObjectInsertStmtMap = new HashMap<>();

    /**
     * 子模型关联的批量插入参数表（当子模型有插入新的的数据时）
     */
    private final Map<XObject, List<Map<String, Object>>> detailObjectInsertParamMapsMap = new HashMap<>();

    /**
     * 子模型关联的更新语句
     * <p>
     * update detailObject set d1 = #{d1}, d2 = #{d2}, ... where primaryField = #{primaryField}
     */
    private final Map<XObject, OqlUpdateStatement> detailObjectUpdateStmtMap = new HashMap<>();

    /**
     * 子模型关联的批量更新参数表（当子模型有更新旧的数据时）
     */
    private final Map<XObject, List<Map<String, Object>>> detailObjectUpdateParamMapsMap = new HashMap<>();

    public OqlUpdateInfosParser(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        super(stmt, false);
        this.paramMap = paramMap;
        this.paramMaps = null;
    }

    public OqlUpdateInfosParser(OqlUpdateStatement stmt, List<Map<String, Object>> paramMaps) {
        super(stmt, true);
        this.paramMap = null;
        this.paramMaps = paramMaps;
    }

    /**
     * 解析成OQL更新语句指令信息
     *
     * @return
     */
    public OqlUpdateInfos parse() {
        // 解析当前语句的本模型
        this.masterObject = this.stmt.getObjectSource().getResolvedObject();
        this.masterOqlStmt.setObjectSource(OqlUtils.buildObjectSource(this.masterObject));
        this.masterPrimaryFieldValueExpr = this.extractMasterIdInWhere(this.masterObject, this.stmt.getWhere());

        // 获取本模型对应的SQL语句
        List<OqlUpdateSetItem> setItems = this.stmt.getSetItems();
        int setItemSize = setItems.size();
        List<OqlUpdateSetItem> masterSetItems = this.masterOqlStmt.getSetItems();
        for (int i = 0; i < setItemSize; i++) {
            OqlUpdateSetItem setItem = setItems.get(i);
            OqlExpr field = setItem.getField();
            SqlExpr fieldValue = setItem.getValue();
            if (field instanceof OqlObjectExpandExpr) {
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
                XObjectRefField objectRefField = objectExpandExpr.getResolvedObjectRefField();
                assert (objectRefField.getRefType() == ObjectRefType.DETAIL);
                assert (!objectExpandExpr.isStarExpanded());

                this.processDetail(objectExpandExpr, fieldValue);
            } else if (field instanceof OqlFieldExpr) {
                masterSetItems.add(setItem);
            }
        }

        SqlExpr where = this.stmt.getWhere();
        if (where != null) {
            this.masterOqlStmt.setWhere(where);
        }

        return this.buildUpdateInfos();
    }

    /**
     * 构建更新信息
     *
     * @return
     */
    private OqlUpdateInfos buildUpdateInfos() {
        OqlUpdateInfos updateInfos = new OqlUpdateInfos();
        updateInfos.setResolvedMasterObject(this.masterObject);

        SqlUpdateCmdBuilder builder;
        if (!this.isBatch) {
            builder = new SqlUpdateCmdBuilder(this.masterOqlStmt, this.paramMap);
        } else {
            builder = new SqlUpdateCmdBuilder(this.masterOqlStmt, this.paramMaps);
        }
        SqlUpdateCmd thisUpdateCmd = builder.build();
        updateInfos.setMasterUpdateCmd(thisUpdateCmd);

        Arrays.asList(this.detailObjectDeleteStmtMap, this.detailObjectDeleteNotInStmtMap).forEach((detailObjectDeleteStmtMap) -> {
            if (!detailObjectDeleteStmtMap.isEmpty()) {
                detailObjectDeleteStmtMap.forEach((detailObject, stmt) -> {
                    updateInfos.addResolvedDetailObject(detailObject);

                    SqlDeleteCmdBuilder detailBuilder;
                    if (!this.isBatch) {
                        Map<String, Object> paramMap = this.getDetailObjectDeleteParamMapByObject(detailObject);
                        detailBuilder = new SqlDeleteCmdBuilder(stmt, paramMap);
                        SqlDeleteCmd detailCmd = detailBuilder.build();
                        updateInfos.addDetailDeleteCmd(detailObject, detailCmd);
                    } else {
                        List<Map<String, Object>> paramMaps1 = this.getDetailObjectDeleteParamMapsByObject(detailObject);
                        if (!CollectionUtils.isEmpty(paramMaps1)) {
                            detailBuilder = new SqlDeleteCmdBuilder(stmt, paramMaps1);
                            SqlDeleteCmd detailCmd = detailBuilder.build();
                            updateInfos.addDetailDeleteCmd(detailObject, detailCmd);
                        }

                        List<Map<String, Object>> paramMaps2 = this.getDetailObjectDeleteNotInParamMapsByObject(detailObject);
                        if (!CollectionUtils.isEmpty(paramMaps2)) {
                            detailBuilder = new SqlDeleteCmdBuilder(stmt, paramMaps2);
                            SqlDeleteCmd detailCmd = detailBuilder.build();
                            updateInfos.addDetailNotInDeleteCmd(detailObject, detailCmd);
                        }
                    }
                });
            }
        });

        if (!this.detailObjectInsertStmtMap.isEmpty()) {
            this.detailObjectInsertStmtMap.forEach((detailObject, stmt) -> {
                updateInfos.addResolvedDetailObject(detailObject);

                // 子表插入语句一定是批量执行的
                List<Map<String, Object>> paramMaps = this.getDetailObjectInsertParamMapsByObject(detailObject);
                SqlInsertCmdBuilder detailBuilder = new SqlInsertCmdBuilder(stmt, paramMaps);
                SqlInsertCmd detailCmd = detailBuilder.build();
                updateInfos.addDetailInsertCmd(detailObject, detailCmd);
            });
        }

        if (!this.detailObjectUpdateStmtMap.isEmpty()) {
            this.detailObjectUpdateStmtMap.forEach((detailObject, stmt) -> {
                updateInfos.addResolvedDetailObject(detailObject);

                // 子表更新语句一定是批量执行的
                List<Map<String, Object>> paramMaps = this.getDetailObjectUpdateParamMapsByObject(detailObject);
                SqlUpdateCmdBuilder detailBuilder = new SqlUpdateCmdBuilder(stmt, paramMaps);
                SqlUpdateCmd detailCmd = detailBuilder.build();
                updateInfos.addDetailUpdateCmd(detailObject, detailCmd);
            });
        }

        return updateInfos;
    }

    /**
     * 处理子表, detail(f1, f2, f3) = [{}, {}, ...] 或 detail(f1, f2, f3) = #{detail'} 或 detail(f1, f2, f2) = #{detail'(f1', f2', f3')}
     *
     * @param detailRefFieldExpr
     * @param detailRefFieldValue
     */
    private void processDetail(OqlObjectExpandExpr detailRefFieldExpr, SqlExpr detailRefFieldValue) {
        // 更新语句中的字段中添加子模型的全部字段，值中添加全部变量
        XObject detailObject = detailRefFieldExpr.getResolvedRefObject();
        String masterObjectName = this.masterObject.getName();
        List<SqlExpr> detailExprs = detailRefFieldExpr.getFields();
        int detailFieldSize = detailExprs.size();
        if (detailFieldSize == 0) {
            throw new FastOqlException("子表字段未指定");
        }

        // 检查子表的字段中必须包括primaryField、masterField
        boolean hasPrimaryField = false;
        boolean hasMasterField = false;
        for (SqlExpr detailExpr : detailExprs) {
            assert (detailExpr instanceof OqlFieldExpr);
            if (detailExpr instanceof OqlFieldExpr) {
                OqlFieldExpr detailFieldExpr = (OqlFieldExpr) detailExpr;
                XField field = detailFieldExpr.getResolvedField();
                if (!hasMasterField && detailObject.getPrimaryField() == field) {
                    hasPrimaryField = true;
                }
                if (!hasMasterField && field instanceof XObjectRefField && ((XObjectRefField) field).getRefObjectName().equals(masterObjectName)) {
                    hasMasterField = true;
                }
            }
        }
        if (!hasPrimaryField) {
            throw new FastOqlException("子表的更新字段中必须包含子表的记录ID字段");
        }
        if (!hasMasterField) {
            throw new FastOqlException("子表的更新字段中必须包含主表记录ID关联字段");
        }

        if (detailRefFieldValue instanceof SqlVariantRefExpr) { // detail(f1, f2, f3) = #{detail'}
            // TODO // detail(f1, f2, f3) = #{detail'(f1', f2', f3')}不支持
            SqlVariantRefExpr varRefExpr = (SqlVariantRefExpr) detailRefFieldValue;
            String varName = varRefExpr.getVarName();
            Map<String, String> paramNamesMap = null;
            List<String> subVarNames = varRefExpr.getSubVarNames();
            if (!CollectionUtils.isEmpty(subVarNames)) {
                if (subVarNames.size() != detailFieldSize) {
                    throw new FastOqlException("子表字段的字段数据与更新的字段数量不一致");
                }

                paramNamesMap = new HashMap<>();
                for (int i = 0; i < detailFieldSize; i++) {
                    String detailFieldName = ((OqlFieldExpr) detailExprs).getName();
                    paramNamesMap.put(detailFieldName, varName);
                }
            }

            if (!isBatch) {
                List<Map<String, Object>> detailParamMaps = (List<Map<String, Object>>) this.paramMap.get(varName);
                if (paramNamesMap != null) {
                    detailParamMaps = this.convertDetailParamMapsWithKeys(detailParamMaps, paramNamesMap);
                }
                this.buildDetailCudParamMaps(detailObject, this.paramMap, detailParamMaps);
            } else {
                for (Map<String, Object> paramMap : this.paramMaps) {
                    List<Map<String, Object>> detailParamMaps = (List<Map<String, Object>>) paramMap.get(varName);
                    if (paramNamesMap != null) {
                        detailParamMaps = this.convertDetailParamMapsWithKeys(detailParamMaps, paramNamesMap);
                    }
                    this.buildDetailCudParamMaps(detailObject, paramMap, detailParamMaps);
                }
            }
        } else if (detailRefFieldValue instanceof SqlJsonArrayExpr){ // detail(f1, f2, f3) = [{}, {}, ...]
            List<Object> values = ((SqlJsonArrayExpr) detailRefFieldValue).getValue();
            List<Map<String, Object>> detailParamMaps = new ArrayList<>();
            for (Object value : values) {
                detailParamMaps.add((Map<String, Object>) value);
            }
            if (!isBatch) {
                this.buildDetailCudParamMaps(detailObject, this.paramMap, detailParamMaps);
            } else {
                throw new FastOqlException("子表不支持用JsonArray批量更新");
            }
        }

        this.buildDetailParamStmts(detailObject, detailRefFieldExpr);
    }

    /**
     * 转换子表的参数值，将变量名KEY转换为字段名KEY
     *
     * @param paramMaps
     * @param namesMap 字段名KEY -> 变量名KEY
     */
    private List<Map<String, Object>> convertDetailParamMapsWithKeys(List<Map<String, Object>> paramMaps, Map<String, String> namesMap) {
        List<Map<String, Object>> targetParamMaps = new ArrayList<>();
        for (Map<String, Object> paramMap : paramMaps) {
            Map<String, Object> targetParamMap = new HashMap<>();
            for (Map.Entry<String, String> namesEntry : namesMap.entrySet()) {
                targetParamMap.put(namesEntry.getKey(), paramMap.get(namesEntry.getValue()));
            }
            targetParamMaps.add(targetParamMap);
        }

        return targetParamMaps;
    }

    /**
     * 构建子表的插入、更新、删除的参数
     *
     * @param detailObject
     * @param masterParamMap
     * @param detailParamMaps
     */
    private void buildDetailCudParamMaps(XObject detailObject, Map<String, Object> masterParamMap, List<Map<String, Object>> detailParamMaps) {
        String masterObjectName = this.masterObject.getName();
        Object masterRecordId = this.extractMasterId(masterParamMap);
        XField masterRefField = detailObject.getObjectRefField(masterObjectName);
        String masterRefFieldName = masterRefField.getName();
        XField primaryField = detailObject.getPrimaryField();
        String primaryFieldName = primaryField.getName();
        List<Object> detailRecordIds = new ArrayList<>();
        for (Map<String, Object> detailParamMap : detailParamMaps) {
            // 判断如果有masterFieldId，则为存量数据，否则为新数据
            Object detailMasterRecordId = detailParamMap.get(masterRefFieldName);
            if (detailMasterRecordId != null) { // 存量数据需要更新
                if (!detailMasterRecordId.equals((masterRecordId))) {
                    throw new FastOqlException("子表中的主表记录ID的值与主表的记不匹配");
                }

                detailRecordIds.add(detailParamMap.get(primaryFieldName));
                List<Map<String, Object>> detailUpdateMaps = this.getDetailObjectUpdateParamMapsByObject(detailObject);
                detailUpdateMaps.add(detailParamMap);
            } else { // 新增数据
                detailParamMap.put(masterRefFieldName, masterRecordId);
                List<Map<String, Object>> detailInsertMaps = this.getDetailObjectInsertParamMapsByObject(detailObject);
                detailInsertMaps.add(detailParamMap);
            }
        }

        Map<String, Object> detailDeleteMap = new HashMap<>();
        detailDeleteMap.put(masterRefFieldName, masterRecordId);
        if (CollectionUtils.isEmpty(detailRecordIds)) {
            if (!isBatch) {
                this.detailObjectDeleteParamMapMap.put(detailObject, detailDeleteMap);
            } else {
                List<Map<String, Object>> detailDeleteMaps = this.getDetailObjectDeleteParamMapsByObject(detailObject);
                detailDeleteMaps.add(detailDeleteMap);
            }
        } else {
            detailDeleteMap.put(primaryFieldName + 's', detailRecordIds);
            if (!isBatch) {
                this.detailObjectDeleteParamMapMap.put(detailObject, detailDeleteMap);
            } else {
                List<Map<String, Object>> detailDeleteNotInMaps = this.getDetailObjectDeleteNotInParamMapsByObject(detailObject);
                detailDeleteNotInMaps.add(detailDeleteMap);
            }
        }
    }

    /**
     * 构建子表的新增、更新、删除 OQL 语句
     */
    private void buildDetailParamStmts(XObject detailObject, OqlObjectExpandExpr detailRefFieldExpr) {
        // 先构建删除子表的语句
        this.buildDetailTwoDeleteStmt(detailObject);

        // 再构建插入子表的语句
        List<Map<String, Object>> detailInsertParamMaps = this.detailObjectInsertParamMapsMap.get(detailObject);
        if (!CollectionUtils.isEmpty(detailInsertParamMaps)) {
            this.buildDetailInsertStmt(detailObject, detailRefFieldExpr);
        }

        // 再构建更新子表的语句
        List<Map<String, Object>> detailUpdateParamMaps = this.detailObjectUpdateParamMapsMap.get(detailObject);
        if (!CollectionUtils.isEmpty(detailUpdateParamMaps)) {
            this.buildDetailUpdateStmt(detailObject, detailRefFieldExpr);
        }
    }

    /**
     * 构建子表的更新语句
     *
     * @param detailObject
     */
    private void buildDetailTwoDeleteStmt(XObject detailObject) {
        XField primaryField = detailObject.getPrimaryField();
        if (!isBatch) {
            // 构建单条删除语句
            Map<String, Object> detailDeleteParamMap = this.detailObjectDeleteParamMapMap.get(detailObject);
            List<Object> detailRecordIds = (List<Object>) detailDeleteParamMap.get(primaryField.getName() + "s");
            if (!CollectionUtils.isEmpty(detailRecordIds)) { // 数据库中的数据部分删除, where masterRefField = #{masterRefField} and primaryField in (#{primaryFields})
                this.buildDetailDeleteNotInStmt(detailObject);
            } else { // 数据库中的数据全删, where masterField = #{masterField}
                this.buildDetailDeleteStmt(detailObject);
            }
        } else { // 构建两个批量删除语句，一个带not in #{primaryFields}，一个不带
            List<Map<String, Object>> detailObjectDeleteParamMaps = this.getDetailObjectDeleteParamMapsByObject(detailObject);
            if (!CollectionUtils.isEmpty(detailObjectDeleteParamMaps)) {
                this.buildDetailDeleteStmt(detailObject);
            }

            List<Map<String, Object>> detailObjectDeleteNotInParamMaps = this.getDetailObjectDeleteNotInParamMapsByObject(detailObject);
            if (!CollectionUtils.isEmpty(detailObjectDeleteNotInParamMaps)) {
                this.buildDetailDeleteNotInStmt(detailObject);
            }
        }
    }


    /**
     * 构建子表的删除语句，数据库中的指定主表记录ID的数据全删, where masterField = #{masterField}
     *
     * @param detailObject
     */
    private void buildDetailDeleteStmt(XObject detailObject) {
        XField masterRefField = detailObject.getObjectRefField(this.masterObject.getName());
        OqlDeleteStatement detailStmt = this.getDetailObjectDeleteStmtByObject(detailObject);
        detailStmt.setWhere(OqlUtils.buildFieldEqualsVarRefExpr(masterRefField));
    }

    /**
     * 构建子表的删除（含not in）语句，数据库中的指定主表记录ID的数据除需要保留的记录之外全删, where masterField = #{masterField} and primaryField not in #{primaryFieldIds}
     *
     * @param detailObject
     */
    private void buildDetailDeleteNotInStmt(XObject detailObject) {
        XField primaryField = detailObject.getPrimaryField();
        XField masterRefField = detailObject.getObjectRefField(this.masterObject.getName());
        SqlExpr left = OqlUtils.buildFieldEqualsVarRefExpr(masterRefField);
        SqlExpr right = OqlUtils.buildFieldInListVarRefExpr(primaryField, true);
        OqlDeleteStatement detailStmt = this.getDetailObjectDeleteNotInStmtByObject(detailObject);
        detailStmt.setWhere(new SqlBinaryOpExpr(left, SqlBinaryOperator.BOOLEAN_AND, right));
    }

    /**
     * 构建子表的更新语句
     *
     * @param detailObject
     * @param detailRefFieldExpr
     */
    private void buildDetailUpdateStmt(XObject detailObject, OqlObjectExpandExpr detailRefFieldExpr) {
        OqlUpdateStatement detailStmt = this.getDetailObjectUpdateStmtByObject(detailObject);
        XField masterRefField = detailObject.getObjectRefField(this.masterObject.getName());
        XField primaryField = detailObject.getPrimaryField();
        List<SqlExpr> detailExprs = detailRefFieldExpr.getFields();
        int detailFieldSize = detailExprs.size();
        for (int i = 0; i < detailFieldSize; i++) {
            SqlExpr detailExpr = detailExprs.get(i);
            assert (detailExpr instanceof OqlFieldExpr);
            OqlFieldExpr detailFieldExpr = (OqlFieldExpr) detailExpr;
            XField detailResolvedField = detailFieldExpr.getResolvedField();
            // 更新语句的更新字段中，不需要子表的主键和主表引用字段
            if (detailResolvedField == primaryField || detailResolvedField == masterRefField) {
                continue;
            }

            OqlUpdateSetItem setItem = new OqlUpdateSetItem();
            setItem.setField(detailFieldExpr);
            setItem.setValue(OqlUtils.buildFieldVarExpr(detailResolvedField));
            detailStmt.addSetItem(setItem);
        }
        detailStmt.setWhere(OqlUtils.buildFieldEqualsVarRefExpr(primaryField));
    }

    /**
     * 构建子表的更新语句
     *
     * @param detailObject
     * @param detailRefFieldExpr
     */
    private void buildDetailInsertStmt(XObject detailObject, OqlObjectExpandExpr detailRefFieldExpr) {
        OqlInsertStatement detailStmt = this.getDetailObjectInsertStmtByObject(detailObject);
        XField primaryField = detailObject.getPrimaryField();
        List<SqlExpr> detailExprs = detailRefFieldExpr.getFields();
        int detailFieldSize = detailExprs.size();
        SqlInsertStatement.ValuesClause valuesClause = new SqlInsertStatement.ValuesClause();
        for (int i = 0; i < detailFieldSize; i++) {
            SqlExpr detailExpr = detailExprs.get(i);
            assert (detailExpr instanceof OqlFieldExpr);
            OqlFieldExpr detailFieldExpr = (OqlFieldExpr) detailExpr;
            XField detailResolvedField = detailFieldExpr.getResolvedField();
            if (detailResolvedField == primaryField && primaryField.isAutoGen()) {
                continue;
            }

            detailStmt.addField(detailFieldExpr);
            valuesClause.addValue(OqlUtils.buildFieldVarExpr(detailResolvedField));
        }
        detailStmt.addValues(valuesClause);
    }

    /**
     * 根据子模型获取它对应的单条删除语句
     *
     * @param detailObject
     * @return
     */
    private OqlDeleteStatement getDetailObjectDeleteStmtByObject(XObject detailObject) {
        OqlDeleteStatement stmt = this.detailObjectDeleteStmtMap.get(detailObject);
        if (stmt == null) {
            stmt = new OqlDeleteStatement();
            stmt.setFrom(OqlUtils.buildObjectSource(detailObject));
            this.detailObjectDeleteStmtMap.put(detailObject, stmt);
        }

        return stmt;
    }


    /**
     * 根据子模型获取它对应的单条删除（含 not in）语句
     *
     * @param detailObject
     * @return
     */
    private OqlDeleteStatement getDetailObjectDeleteNotInStmtByObject(XObject detailObject) {
        OqlDeleteStatement stmt = this.detailObjectDeleteNotInStmtMap.get(detailObject);
        if (stmt == null) {
            stmt = new OqlDeleteStatement();
            stmt.setFrom(OqlUtils.buildObjectSource(detailObject));
            this.detailObjectDeleteNotInStmtMap.put(detailObject, stmt);
        }

        return stmt;
    }

    /**
     * 根据子模型获取它对应的单条删除参数表
     *
     * @param detailObject
     * @return
     */
    private Map<String, Object> getDetailObjectDeleteParamMapByObject(XObject detailObject) {
        Map<String, Object> paramMap = this.detailObjectDeleteParamMapMap.get(detailObject);
        if (paramMap == null) {
            paramMap = new HashMap<>();
            this.detailObjectDeleteParamMapMap.put(detailObject, paramMap);
        }
        return paramMap;
    }

    /**
     * 根据子模型获取它对应的批量删除操作参数表
     *
     * @param detailObject
     * @return
     */
    private List<Map<String, Object>> getDetailObjectDeleteParamMapsByObject(XObject detailObject) {
        List<Map<String, Object>> paramMapList = this.detailObjectDeleteParamMapsMap.get(detailObject);
        if (paramMapList == null) {
            paramMapList = new ArrayList<>();
            this.detailObjectDeleteParamMapsMap.put(detailObject, paramMapList);
        }
        return paramMapList;
    }

    /**
     * 根据子模型获取它对应的批量删除（含not in）操作参数表
     *
     * @param detailObject
     * @return
     */
    private List<Map<String, Object>> getDetailObjectDeleteNotInParamMapsByObject(XObject detailObject) {
        List<Map<String, Object>> paramMapList = this.detailObjecDeletetNotInParamMapsMap.get(detailObject);
        if (paramMapList == null) {
            paramMapList = new ArrayList<>();
            this.detailObjecDeletetNotInParamMapsMap.put(detailObject, paramMapList);
        }
        return paramMapList;
    }


    /**
     * 根据子模型获取它对应的插入语句
     *
     * @param detailObject
     * @return
     */
    private OqlInsertStatement getDetailObjectInsertStmtByObject(XObject detailObject) {
        OqlInsertStatement stmt = this.detailObjectInsertStmtMap.get(detailObject);
        if (stmt == null) {
            OqlInsertInto insertInto = new OqlInsertInto();
            insertInto.setObjectSource(OqlUtils.buildObjectSource(detailObject));
            stmt = new OqlInsertStatement(insertInto);
            this.detailObjectInsertStmtMap.put(detailObject, stmt);
        }

        return stmt;
    }

    /**
     * 根据子模型获取它对应的批量插入操作参数表
     *
     * @param detailObject
     * @return
     */
    private List<Map<String, Object>> getDetailObjectInsertParamMapsByObject(XObject detailObject) {
        List<Map<String, Object>> paramMapList = this.detailObjectInsertParamMapsMap.get(detailObject);
        if (paramMapList == null) {
            paramMapList = new ArrayList<>();
            this.detailObjectInsertParamMapsMap.put(detailObject, paramMapList);
        }
        return paramMapList;
    }

    /**
     * 根据子模型获取它对应的更新语句
     *
     * @param detailObject
     * @return
     */
    private OqlUpdateStatement getDetailObjectUpdateStmtByObject(XObject detailObject) {
        OqlUpdateStatement stmt = this.detailObjectUpdateStmtMap.get(detailObject);
        if (stmt == null) {
            stmt = new OqlUpdateStatement();
            stmt.setObjectSource(OqlUtils.buildObjectSource(detailObject));
            this.detailObjectUpdateStmtMap.put(detailObject, stmt);
        }

        return stmt;
    }

    /**
     * 根据子模型获取它对应的批量更新操作参数表
     *
     * @param detailObject
     * @return
     */
    private List<Map<String, Object>> getDetailObjectUpdateParamMapsByObject(XObject detailObject) {
        List<Map<String, Object>> paramMapList = this.detailObjectUpdateParamMapsMap.get(detailObject);
        if (paramMapList == null) {
            paramMapList = new ArrayList<>();
            this.detailObjectUpdateParamMapsMap.put(detailObject, paramMapList);
        }
        return paramMapList;
    }

}
