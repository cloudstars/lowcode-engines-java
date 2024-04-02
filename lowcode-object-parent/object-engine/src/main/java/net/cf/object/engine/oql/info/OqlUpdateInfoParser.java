package net.cf.object.engine.oql.info;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOperator;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.util.OqlUtils;

import java.util.*;

/**
 * OQL更新语句指令构建器
 * <p>
 * 职责：用于将一条OQL更新语句解析成本表的插入与子表的插入、更新、删除
 */
public class OqlUpdateInfoParser extends AbstractOqlInfoParser {

    /**
     * 输入的插入语句
     */
    private final OqlUpdateStatement stmt;

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 本表的更新语句
     */
    private OqlUpdateInfo selfUpdateInfo;

    /**
     * 子表的插入信息
     */
    private List<OqlDetailInsertInfo> detailInsertInfos;

    /**
     * 子表的更新信息
     */
    private List<OqlDetailUpdateInfo> detailUpdateInfos;

    /**
     * 子表的删除信息
     */
    private List<OqlDetailDeleteInfo> detailDeleteInfos;

    public OqlUpdateInfoParser(OqlUpdateStatement stmt) {
        this(stmt, null);
    }

    public OqlUpdateInfoParser(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        super(false);
        this.stmt = stmt;
        this.paramMap = paramMap;
    }

    public OqlUpdateInfo getSelfUpdateInfo() {
        return selfUpdateInfo;
    }

    public List<OqlDetailInsertInfo> getDetailInsertInfos() {
        return detailInsertInfos;
    }

    public List<OqlDetailUpdateInfo> getDetailUpdateInfos() {
        return detailUpdateInfos;
    }

    public List<OqlDetailDeleteInfo> getDetailDeleteInfos() {
        return detailDeleteInfos;
    }

    /**
     * 解析OQL语句
     */
    public void parse() {
        XObject selfObject = stmt.getObjectSource().getResolvedObject();
        OqlUpdateInfo selfOqlInfo = new OqlUpdateInfo();
        selfOqlInfo.setObject(selfObject);
        selfOqlInfo.setParamMap(this.paramMap);


        List<OqlUpdateSetItem> setItems = this.stmt.getSetItems();
        if (!this.hasDetailSetItems(setItems)) {
            selfOqlInfo.setStatement(this.stmt);
        } else {
            OqlUpdateStatement selfStmt = new OqlUpdateStatement();
            selfStmt.setObjectSource(this.stmt.getObjectSource());

            this.detailInsertInfos = new ArrayList<>();
            this.detailUpdateInfos = new ArrayList<>();
            this.detailDeleteInfos = new ArrayList<>();
            for (OqlUpdateSetItem setItem : setItems) {
                SqlExpr field = setItem.getField();
                if (field instanceof OqlObjectExpandExpr) {
                    OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
                    // TODO 请在Checker中校验不支持相关表、子表展开必须显式展开
                    assert (objectExpandExpr.getResolvedObjectRefField().getRefType() == ObjectRefType.DETAIL);
                    assert (!objectExpandExpr.isStarExpanded() && !objectExpandExpr.isDefaultExpanded());
                    AbstractOqlInfo[] detailUpdateInfos = this.processDetail(objectExpandExpr, setItem);
                    this.detailInsertInfos.add((OqlDetailInsertInfo) detailUpdateInfos[0]);
                    this.detailUpdateInfos.add((OqlDetailUpdateInfo) detailUpdateInfos[1]);
                    this.detailDeleteInfos.add((OqlDetailDeleteInfo) detailUpdateInfos[2]);
                } else {
                    selfStmt.addSetItem(setItem);
                }
            }
            selfStmt.setWhere(this.stmt.getWhere());
            selfOqlInfo.setStatement(selfStmt);
        }

        this.selfUpdateInfo = selfOqlInfo;
    }

