package net.cf.object.engine.oql.info;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.util.OqlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OQL插入语句指令构建器
 * <p>
 * 职责：用于将一条OQL语句解析成本表的插入与子表的插入
 */
public class OqlSelectInfoParser extends AbstractOqlInfoParser {

    /**
     * 输入的语句
     */
    private final OqlSelectStatement stmt;

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 本表的查询信息
     */
    private OqlSelectInfo selfSelectInfo;

    /**
     * 子表的查询信息（可能有多个子表）
     */
    private List<OqlSelectInfo> detailSelectInfos;

    /**
     * 一对多的相关表的信息（可能有多个一对多的相关表）
     */
    private List<OqlSelectInfo> multiLookupSelectInfos;

    public OqlSelectInfoParser(OqlSelectStatement stmt) {
        this(stmt, null);
    }

    public OqlSelectInfoParser(OqlSelectStatement stmt, boolean isBatchMode) {
        super(isBatchMode);
        this.stmt = stmt;
        this.paramMap = null;
    }

    public OqlSelectInfoParser(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        super(false);
        this.stmt = stmt;
        this.paramMap = paramMap;
    }

    public OqlSelectInfoParser(OqlSelectStatement stmt, Map<String, Object> paramMap, boolean isBatchMode) {
        super(isBatchMode);
        this.stmt = stmt;
        this.paramMap = paramMap;
    }

    public OqlSelectInfo getSelfObjectSelectInfo() {
        return selfSelectInfo;
    }

    public List<OqlSelectInfo> getDetailObjectSelectInfos() {
        return detailSelectInfos;
    }

    /**
     * 解析OQL语句
     */
    public void parse() {
        OqlSelect select = this.stmt.getSelect();
        XObject selfObject = select.getFrom().getResolvedObject();
        OqlSelectInfo selfOqlInfo = new OqlSelectInfo();
        selfOqlInfo.setObject(selfObject);

        List<OqlSelectItem> selectItems = select.getSelectItems();
        if (!this.hasDetailExpandFields(selectItems)) {
            selfOqlInfo.setStatement(this.stmt);
            selfOqlInfo.setParamMap(this.paramMap);
        } else {
            OqlSelect selfSelect = new OqlSelect();
            this.detailSelectInfos = new ArrayList<>();
            for (OqlSelectItem selectItem : selectItems) {
                SqlExpr expr = selectItem.getExpr();
                if (expr instanceof OqlObjectExpandExpr) {
                    OqlSelectInfo detailSelectInfo = this.processDetail((OqlObjectExpandExpr) expr);
                    detailSelectInfos.add(detailSelectInfo);
                } else {
                    selfSelect.addSelectItem(selectItem);
                }
            }
            selfSelect.setFrom(select.getFrom());
            selfSelect.setWhere(select.getWhere());
            selfOqlInfo.setStatement(new OqlSelectStatement(selfSelect));
        }

        this.selfSelectInfo = selfOqlInfo;
    }

