
package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlListExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.sql.SqlInsertCmd;
import net.cf.object.engine.sql.SqlInsertCmdBuilder;
import net.cf.object.engine.util.OqlUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * OQL插入语句解析器
 * <p>
 * 职责：用于将一条OQL插入语句解析成主表的插入与子表的插入
 * <p>
 * 单个values的OQL语句示例：如：
 * insert into f1, f2, f3, f4, f5, detail(d1, d2, d3, ...), ... into object values (f1, f2, [多选不带属性], {单选带属性}, [多选带属性], [{d1, d2, d3, ...}, {d1, d2, d3, ...}, ...], ...)
 * insert into f1, f2, f3, f4, f5, detail(d1, d2, d3, ...), ... into object values (#{f1}, #{f2}, #{f3}, {单选带属性}, [多选带属性], #{detail'(d1', d2', d3', ...), ...}, ...)
 * 多个values的OQL语句允许有多个values：如：insert into f1, f2, f3, f4, f5, ... into object values 上述两个case的组合
 */
public class OqlInsertInfosParser extends AbstractOqInfosParser<OqlInsertStatement, OqlInsertInfos> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;

    /**
     * 模型关联的插入语句（含主表+子表）
     */
    private final Map<XObject, OqlInsertStatement> objectStmtMap = new HashMap<>();

    /**
     * 模型子关联的批量参数表
     */
    private final Map<XObject, List<Map<String, Object>>> detailObjectParamMapsMap = new HashMap<>();

    /**
     * 从OQL语句中解析出来的主表主键字段在插入字段中的序号
     */
    private int masterPrimaryFieldIndex = -1;

    public OqlInsertInfosParser(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        super(stmt, false);
        this.paramMap = paramMap;
        this.paramMaps = null;
    }

    public OqlInsertInfosParser(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        super(stmt, true);
        this.paramMap = null;
        this.paramMaps = paramMaps;
    }

    /**
     * 解析成OQL插入语句指令信息
     *
     * @return
     */
    public OqlInsertInfos parse() {
        // 解析当前语句的本模型
        OqlInsertInto insertInto = this.stmt;
        this.masterObject = insertInto.getObjectSource().getResolvedObject();
        this.masterPrimaryFieldIndex = this.getMasterPrimaryFieldIndex();

        // 处理 SQL 插入语句的列
        List<OqlExpr> fields = this.stmt.getFields();
        int fieldSize = fields.size();
        for (int fieldIndex = 0; fieldIndex < fieldSize; fieldIndex++) {
            OqlExpr field = fields.get(fieldIndex);
            this.processField(field);
        }

        // 处理 SQL 插入语句的值
        List<SqlInsertStatement.ValuesClause> valuesList = this.stmt.getValuesList();
        int valuesSize = valuesList.size();
        List<SqlInsertStatement.ValuesClause> masterValuesList = this.getObjectStmtByObject(this.masterObject).getValuesList();
        for (int valueIndex = 0; valueIndex < valuesSize; valueIndex++) {
            SqlInsertStatement.ValuesClause masterValues = new SqlInsertStatement.ValuesClause();
            for (int fieldIndex = 0; fieldIndex < fieldSize; fieldIndex++) {
                OqlExpr field = fields.get(fieldIndex);
                SqlExpr fieldValue = valuesList.get(valueIndex).getValue(fieldIndex);
                if (field instanceof OqlFieldExpr) { // 处理本表的值
                    masterValues.addValue(fieldValue);
                } else { // 处理子表的值
                    OqlObjectExpandExpr detailRefExpr = (OqlObjectExpandExpr) field;
                    this.processDetailValue(detailRefExpr, valueIndex, fieldValue);
                }
            }
            masterValuesList.add(masterValues);
        }

        return this.buildInsertInfos();
    }

    /**
     * 解析主表的主键字段在插入字段中的序号（从0开始）
     *
     * @return
     */
    private int getMasterPrimaryFieldIndex() {
        XField masterPrimaryField = this.masterObject.getPrimaryField();
        List<OqlExpr> fields = this.stmt.getFields();
        int fieldSize = fields.size();
        boolean hasDetailField = false;
        for (int i = 0; i < fieldSize; i++) {
            OqlExpr field = fields.get(i);
            if (field instanceof OqlFieldExpr) {
                XField resolvedField = ((OqlFieldExpr) field).getResolvedField();
                if (resolvedField == masterPrimaryField) {
                    return i;
                }
            } else if (field instanceof OqlObjectExpandExpr) {
                hasDetailField = true;
            }
        }

        if (hasDetailField && !masterPrimaryField.isAutoGen()) {
            throw new FastOqlException("OQL插入语句中存在子表字段，并且未插入非自动生成的主表主键字段");
        }

        return -1;
    }

    /**
     * 处理OQL插入语句的字段
     */
    private void processField(OqlExpr field) {
        if (field instanceof OqlFieldExpr) {
            this.addFieldToStmt((OqlFieldExpr) field);
        } else if (field instanceof OqlObjectExpandExpr) {
            OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
            XObjectRefField objectRefField = objectExpandExpr.getResolvedObjectRefField();
            if (objectRefField.getRefType() == ObjectRefType.LOOKUP) {
                throw new FastOqlException("插入语句中不支持相关表");
            }
            if (objectExpandExpr.isStarExpanded()) {
                throw new FastOqlException("插入语句中不支持\"*\"表达号");
            }

            // 给子表添加展开的字段
            List<SqlExpr> detailFields = objectExpandExpr.getFields();
            for (SqlExpr detailField : detailFields) {
                if (!(detailField instanceof OqlFieldExpr)) {
                    throw new FastOqlException("插入的子表中必须指定具体的子表");
                }
                this.addFieldToStmt((OqlFieldExpr) detailField);
            }

            // 给子表添加masterField字段
            XObject detailObject = objectExpandExpr.getResolvedRefObject();
            XField detailMasterField = detailObject.getObjectRefField(this.masterObject.getName());
            this.addFieldToStmt(OqlUtils.buildFieldExpr(detailMasterField));

            // 无论子表的值是[(), (), ...]的常量语法，还是#{detail}或#{detail(d1, d3, ...)}的变量语法，最后都转为统一的变量语法
            SqlInsertStatement.ValuesClause detailValues = new SqlInsertStatement.ValuesClause();
            for (SqlExpr detailField : detailFields) {
                assert (detailField instanceof OqlFieldExpr);
                XField resolvedField = ((OqlFieldExpr) detailField).getResolvedField();
                detailValues.addValue(OqlUtils.buildFieldVarExpr(resolvedField));
            }

            // 给子表添加masterField字段的值
            detailValues.addValue(OqlUtils.buildFieldVarExpr(detailMasterField));

            OqlInsertStatement detailStmt = this.getObjectStmtByObject(detailObject);
            detailStmt.getValuesList().add(detailValues);
        }
    }

    /**
     * 处理子表字段（detailRefExpr）对应的第valuesIndex个values的对应的值
     *
     * @param detailRefExpr
     * @param valuesIndex
     * @param detailFieldValue
     */
    private void processDetailValue(OqlObjectExpandExpr detailRefExpr, int valuesIndex, SqlExpr detailFieldValue) {
        XObject detailObject = detailRefExpr.getResolvedRefObject();
        List<SqlExpr> detailFields = detailRefExpr.getFields();
        int detailFieldSize = detailFields.size();
        List<String> detailFieldNames = new ArrayList<>();
        for (int i = 0; i < detailFieldSize; i++) {
            String detailFieldName = ((OqlFieldExpr) detailFields.get(i)).getName();
            detailFieldNames.add(detailFieldName);
        }

        if (detailFieldValue instanceof SqlVariantRefExpr) {
            SqlVariantRefExpr varRefExpr = (SqlVariantRefExpr) detailFieldValue;
            String detailVarName = varRefExpr.getVarName();
            List<String> detailFieldVarNames = varRefExpr.getSubVarNames();
            if (CollectionUtils.isEmpty(detailFieldVarNames)) {
                this.extractDetailParamValues(detailObject, detailVarName, detailFieldNames, valuesIndex);
            } else {
                if (detailFieldVarNames.size() != detailFieldSize) {
                    throw new FastOqlException("子表字段的个数与子表字段值的个数不匹配");
                }
                // 从入参中抽取子表的值
                this.extractDetailParamValues(detailObject, detailVarName, detailFieldVarNames, valuesIndex);
            }
        } else if (detailFieldValue instanceof SqlJsonArrayExpr) {
            SqlJsonArrayExpr jsonArrayExpr = (SqlJsonArrayExpr) detailFieldValue;
            this.extractDetailParamValues(detailObject, jsonArrayExpr, detailFieldNames, valuesIndex);
        } else {
            throw new RuntimeException("不支持的字表字段值类型：" + detailFieldValue.getClass().getName());
        }
    }

    /**
     * 从子表的变量值中抽取某个子表字段的列表值
     *
     * @param detailObject
     * @param detailVarName
     * @param detailVarFieldNames
     * @param valuesIndex
     * @return
     */
    private void extractDetailParamValues(XObject detailObject, String detailVarName, List<String> detailVarFieldNames, int valuesIndex) {
        List<Map<String, Object>> detailParamMaps = this.getDetailObjectParamMapsByObject(detailObject);

        if (!this.isBatch) {
            assert (this.paramMap != null);
            List<Map<String, Object>> detailParamValues = this.extractDetailParamValues(this.paramMap, detailVarName, detailVarFieldNames);
            this.completeMasterPrimaryFieldLiteralRecordId(detailObject, 0, valuesIndex, detailParamValues);
            detailParamMaps.addAll(detailParamValues);
        } else {
            assert (this.paramMaps != null);
            int paramIndex = 0;
            for (Map<String, Object> paramMap : this.paramMaps) {
                List<Map<String, Object>> detailParamValues = this.extractDetailParamValues(paramMap, detailVarName, detailVarFieldNames);
                this.completeMasterPrimaryFieldLiteralRecordId(detailObject, paramIndex, valuesIndex, detailParamValues);
                for (Map<String, Object> detailParamValue : detailParamValues) {
                    detailParamValue.put(AbstractOqlInfos.PARAM_INDEX, paramIndex);
                    detailParamValue.put(OqlInsertInfos.VALUES_INDEX, valuesIndex);
                }
                detailParamMaps.addAll(detailParamValues);
                paramIndex++;
            }
        }
    }

    /**
     * 从paramMap中获取变量detailVarName的列表值，并按照detailFieldNames的字段名依次从detailVarNames变量名中取值形成一个列表
     *
     * @param paramMap
     * @param detailVarName
     * @param detailVarFieldNames
     * @return
     */
    private List<Map<String, Object>> extractDetailParamValues(Map<String, Object> paramMap, String detailVarName, List<String> detailVarFieldNames) {
        Object detailParamValues = paramMap.get(detailVarName);
        if (detailParamValues == null) {
            return Collections.emptyList();
        }

        assert (detailParamValues instanceof List);
        int detailVarPropSize = detailVarFieldNames.size();
        List<Map<String, Object>> targetDetailParamValues = new ArrayList<>();
        for (Object detailParamValue : (List) detailParamValues) {
            Map<String, Object> targetDetailParamValue = new HashMap<>();
            for (int i = 0; i < detailVarPropSize; i++) {
                String detailVarPropName = detailVarFieldNames.get(i);
                if (detailParamValue instanceof Map) {
                    targetDetailParamValue.put(detailVarPropName, ((Map) detailParamValue).get(detailVarPropName));
                } else if (detailParamValue instanceof List) { // 对于子表常量的一种特殊处理
                    targetDetailParamValue.put(detailVarPropName, ((List<?>) detailParamValue).get(i));
                }
            }
            targetDetailParamValues.add(targetDetailParamValue);
        }

        return targetDetailParamValues;
    }

    /**
     * 从JSON数组常量中抽取子表的参数值
     *
     * @param detailObject
     * @param fieldValue
     * @param detailFieldNames
     * @param valuesIndex
     */
    private void extractDetailParamValues(XObject detailObject, SqlJsonArrayExpr fieldValue, List<String> detailFieldNames, int valuesIndex) {
        List<Map<String, Object>> detailParamValues = this.extractDetailParamValues(fieldValue, detailFieldNames, valuesIndex);
        this.completeMasterPrimaryFieldLiteralRecordId(detailObject, 0, valuesIndex, detailParamValues);

        List<Map<String, Object>> detailParamMaps = this.getDetailObjectParamMapsByObject(detailObject);
        if (!this.isBatch) {
            detailParamMaps.addAll(detailParamValues);
        } else {
            int paramSize = this.paramMaps.size();
            for (int paramIndex = 0; paramIndex < paramSize; paramIndex++) {
                for (Map<String, Object> detailParamValue : detailParamValues) {
                    detailParamValue.put(AbstractOqlInfos.PARAM_INDEX, paramIndex);
                    detailParamValue.put(OqlInsertInfos.VALUES_INDEX, valuesIndex);
                }
                detailParamMaps.addAll(detailParamValues);
            }
        }
    }

    /**
     * 从JSON数组中抽取一个子表字段的列表值
     *
     * @param fieldValue
     * @param detailFieldNames
     * @param valuesIndex
     * @return
     */
    private List<Map<String, Object>> extractDetailParamValues(SqlJsonArrayExpr fieldValue, List<String> detailFieldNames, int valuesIndex) {
        int detailFieldSize = detailFieldNames.size();
        List<Map<String, Object>> detailParamMaps = new ArrayList<>();
        List<SqlExpr> fieldValueItems = fieldValue.getItems();
        for (SqlExpr fieldValueItem : fieldValueItems) {
            assert (fieldValueItem instanceof SqlListExpr);
            List<SqlExpr> listItems = ((SqlListExpr) fieldValueItem).getItems();
            assert (listItems.size() == detailFieldSize);
            Map<String, Object> detailParamMap = new HashMap<>();
            for (int i = 0; i < detailFieldSize; i++) {
                SqlExpr listItem = listItems.get(i);
                assert (listItem instanceof SqlValuableExpr);
                detailParamMap.put(detailFieldNames.get(i), ((SqlValuableExpr) listItem).getValue());
            }
            detailParamMap.put(OqlInsertInfos.VALUES_INDEX, valuesIndex);
            detailParamMaps.add(detailParamMap);
        }

        return detailParamMaps;
    }

    /**
     * 给子表的数据中添加主表的主键记录ID的值，如果插入的主表字段中有主表记录ID并且值是常量或变量的话
     *
     * @param detailObject
     * @param valuesIndex
     * @param detailParamValues
     */
    private void completeMasterPrimaryFieldLiteralRecordId(XObject detailObject, int paramIndex, int valuesIndex, List<Map<String, Object>> detailParamValues) {
        // 如果主表具有主键列并且值是常量或变量的话，则添加主键变量对应的常量或变量值
        if (masterPrimaryFieldIndex >= 0) {
            Object masterPrimaryFieldParseValue = null;
            String detailMasterFieldName = detailObject.getObjectRefField(this.masterObject.getName()).getName();

            SqlExpr masterPrimaryFieldValue = this.stmt.getValuesList().get(valuesIndex).getValue(masterPrimaryFieldIndex);
            if (masterPrimaryFieldValue instanceof SqlValuableExpr) {
                masterPrimaryFieldParseValue = ((SqlValuableExpr) masterPrimaryFieldValue).getValue();

            } else if (masterPrimaryFieldValue instanceof SqlVariantRefExpr) {
                String varName = ((SqlVariantRefExpr) masterPrimaryFieldValue).getVarName();
                if (!isBatch) {
                    masterPrimaryFieldParseValue = this.paramMap.get(varName);
                } else {
                    masterPrimaryFieldParseValue = this.paramMaps.get(paramIndex).get(varName);
                }
            }

            if (masterPrimaryFieldParseValue != null) {
                for (Map<String, Object> detailParamValue : detailParamValues) {
                    detailParamValue.put(detailMasterFieldName, masterPrimaryFieldParseValue);
                }
            }
        }
    }

    /**
     * 将一个模型的字段添加到模型对应的插入语句的字段中，加入前会去重
     *
     * @param fieldExpr
     * @return
     */
    private void addFieldToStmt(OqlFieldExpr fieldExpr) {
        OqlInsertStatement stmt = this.getObjectStmtByObject(fieldExpr.getResolvedField().getOwner());
        List<OqlExpr> existedExprs = stmt.getFields();
        for (OqlExpr existedExpr : existedExprs) {
            OqlFieldExpr existedFieldExpr = (OqlFieldExpr) existedExpr;
            if (existedFieldExpr.getName().equals(fieldExpr.getName())) {
                return;
            }
        }

        stmt.addField(fieldExpr);
    }

    /**
     * 构建插入信息
     *
     * @return
     */
    private OqlInsertInfos buildInsertInfos() {
        OqlInsertInfos insertInfos = new OqlInsertInfos();
        for (Map.Entry<XObject, OqlInsertStatement> entry : objectStmtMap.entrySet()) {
            XObject thisObject = entry.getKey();
            OqlInsertStatement thisStmt = this.getObjectStmtByObject(thisObject);
            SqlInsertCmdBuilder builder;
            if (thisObject == this.masterObject) {
                if (!this.isBatch) {
                    builder = new SqlInsertCmdBuilder(thisStmt, this.paramMap);
                } else {
                    builder = new SqlInsertCmdBuilder(thisStmt, this.paramMaps);
                }
                SqlInsertCmd thisInsertCmd = builder.build();
                insertInfos.setResolvedMasterObject(thisObject);
                insertInfos.setMasterInsertCmd(thisInsertCmd);
            } else {
                insertInfos.addResolvedDetailObject(thisObject);

                // 构建子表的插入指令
                List<Map<String, Object>> detailParamMaps = this.detailObjectParamMapsMap.get(thisObject);
                if (detailParamMaps == null) {
                    builder = new SqlInsertCmdBuilder(thisStmt, new HashMap<>());
                } else {
                    builder = new SqlInsertCmdBuilder(thisStmt, detailParamMaps);
                }
                SqlInsertCmd detailInsertCmd = builder.build();
                insertInfos.addDetailInsertCmd(detailInsertCmd);
            }
        }

        return insertInfos;
    }

    /**
     * 根据子模型获取它对应的插入语句
     *
     * @param object
     * @return
     */
    private OqlInsertStatement getObjectStmtByObject(XObject object) {
        OqlInsertStatement stmt = this.objectStmtMap.get(object);
        if (stmt == null) {
            OqlInsertInto insertInto = new OqlInsertInto();
            OqlExprObjectSource objectSource = new OqlExprObjectSource(object.getName());
            objectSource.setResolvedObject(object);
            insertInto.setObjectSource(objectSource);
            stmt = new OqlInsertStatement(insertInto);
            this.objectStmtMap.put(object, stmt);
        }

        return stmt;
    }

    /**
     * 根据子模型获取它对应的批量操作参数表
     *
     * @param detailObject
     * @return
     */
    private List<Map<String, Object>> getDetailObjectParamMapsByObject(XObject detailObject) {
        List<Map<String, Object>> paramMapList = this.detailObjectParamMapsMap.get(detailObject);
        if (paramMapList == null) {
            paramMapList = new ArrayList<>();
            this.detailObjectParamMapsMap.put(detailObject, paramMapList);
        }
        return paramMapList;
    }

}
