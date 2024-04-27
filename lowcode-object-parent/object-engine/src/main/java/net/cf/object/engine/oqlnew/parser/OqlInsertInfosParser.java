
package net.cf.object.engine.oqlnew.parser;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertInto;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.ObjectRefType;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oqlnew.info.OqlInsertInfo;
import net.cf.object.engine.oqlnew.info.OqlInsertInfos;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * OQL插入语句解析器
 * <p>
 * 职责：用于将一条OQL插入语句解析成本表的插入与子表的插入
 */
public class OqlInsertInfosParser extends AbstractOqInfoParser<OqlInsertStatement, OqlInsertInfos> {

    /**
     * 输入的参数
     */
    private final Map<String, Object> paramMap;

    /**
     * 输入的参数（批量模式）
     */
    private final List<Map<String, Object>> paramMaps;

    /**
     * 模型关联的插入语句
     */
    private final Map<XObject, SqlInsertStatement> stmtMap = new HashMap<>();

    /**
     * 子表变量名展开的匹配模式
     */
    private final Pattern pattern = Pattern.compile("(\\w+)\\((\\.+)\\)");


    /**
     * 输入的参数
     */
    //private final Map<XObject, Map<String, Object>> paramMapMap = new HashMap<>();

    private final RepoExprBuilder sqlExprBuilder;

    public OqlInsertInfosParser(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        super(stmt, false);
        this.paramMap = paramMap;
        this.paramMaps = null;
        this.sqlExprBuilder = new RepoExprBuilder();
    }

    public OqlInsertInfosParser(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        super(stmt, true);
        this.paramMap = null;
        this.paramMaps = paramMaps;
        this.sqlExprBuilder = new RepoExprBuilder();
    }

    /**
     * 解析成OQL插入语句指令信息
     *
     * @return
     */
    public OqlInsertInfos parse() {
        // 解析当前语句的本模型
        OqlInsertInto insertInfo = this.stmt;
        this.selfObject = insertInfo.getObjectSource().getResolvedObject();

        // 获取本模型对应的SQL语句
        SqlInsertStatement selfStmt = this.getStmtByObject(this.selfObject);
        SqlInsertInto selfInsertInto = selfStmt;
        List<SqlInsertStatement.ValuesClause> valuesList = this.stmt.getValuesList();
        int valuesSize = valuesList.size();
        for (int j = 0; j < valuesSize; j++) {
            selfInsertInto.addValues(new SqlInsertStatement.ValuesClause());
        }

        List<OqlExpr> fields = this.stmt.getFields();
        int fieldSize = fields.size();
        for (int i = 0; i < fieldSize; i++) {
            OqlExpr field = fields.get(i);
            if (field instanceof OqlObjectExpandExpr) {
                OqlObjectExpandExpr objectExpandExpr = (OqlObjectExpandExpr) field;
                XObjectRefField objectRefField = objectExpandExpr.getResolvedObjectRefField();
                // TODO 请在Checker中校验不支持相关表、子表展开必须显式展开
                assert (objectRefField.getRefType() == ObjectRefType.DETAIL);
                assert (!objectExpandExpr.isStarExpanded());

                List<SqlExpr> detailFieldValues = new ArrayList<>();
                for (int j = 0; j < valuesSize; j++) {
                    detailFieldValues.add(valuesList.get(j).getValue(i));
                }

                OqlInsertInfo detailInsertInfo = this.processDetail(objectExpandExpr, detailFieldValues);
                /*if (detailInsertInfo != null) {
                    this.detailInsertInfos.add(detailInsertInfo);
                }*/
            } else if (field instanceof OqlFieldExpr) {
                XField resolvedField = ((OqlFieldExpr) field).getResolvedField();
                selfInsertInto.addColumn(this.toRepoSelfExpr(resolvedField));
                for (int j = 0; j < valuesSize; j++) {
                    SqlExpr fieldValue = valuesList.get(j).getValues().get(i);
                    selfInsertInto.getValuesList().get(j).addValue(fieldValue);
                }
            }
        }

        return null;
    }

    /**
     * 生成子表的插入语句
     *
     * <p>
     * case1 变量: insert into object (f1, f2, detail(d1, d2, d3)) values (#{f1}, #{f2}, #{detail(d1, d2, d3)})
     *   --> 子表转为一条语句，批量
     * case2 常量: insert into object (f1, f2, detail(d1, d2, d3) values ("f1": "...", "f2": "...", {"d1": "...", "d2": "...", "d3": "..."})
     *   --> 子表转为一条语句，批量
     * case3 多条变量: insert into object (f1, f2, detail(d1, d2, d3)) values (#{f1}, #{f2}, #{detail(d1, d2, d3)}), (#{f1'}, #{f2'}, #{detail'(d1', d2', d3')})
     *   --> 子表转为多条语句，批量
     * case4 多条混搭: insert into object (f1, f2, detail(d1, d2, d3) values ("f1": "...", "f2": "...", {"d1": "...", "d2": "...", "d3": "..."}), (#{f1}, #{f2}, #{detail(d1, d2, d3)})
     *   --> 子表转为多条语句，批量
     *
     * @param objectExpandFieldExpr
     * @param fieldValues
     */
    private OqlInsertInfo processDetail(OqlObjectExpandExpr objectExpandFieldExpr, List<SqlExpr> fieldValues) {
        XObject detailObject = objectExpandFieldExpr.getResolvedRefObject();

        SqlInsertStatement detailStmt = this.buildDetailStatements(objectExpandFieldExpr);
        List<Map<String, Object>> detailInsertParamMaps = this.parseDetailValuesList(objectExpandFieldExpr, fieldValues);

        OqlInsertInfo detailInsertInfo = new OqlInsertInfo();
        detailInsertInfo.setResolvedObject(detailObject);
        detailInsertInfo.setBatch(true);
        detailInsertInfo.setStatement(detailStmt);
        detailInsertInfo.setParamMaps(detailInsertParamMaps);
        return detailInsertInfo;
    }

