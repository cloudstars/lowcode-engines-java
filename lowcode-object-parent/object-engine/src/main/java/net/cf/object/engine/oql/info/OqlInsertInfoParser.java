package net.cf.object.engine.oql.info;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;

import java.util.*;

/**
 * OQL插入语句指令构建器
 * <p>
 * 职责：用于将一条OQL语句解析成本表的插入与子表的插入
 */
public class OqlInsertInfoParser extends AbstractOqlInfoParser {

    /**
     * 输入的语句
     */
    private final OqlInsertStatement stmt;

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 本表的插入信息
     */
    private OqlInsertInfo selfInsertInfo;

    /**
     * 子表的插入信息列表
     */
    private List<OqlDetailInsertInfo> detailInsertInfos;

    public OqlInsertInfoParser(OqlInsertStatement stmt) {
        this(stmt, (Map<String, Object>) null);
    }

    public OqlInsertInfoParser(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        super(false);
        this.stmt = stmt;
        this.paramMap = paramMap;
    }

    public OqlInsertInfo getSelfInsertInfo() {
        return selfInsertInfo;
    }

    public List<OqlDetailInsertInfo> getDetailInsertInfos() {
        return detailInsertInfos;
    }

    /**
     * 解析OQL语句，要考虑insert into (...) values (), (), ... 这种情况
     */
    public void parse() {
        XObject selfObject = this.stmt.getObjectSource().getResolvedObject();
        List<OqlExpr> fields = this.stmt.getFields();
        OqlInsertInfo selfOqlInfo = new OqlInsertInfo();
        selfOqlInfo.setObject(selfObject);
        selfOqlInfo.setParamMap(this.paramMap);
        if (!this.hasDetailFields(fields)) {
            selfOqlInfo.setStatement(this.stmt);
        } else {
            this.detailInsertInfos = new ArrayList<>();

            int fieldSize = fields.size();
            List<SqlInsertStatement.ValuesClause> valuesList = this.stmt.getValuesList();
            int valuesSize = valuesList.size();
            OqlInsertInto selfInsertInto = new OqlInsertInto();
            selfInsertInto.setObjectSource(this.stmt.getObjectSource());
            for (int j = 0; j < valuesSize; j++) {
                selfInsertInto.addValues(new SqlInsertStatement.ValuesClause());
            }

            for (int i = 0; i < fieldSize; i++) {
                OqlExpr field = fields.get(i);
                if (field instanceof OqlObjectExpandExpr) {
                    OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
                    // TODO 请在Checker中校验不支持相关表、子表展开必须显式展开
                    assert (objectExpandExpr.getResolvedObjectRefField().getRefType() == ObjectRefType.DETAIL);
                    assert (!objectExpandExpr.isStarExpanded() && !objectExpandExpr.isDefaultExpanded());
                    List<SqlExpr> fieldValues = new ArrayList<>();
                    for (int j = 0; j < valuesSize; j++) {
                        fieldValues.add(valuesList.get(j).getValue(i));
                    }
                    OqlDetailInsertInfo detailInsertInfo = this.processDetail(objectExpandExpr, fieldValues);
                    this.detailInsertInfos.add(detailInsertInfo);
                } else {
                    selfInsertInto.addField(field);
                    for (int j = 0; j < valuesSize; j++) {
                        SqlExpr fieldValue = valuesList.get(j).getValues().get(i);
                        selfInsertInto.getValuesList().get(j).addValue(fieldValue);
                    }
                }
            }
            selfOqlInfo.setStatement(new OqlInsertStatement(selfInsertInto));
        }

        this.selfInsertInfo = selfOqlInfo;
    }

