package net.cf.object.engine.sqlbuilder.insert;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonObjectExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.oql.ast.OqlFieldExpandExpr;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlPropertyExpr;
import net.cf.object.engine.oql.visitor.OqlAstVisitorAdaptor;

import java.util.List;
import java.util.Map;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class OqlInsertAstVisitor extends OqlAstVisitorAdaptor {

    private final SqlInsertStatementBuilder builder;

    /**
     * 调用OQL的参数
     */
    private final Map<String, Object> paramMap;

    public OqlInsertAstVisitor(SqlInsertStatementBuilder builder) {
        this(builder, null);
    }

    public OqlInsertAstVisitor(SqlInsertStatementBuilder builder, Map<String, Object> paramMap) {
        this.builder = builder;
        this.paramMap = paramMap;
    }

    @Override
    public boolean visit(OqlInsertStatement x) {
        this.resolvedObject = x.getObjectSource().getResolvedObject();

        XField primaryField = this.resolvedObject.getPrimaryField();
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
                List<SqlIdentifierExpr> properties = fieldExpandExpr.getProperties();
                for (SqlIdentifierExpr property : properties) {
                    OqlPropertyExpr propertyExpr = new OqlPropertyExpr(field, property.getName());
                    this.builder.appendColumn(this.buildSqlExpr(propertyExpr));
                }
            } else {
                SqlExpr sqlExprX = this.buildSqlExpr(insertField);
                this.builder.appendColumn(sqlExprX);
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
                List<SqlIdentifierExpr> properties = fieldExpandExpr.getProperties();
                if (insertValue instanceof SqlJsonObjectExpr) {
                    SqlJsonObjectExpr jsonObjectExpr = (SqlJsonObjectExpr) insertValue;
                    Map<String, SqlExpr> items = jsonObjectExpr.getItems();
                    for (SqlIdentifierExpr property : properties) {
                        SqlExpr itemValue = items.get(property.getName());
                        sqlValuesClause.addValue(itemValue);
                    }
                } else if (insertValue instanceof SqlVariantRefExpr) {
                    SqlVariantRefExpr variantRefExpr = (SqlVariantRefExpr) insertValue;
                    String varName = variantRefExpr.getVarName();
                    for (SqlIdentifierExpr property : properties) {
                        SqlVariantRefExpr propVariableRefExpr = new SqlVariantRefExpr();
                        propVariableRefExpr.setVarName(varName + "." + property.getName());
                        sqlValuesClause.addValue(propVariableRefExpr);
                    }
                } else {
                    sqlValuesClause.addValue(this.buildSqlExpr(insertValue));
                }
            } else {
                sqlValuesClause.addValue(this.buildSqlExpr(insertValue));
            }
        }

        return sqlValuesClause;
    }

}