    /**
     * @param detailFieldValues
     * @return
     */
    private List<Map<String, Object>> parseDetailValuesList(OqlObjectExpandExpr objectExpandFieldExpr, List<SqlExpr> detailFieldValues) {
        List<Map<String, Object>> detailParamMaps = new ArrayList<>();
        for (SqlExpr fieldValue : detailFieldValues) {
            if (fieldValue instanceof SqlVariantRefExpr) {
                SqlVariantRefExpr varRefExpr = (SqlVariantRefExpr) fieldValue;
                String varName = varRefExpr.getVarName();
                List<Map<String, Object>> detailValues;
                if (!this.isBatch) {
                    detailValues = this.extractDetailValues(this.paramMap, objectExpandFieldExpr, varName);
                } else {
                    detailValues = this.extractDetailValues(this.paramMaps, objectExpandFieldExpr, varName);
                }
                detailParamMaps.addAll(detailValues);
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
     * 从单个的参数中抽取子表相关的数据
     *
     * @param paramMap
     * @param varName，如：#{detail}，或者#{detail(d1, d2, ...)}
     * @return
     */
    protected List<Map<String, Object>> extractDetailValues(Map<String, Object> paramMap, OqlObjectExpandExpr objectExpandFieldExpr, String varName) {
        Matcher matcher = pattern.matcher(varName);
        String relaVarName = varName;
        boolean hasDetailVars = matcher.matches();
        if (hasDetailVars) {
            relaVarName = matcher.group(1);
        }

        Object paramValue = paramMap.get(relaVarName);
        if (!(paramValue instanceof List)) {
            throw new FastOqlException("子模型的参数类型必须是一个List<Map>");
        }

        if (!hasDetailVars) {
            return (List<Map<String, Object>>) paramValue;
        } else {
            // TODO 根据变量展开的名称转换变量
            return null;
        }
    }

    /**
     * 从批量的参数中抽取子表相关的数据
     *
     * @param paramMaps
     * @param objectExpandFieldExpr
     * @param varName
     * @return
     */
    protected List<Map<String, Object>> extractDetailValues(List<Map<String, Object>> paramMaps, OqlObjectExpandExpr objectExpandFieldExpr, String varName) {
        List<Map<String, Object>> targetValues = new ArrayList<>();
        for (Map<String, Object> paramMap : paramMaps) {
            Object paramValue = paramMap.get(varName);
            if (paramValue == null) {
                continue;
            }
            if (!(paramValue instanceof List)) {
                throw new FastOqlException("子模型的参数类型必须是一个List<Map>");
            }
            if (CollectionUtils.isEmpty((List) paramValue)) {
                continue;
            }
            List<Map<String, Object>> listParamValue = (List<Map<String, Object>>) paramValue;
            targetValues.addAll(listParamValue);
        }

        return targetValues;
    }

    /**
     * 构建子表的插入语句
     *
     * @param detailObjectExpandFieldExpr
     * @return
     */
    private SqlInsertStatement buildDetailStatements(OqlObjectExpandExpr detailObjectExpandFieldExpr) {
        SqlInsertInto detailInsertInto = new SqlInsertInto();

        XObject detailObject = detailObjectExpandFieldExpr.getResolvedRefObject();
        String detailObjectName = detailObject.getName();
        XField masterField = detailObject.getObjectRefField(this.selfObject.getName());

        // 构建子表数据源
        OqlExprObjectSource detailObjectSource = new OqlExprObjectSource(detailObjectName);
        detailObjectSource.setResolvedObject(detailObject);
        detailInsertInto.setTableSource(this.toRepoExprTableSource(detailObjectSource));
        SqlInsertStatement.ValuesClause valuesClause = new SqlInsertStatement.ValuesClause();

        // 添加主表记录ID字段（更新时新录入的数据需要补充主表记录ID列）
        String masterFieldName = masterField.getName();
        OqlFieldExpr masterFieldExpr = new OqlFieldExpr(masterFieldName);
        masterFieldExpr.setResolvedField(masterField);
        detailInsertInto.addColumn(this.toRepoSelfExpr(masterField));
        SqlVariantRefExpr masterFieldValueExpr = new SqlVariantRefExpr("#{" + masterFieldName + "}");
        valuesClause.addValue(masterFieldValueExpr);

        // 插入语句中的字段中添加子模型的全部字段，值中添加全部变量
        List<OqlExpr> detailFields = detailObjectExpandFieldExpr.getFields();
        for (OqlExpr detailField : detailFields) {
            // TODO 在Check中检查字段只能是OqlFieldExpr
            assert (detailField instanceof OqlFieldExpr);

            detailInsertInto.addColumn(detailField);
            String varName = ((OqlFieldExpr) detailField).getName();
            SqlVariantRefExpr varRefExpr = new SqlVariantRefExpr("#{" + varName + "}");
            valuesClause.addValue(varRefExpr);
        }
        detailInsertInto.addValues(valuesClause);

        return new SqlInsertStatement(detailInsertInto);
    }


    /**
     * 根据模型获取它对应的插入语句
     *
     * @param object
     * @return
     */
    private SqlInsertStatement getStmtByObject(XObject object) {
        SqlInsertStatement stmt = this.stmtMap.get(object);
        if (stmt == null) {
            SqlInsertInto insertInto = new SqlInsertInto();
            insertInto.setTableSource(new SqlExprTableSource(object.getTableName()));
            stmt = new SqlInsertStatement(insertInto);
            this.stmtMap.put(object, stmt);
        }
        return stmt;
    }

}