    /**
     * 表达式列表中是否包含子表
     *
     * @param exprs
     * @return
     */
    protected boolean hasDetailFields(List<OqlExpr> exprs) {
        for (SqlExpr expr : exprs) {
            if (expr instanceof OqlObjectExpandExpr ) {
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) expr;
                if (objectExpandExpr.getResolvedObjectRefField().getRefType() == ObjectRefType.DETAIL) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 生成子表的插入语句
     *
     * <p>
     * case1: insert into object (f1, f2, detail(d1, d2, d3(d31, d32))) values
     * (#{f1}, #{f2}, #{detail}),
     * (#{f1'}, #{f2'}, #{detail'})
     * case2: insert into object (f1, f2, detail(d1, d2, d3(d31, d32)) values (#{f1}, #{f2}, [
     * {"d1": "...", "d2": "...", "d3": {"d31": "...", "d32": "..."}},
     * {"d1": "", "d2": "", "d3": {"d31": "...", "d32": "..."}}
     * ])
     *
     * @param objectExpandFieldExpr
     * @param fieldValues
     */
    private OqlDetailInsertInfo processDetail(OqlObjectExpandExpr objectExpandFieldExpr, List<SqlExpr> fieldValues) {
        XObject detailObject = objectExpandFieldExpr.getResolvedRefObject();

        OqlInsertStatement detailStmt = this.buildDetailStatements(objectExpandFieldExpr);
        List<Map<String, Object>> detailInsertParamMaps = this.parseDetailValuesList(fieldValues);

        OqlDetailInsertInfo detailInsertInfo = new OqlDetailInsertInfo();
        detailInsertInfo.setObject(detailObject);
        detailInsertInfo.setStatement(detailStmt);
        detailInsertInfo.setParamMaps(detailInsertParamMaps);
        return detailInsertInfo;
    }

    /**
     * @param detailFieldValues
     * @return
     */
    private List<Map<String, Object>> parseDetailValuesList(List<SqlExpr> detailFieldValues) {
        List<Map<String, Object>> detailParamMaps = new ArrayList<>();
        for (SqlExpr fieldValue : detailFieldValues) {
            if (fieldValue instanceof SqlVariantRefExpr) {
                SqlVariantRefExpr varRefExpr = (SqlVariantRefExpr) fieldValue;
                String varName = varRefExpr.getVarName();
                Object paramValue = this.paramMap.get(varName);
                if (!(paramValue instanceof List)) {
                    throw new FastOqlException("子表的参数类型必须是一个List<Map>");
                }

                List<Map<String, Object>> listParamValue = (List<Map<String, Object>>) paramValue;
                detailParamMaps.addAll(listParamValue);
            } else if (fieldValue instanceof SqlJsonArrayExpr) {
                // 将Array常量转为List<Map>
                SqlJsonArrayExpr listValueExpr = (SqlJsonArrayExpr) fieldValue;
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
                    detailParamMaps.add(itemValueMap);
                }
            }
        }

        return detailParamMaps;
    }

    /**
     * 构建子表的插入语句
     *
     * @param detailObjectExpandFieldExpr
     * @return
     */
    private OqlInsertStatement buildDetailStatements(OqlObjectExpandExpr detailObjectExpandFieldExpr) {
        OqlInsertInto detailInsertInto = new OqlInsertInto();

        XObject detailObject = detailObjectExpandFieldExpr.getResolvedRefObject();
        String detailObjectName = detailObject.getName();
        XField masterField = detailObject.getMasterField();

        // 构建子表数据源
        OqlExprObjectSource detailObjectSource = new OqlExprObjectSource(detailObjectName);
        detailObjectSource.setResolvedObject(detailObject);
        detailInsertInto.setObjectSource(detailObjectSource);
        SqlInsertStatement.ValuesClause valuesClause = new SqlInsertStatement.ValuesClause();

        // 添加主表记录ID字段（更新时新录入的数据需要补充主表记录ID列）
        String masterFieldName = masterField.getName();
        OqlFieldExpr masterFieldExpr = new OqlFieldExpr(masterFieldName);
        masterFieldExpr.setResolvedField(masterField);
        detailInsertInto.addField(masterFieldExpr);
        SqlVariantRefExpr masterFieldValueExpr = new SqlVariantRefExpr("#{" + masterFieldName +  "}");
        valuesClause.addValue(masterFieldValueExpr);

        // 插入语句中的字段中添加子模型的全部字段，值中添加全部变量
        List<OqlExpr> detailFields = detailObjectExpandFieldExpr.getFields();
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
            SqlVariantRefExpr varRefExpr = new SqlVariantRefExpr("#{" + varName + "}");
            valuesClause.addValue(varRefExpr);
        }
        detailInsertInto.addValues(valuesClause);

        return new OqlInsertStatement(detailInsertInto);
    }

}
