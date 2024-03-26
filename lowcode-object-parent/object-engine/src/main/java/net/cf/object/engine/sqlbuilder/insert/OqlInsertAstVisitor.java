package net.cf.object.engine.sqlbuilder.insert;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XProperty;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.OqlFieldExpandExpr;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlPropertyExpr;
import net.cf.object.engine.oql.util.OqlUtils;
import net.cf.object.engine.sqlbuilder.SqlBuilderOqlAstVisitorAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class OqlInsertAstVisitor extends SqlBuilderOqlAstVisitorAdaptor {

    private final SqlInsertStatementBuilder builder;

    public OqlInsertAstVisitor(SqlInsertStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlInsertStatement x) {
        this.selfObject = x.getObjectSource().getResolvedObject();

        XField primaryField = this.selfObject.getPrimaryField();
        if (primaryField != null && primaryField.isAutoGen()) {
            this.builder.setAutoGenName(primaryField.getName());
        }

        // 构建表源
        SqlExprTableSource tableSource = this.buildSqlTableSource(x.getObjectSource());
        this.builder.tableSource(tableSource);

        // 构建插入的列
        this.buildInsertColumns(x);

        // 输出插入列的列表
        buildInsertValuesList(x);

        return false;
    }

    /**
     * 构建插入列的列表
     *
     * @param x
     */
    private void buildInsertColumns(OqlInsertStatement x) {
        List<SqlExpr> insertFields = x.getFields();
        for (SqlExpr insertField : insertFields) {
            if (insertField instanceof OqlFieldExpandExpr) {
                OqlFieldExpandExpr fieldExpandExpr = (OqlFieldExpandExpr) insertField;
                XField field = fieldExpandExpr.getResolvedField();
                List<? extends SqlExpr> properties;
                if (fieldExpandExpr.isDefaultExpanded() || fieldExpandExpr.isStarExpanded()) {
                    properties = OqlUtils.defaultExpandFieldProperties(field);
                } else {
                    properties = fieldExpandExpr.getProperties();
                }

                for (SqlExpr property : properties) {
                    if (!(property instanceof OqlPropertyExpr)) {
                        throw new FastOqlException("OQL insert语句的字段展开表达式中不支持字段属性之外的表达式");
                    }
                    SqlExpr exprX = this.buildSqlExpr(this.selfObject, property);
                    this.builder.appendColumn(exprX);
                }
            } else {
                SqlExpr exprX = this.buildSqlExpr(this.selfObject, insertField);
                this.builder.appendColumn(exprX);
            }
        }
    }

    /**
     * 构建插入值的列表
     *
     * @param x
     */
    private void buildInsertValuesList(OqlInsertStatement x) {
        List<SqlExpr> insertFields = x.getFields();
        List<SqlInsertStatement.ValuesClause> valuesClauses = x.getValuesList();
        for (SqlInsertStatement.ValuesClause valuesClause : valuesClauses) {
            SqlInsertStatement.ValuesClause sqlValuesClause = this.toSqlValuesClause(valuesClause, insertFields);
            this.builder.appendInsertValues(sqlValuesClause);
        }
    }

    /**
     * 转换成SQL层的插入值子句
     *
     * @param valuesClause
     * @param insertFields
     * @return
     */
    private SqlInsertStatement.ValuesClause toSqlValuesClause(SqlInsertStatement.ValuesClause valuesClause, List<SqlExpr> insertFields) {
        SqlInsertStatement.ValuesClause sqlValuesClause = new SqlInsertStatement.ValuesClause();
        List<SqlExpr> values = valuesClause.getValues();
        // 判断插入的列是否有被展开，如果列有展开，那么值也要被展开
        int size = insertFields.size();
        for (int i = 0; i < size; i++) {
            SqlExpr insertField = insertFields.get(i);
            SqlExpr insertValue = values.get(i);
            if (insertField instanceof OqlFieldExpandExpr) {
                OqlFieldExpandExpr fieldExpandExpr = (OqlFieldExpandExpr) insertField;
            } else {
                sqlValuesClause.addValue(this.buildSqlExpr(this.selfObject, insertValue));
            }
        }

        return sqlValuesClause;
    }

    /**
     * 处理字段展开
     *
     * @param fieldExpandExpr
     * @param insertValue
     * @param sqlValuesClause
     */
    private void processFieldExpand(OqlFieldExpandExpr fieldExpandExpr, SqlExpr insertValue, SqlInsertStatement.ValuesClause sqlValuesClause) {
        List<SqlExpr> properties;
        if (fieldExpandExpr.isDefaultExpanded()) {
            properties = new ArrayList<>();
            List<XProperty> fieldProps = fieldExpandExpr.getResolvedField().getProperties();
            for (XProperty fieldProp : fieldProps) {
                properties.add(new SqlIdentifierExpr(fieldProp.getName()));
            }
        } else {
            properties = fieldExpandExpr.getProperties();
        }

        if (insertValue instanceof SqlJsonObjectExpr) {
            SqlJsonObjectExpr jsonObjectExpr = (SqlJsonObjectExpr) insertValue;
            Map<String, SqlExpr> items = jsonObjectExpr.getItems();
            for (SqlExpr property : properties) {
                if (property instanceof SqlIdentifierExpr) {
                    SqlExpr itemValue = items.get(((SqlIdentifierExpr) property).getName());
                    sqlValuesClause.addValue(itemValue);
                } else {
                    throw new FastOqlException("OQL insert语句的字段展开表达式中不支持字段属性之外的表达式");
                }
            }
        } else if (insertValue instanceof SqlVariantRefExpr) {
            SqlVariantRefExpr variantRefExpr = (SqlVariantRefExpr) insertValue;
            String varName = variantRefExpr.getVarName();
            for (SqlExpr property : properties) {
                if (property instanceof SqlIdentifierExpr) {
                    SqlVariantRefExpr propVariableRefExpr = new SqlVariantRefExpr();
                    propVariableRefExpr.setVarName(varName + "." + ((SqlIdentifierExpr) property).getName());
                    sqlValuesClause.addValue(propVariableRefExpr);
                } else {
                    throw new FastOqlException("OQL insert语句的字段展开表达式中不支持字段属性之外的表达式");
                }
            }
        } else {
            sqlValuesClause.addValue(this.buildSqlExpr(this.selfObject, insertValue));
        }
    }

}