    /**
     * 表达式列表中是否包含子表
     *
     * @param setItems
     * @return
     */
    protected boolean hasDetailSetItems(List<OqlUpdateSetItem> setItems) {
        for (OqlUpdateSetItem setItem : setItems) {
            OqlExpr field = setItem.getField();
            if (field instanceof OqlObjectExpandExpr) {
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
                if (objectExpandExpr.getResolvedObjectRefField().getRefType() == ObjectRefType.DETAIL) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 生成子表的插入、更新、删除语句
     *
     * @param objectExpandFieldExpr
     * @param setItem
     */
    private AbstractOqlInfo[] processDetail(OqlObjectExpandExpr objectExpandFieldExpr, OqlUpdateSetItem setItem) {
        XObject detailObject = objectExpandFieldExpr.getResolvedRefObject();

        List<OqlStatement> detailStmts = this.buildDetailStatements(objectExpandFieldExpr);
        OqlInsertStatement detailInsertStmt = (OqlInsertStatement) detailStmts.get(0);
        OqlUpdateStatement detailUpdateStmt = (OqlUpdateStatement) detailStmts.get(1);
        OqlDeleteStatement detailDeleteStmt = (OqlDeleteStatement) detailStmts.get(2);

        List<?> values = this.parseDetailValuesList(detailObject, setItem);
        List<Map<String, Object>> detailInsertParamMaps = (List<Map<String, Object>>) values.get(0);
        List<Map<String, Object>> detailUpdateParamMaps = (List<Map<String, Object>>) values.get(1);
        List<String> detailInputRecordIds = (List<String>) values.get(2);

        OqlDetailInsertInfo detailInsertInfo = new OqlDetailInsertInfo();
        detailInsertInfo.setObject(detailObject);
        detailInsertInfo.setStatement(detailInsertStmt);
        detailInsertInfo.setParamMaps(detailInsertParamMaps);

        OqlDetailUpdateInfo detailUpdateInfo = new OqlDetailUpdateInfo();
        detailUpdateInfo.setObject(detailObject);
        detailUpdateInfo.setStatement(detailUpdateStmt);
        detailUpdateInfo.setParamMaps(detailUpdateParamMaps);

        OqlDetailDeleteInfo detailDeleteInfo = new OqlDetailDeleteInfo();
        detailDeleteInfo.setObject(detailObject);
        detailDeleteInfo.setStatement(detailDeleteStmt);
        detailDeleteInfo.setRemainedRecordIds(detailInputRecordIds);

        return new AbstractOqlInfo[] {detailInsertInfo, detailUpdateInfo, detailDeleteInfo};
    }

    /**
     * 构建子表的插入语句（对于没有记录ID的数据）
     *
     * @param detailObjectExpandFieldExpr
     * @return
     */
    private List<OqlStatement> buildDetailStatements(OqlObjectExpandExpr detailObjectExpandFieldExpr) {
        OqlInsertInto detailInsertInto = new OqlInsertInto();
        OqlUpdateStatement detailUpdateStmt = new OqlUpdateStatement();
        OqlDeleteStatement detailDeleteStmt = new OqlDeleteStatement();

        XObject detailObject = detailObjectExpandFieldExpr.getResolvedRefObject();
        String detailObjectName = detailObject.getName();
        XField primaryField = detailObject.getPrimaryField();
        String primaryFieldName = primaryField.getName();
        XField masterField = detailObject.getMasterField();
        String masterFieldName = masterField.getName();

        // 构建子表数据源
        OqlExprObjectSource detailObjectSource = new OqlExprObjectSource(detailObjectName);
        detailObjectSource.setResolvedObject(detailObject);
        detailInsertInto.setObjectSource(detailObjectSource);
        detailUpdateStmt.setObjectSource(detailObjectSource);
        detailDeleteStmt.setFrom(detailObjectSource);

        // 插入语句中的字段中添加子模型的全部字段，值中添加全部变量
        List<OqlExpr> detailFields = detailObjectExpandFieldExpr.getFields();
        SqlInsertStatement.ValuesClause valuesClause = new SqlInsertStatement.ValuesClause();
        detailInsertInto.addValues(valuesClause);

        // 添加主表记录ID字段（更新时新录入的数据需要补充主表记录ID列）
        OqlFieldExpr masterFieldExpr = new OqlFieldExpr(masterFieldName);
        masterFieldExpr.setResolvedField(masterField);
        detailInsertInto.addField(masterFieldExpr);
        SqlVariantRefExpr masterFieldValueExpr = new SqlVariantRefExpr("#{" + masterFieldName +  "}");
        valuesClause.addValue(masterFieldValueExpr);

        List<String> detailFieldNames = new ArrayList<>();
        for (OqlExpr detailField : detailFields) {
            // TODO 在Check中检查字段只能是OqlFieldExpr或者OqlFieldExpandExpr
            assert (detailField instanceof OqlFieldExpr || detailField instanceof OqlFieldExpandExpr);

            detailInsertInto.addField(detailField);
            String varName = null;
            if (detailField instanceof OqlFieldExpr) {
                varName = ((OqlFieldExpr) detailField).getName();
            } else if (detailField instanceof OqlFieldExpandExpr) {
                varName = ((OqlFieldExpandExpr) detailField).getOwner();
            }
            detailFieldNames.add(varName);
            SqlVariantRefExpr varRefExpr = new SqlVariantRefExpr("#{" + varName + "}");
            valuesClause.addValue(varRefExpr);
            detailUpdateStmt.addSetItem(new OqlUpdateSetItem(detailField, varRefExpr));
        }

        // 分析数据，修正插入与删除的语句
        String detailFieldName = detailObjectExpandFieldExpr.getResolvedObjectRefField().getName();
        Object detailParamMapValue = this.paramMap.get(detailFieldName);
        if (detailParamMapValue != null && detailParamMapValue instanceof List) {
            List<Map<String, Object>> detailParamMaps = (List<Map<String, Object>>) detailParamMapValue;
            Map<String, Object> firstDetailInsertRecord = null;
            Map<String, Object> firstDetailUpdateRecord = null;
            for (Map<String, Object> detailParamMap : detailParamMaps) {
                if (firstDetailInsertRecord == null && !detailParamMap.containsKey(primaryFieldName)) {
                    // 这是一条要插入的数据，取第一条数据中的数据，补充到插入字段中（这里假设其它新数据也具有同样的字段）
                    firstDetailInsertRecord = detailParamMap;
                } else if (firstDetailUpdateRecord == null && detailParamMap.containsKey(primaryFieldName)) {
                    // 这是一条待更新的数据
                    firstDetailUpdateRecord = detailParamMap;
                }
            }

            if (firstDetailInsertRecord != null) {
                // 如果第一条插入的数据中存在子模型中的别的字段的话，添加到插入的字段中
                for (Map.Entry<String, Object> entry : firstDetailInsertRecord.entrySet()) {
                    String insertFieldName = entry.getKey();
                    XField insertField = detailObject.getField(insertFieldName);
                    if (insertField != null && !detailFieldNames.contains(insertFieldName)) {
                        detailInsertInto.addField(OqlUtils.defaultFieldExpr(insertField));
                        SqlVariantRefExpr varRefExpr = new SqlVariantRefExpr("#{" + insertFieldName + "}");
                        detailInsertInto.getValuesList().get(0).addValue(varRefExpr);
                    }
                }
            }

            if (firstDetailUpdateRecord != null) {
                // 如果第一条更新的数据中存在子模型中的别的字段的话，添加到更新的字段中
                for (Map.Entry<String, Object> entry : firstDetailUpdateRecord.entrySet()) {
                    String updateFieldName = entry.getKey();
                    XField updateField = detailObject.getField(updateFieldName);
                    if (updateField != null && !detailFieldNames.contains(updateFieldName) && !updateFieldName.equals(primaryFieldName)) {
                        OqlUpdateSetItem updateSetItem = new OqlUpdateSetItem();
                        updateSetItem.setField(OqlUtils.defaultFieldExpr(updateField));
                        updateSetItem.setValue(new SqlVariantRefExpr("#{" + updateFieldName + "}"));
                        detailUpdateStmt.addSetItem(updateSetItem);
                    }
                }
            }
        }

        // 设置更新语句的过滤条件 where primaryFieldName = #{primaryFieldName}
        SqlBinaryOpExpr primaryIdEqualsCondition = this.buildFieldEqualityVariantRefExpr(primaryField);
        OqlFieldExpr primaryFieldExpr = (OqlFieldExpr) primaryIdEqualsCondition.getLeft();
        detailUpdateStmt.setWhere(primaryIdEqualsCondition);

        // 设置删除语句的过滤条件 where masterFieldName = #{masterFieldName} and primaryFieldName not in (#{remainedRecordIds}})
        SqlBinaryOpExpr masterIdEqualsCondition = this.buildFieldEqualityVariantRefExpr(masterField);
        SqlInListExpr primaryIdNotInCondition = new SqlInListExpr();
        primaryIdNotInCondition.setNot(true);
        primaryIdNotInCondition.setLeft(primaryFieldExpr);
        primaryIdNotInCondition.setTargetList(Arrays.asList(new SqlVariantRefExpr("#{remainedRecordIds}")));
        SqlBinaryOpExpr deleteWhere = new SqlBinaryOpExpr();
        deleteWhere.setLeft(masterIdEqualsCondition);
        deleteWhere.setOperator(SqlBinaryOperator.BOOLEAN_AND);
        deleteWhere.setRight(primaryIdNotInCondition);
        detailDeleteStmt.setWhere(deleteWhere);

        return Arrays.asList(new OqlInsertStatement(detailInsertInto), detailUpdateStmt, detailDeleteStmt);
    }


    /**
     * case1: update object set f1 = #{f1}, f2 = #{f2}, detail(d1, d2, d3(d31, d32))) =(#{f1}, #{f2}, #{detail})
     * case2: update object set f1 = #{f1}, f2 = #{f2}, detail(d1, d2, d3(d31, d32)) = [
     * {"d1": "...", "d2": "...", "d3": {"d31": "...", "d32": "..."}},
     * {"d1": "", "d2": "", "d3": {"d31": "...", "d32": "..."}}
     * ]
     *
     * @param detailObject
     * @param setItem
     * @return
     */
    private List<?> parseDetailValuesList(XObject detailObject, OqlUpdateSetItem setItem) {
        String detailPrimaryFieldName = detailObject.getPrimaryField().getName();

        // 添加插入的列
        List<Map<String, Object>> detailInsertParamMaps = new ArrayList<>();
        List<Map<String, Object>> detailUpdateParamMaps = new ArrayList<>();
        List<String> detailInputRecordIds = new ArrayList<>();

        SqlExpr updateValue = setItem.getValue();
        if (updateValue instanceof SqlVariantRefExpr) {
            SqlVariantRefExpr varRefExpr = (SqlVariantRefExpr) updateValue;
            String varName = varRefExpr.getVarName();
            Object paramValue = this.paramMap.get(varName);
            if (!(paramValue instanceof List)) {
                throw new FastOqlException("子表的参数类型必须是一个List<Map>");
            }

            List<Map<String, Object>> listParamValue = (List<Map<String, Object>>) paramValue;
            for (Map<String, Object> listParamItem : listParamValue) {
                Object itemRecordId = listParamItem.get(detailPrimaryFieldName);
                if (itemRecordId == null) {
                    detailInsertParamMaps.add(listParamItem);
                    // 判断这条数据中

                } else {
                    detailUpdateParamMaps.add(listParamItem);
                    detailInputRecordIds.add(itemRecordId.toString());
                }
            }
        } else if (updateValue instanceof SqlJsonArrayExpr) {
            // 将Array常量转为List<Map>
            SqlJsonArrayExpr listValueExpr = (SqlJsonArrayExpr) updateValue;
            for (SqlExpr itemValueExpr : listValueExpr.getItems()) {
                // TODO 语法检查应该在Checker里面做
                assert (itemValueExpr instanceof SqlJsonObjectExpr);
                Map<String, Object> itemValueMap = new HashMap<>();
                SqlJsonObjectExpr itemObjectValueExpr = (SqlJsonObjectExpr) itemValueExpr;
                Map<String, SqlExpr> itemObjectItems = itemObjectValueExpr.getItems();
                for (Map.Entry<String, SqlExpr> entry : itemObjectItems.entrySet()) {
                    SqlExpr vExpr = entry.getValue();
                    // TODO 语法检查应该在Checker里面做
                    assert (vExpr instanceof SqlValuableExpr);
                    String k = entry.getKey();
                    Object v = ((SqlValuableExpr) vExpr).getValue();
                    itemValueMap.put(k, v);
                }

                Object itemRecordId = itemValueMap.get(detailPrimaryFieldName);
                if (itemRecordId == null) {
                    detailInsertParamMaps.add(itemValueMap);
                } else {
                    detailUpdateParamMaps.add(itemValueMap);
                    detailInputRecordIds.add(itemRecordId.toString());
                }
            }
        }

        return Arrays.asList(detailInsertParamMaps, detailUpdateParamMaps, detailInputRecordIds);
    }

}