    /**
     * 查询字段列表中是否包含子表扩展查询
     *
     * @param selectItems
     * @return
     */
    protected boolean hasDetailExpandFields(List<OqlSelectItem> selectItems) {
        for (OqlSelectItem selectItem : selectItems) {
            SqlExpr expr = selectItem.getExpr();
            if (this.isDetailFieldExpr(expr)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 生成子表的插入语句
     *
     * <p>
     * case1: select f1, f2, detail from object
     * case2: select f1, f2, detail.* from object
     * case3: select f1, f2, detail(d1, d2, d3(d31, d32)), detail.d4 from object
     * case4: select f1, f2, detail(1 as d1, 2, d3) from object
     *
     * @param objectExpandFieldExpr
     */
    private OqlSelectInfo processDetail(OqlObjectExpandExpr objectExpandFieldExpr) {
        XObject detailObject = objectExpandFieldExpr.getResolvedRefObject();
        OqlSelect detailSelect = new OqlSelect();

        // 构建子模型的查询字段
        boolean isDetailDefaultExpandQuery = false;
        /*if (objectExpandFieldExpr.isDefaultExpanded()) { // case1，默认取模型的主键字段
            OqlSelectItem detailSelectItem = new OqlSelectItem();
            detailSelectItem.setExpr(OqlUtils.defaultPrimaryField(objectExpandFieldExpr.getResolvedRefObject()));
            detailSelect.addSelectItem(detailSelectItem);
            isDetailDefaultExpandQuery = true;
        } else */if (objectExpandFieldExpr.isStarExpanded()) { // case2
            List<OqlExpr> detailExprs = OqlUtils.defaultExpandObjectFields(detailObject);
            for (OqlExpr detailExpr : detailExprs) {
                OqlSelectItem detailSelectItem = new OqlSelectItem();
                detailSelectItem.setExpr(detailExpr);
                detailSelect.addSelectItem(detailSelectItem);
            }
        } else { // case3/4
            List<OqlExpr> fields = objectExpandFieldExpr.getFields();
            for (OqlExpr field : fields) {
                if (field instanceof OqlSelectItem) {
                    detailSelect.addSelectItem((OqlSelectItem) field);
                } else {
                    OqlSelectItem detailSelectItem = new OqlSelectItem();
                    detailSelectItem.setExpr(field);
                    detailSelect.addSelectItem(detailSelectItem);
                }
            }
        }

        // 构建子表数据源
        OqlExprObjectSource objectSource = this.buildExprObjectSource(detailObject);
        detailSelect.setFrom(objectSource);

        // 设置子表查询语句的过滤条件 where
        XObjectRefField masterField = detailObject.getObjectRefField(this.stmt.getSelect().getFrom().getResolvedObject().getName());
        SqlExpr selectWhere;
        if (this.isBatchMode) {
            //  masterField in (#{masterFields})
            selectWhere = OqlUtils.buildFieldInListVarRefExpr(masterField);
        } else {
            // masterField = #{masterField}
            selectWhere = OqlUtils.buildFieldEqualsVarRefExpr(masterField);
        }
        detailSelect.setWhere(selectWhere);

        OqlSelectInfo detailSelectInfo = new OqlSelectInfo();
        detailSelectInfo.setObject(detailObject);
        detailSelectInfo.setStatement(new OqlSelectStatement(detailSelect));
        detailSelectInfo.setParamMap(new HashMap<>());
        detailSelectInfo.setDetailDefaultExpandQuery(isDetailDefaultExpandQuery);
        return detailSelectInfo;
    }

    /**
     * @param detailObject
     * @param detailFieldValues
     * @return
     */
    /*private List<Map<String, Object>> parseDetailValuesList(XObject detailObject, List<SqlExpr> detailFieldValues) {
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
    }*/

    /**
     * 构建子表的插入语句
     *
     * @param detailObjectExpandFieldExpr
     * @return
     */
    /*private OqlInsertStatement buildDetailStatements(OqlObjectExpandExpr detailObjectExpandFieldExpr) {
        OqlInsertInto detailInsertInto = new OqlInsertInto();

        XObject detailObject = detailObjectExpandFieldExpr.getResolvedRefObject();
        String detailObjectName = detailObject.getName();
        XField primaryField = detailObject.getPrimaryField();

        // 构建子表数据源
        OqlExprObjectSource detailObjectSource = new OqlExprObjectSource(detailObjectName);
        detailObjectSource.setResolvedObject(detailObject);
        detailInsertInto.setObjectSource(detailObjectSource);

        // 插入语句中的字段中添加子模型的全部字段，值中添加全部变量
        List<OqlExpr> detailFields = detailObjectExpandFieldExpr.getFields();
        SqlInsertStatement.ValuesClause valuesClause = new SqlInsertStatement.ValuesClause();
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

        return new OqlInsertStatement(detailInsertInto);
    }*/

}
