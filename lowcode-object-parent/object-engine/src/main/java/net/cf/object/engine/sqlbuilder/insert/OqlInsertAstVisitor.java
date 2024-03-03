package net.cf.object.engine.sqlbuilder.insert;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.visitor.OqlAstVisitorAdaptor;

import java.util.List;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class OqlInsertAstVisitor extends OqlAstVisitorAdaptor {

    private final SqlInsertStatementBuilder builder;

    public OqlInsertAstVisitor(SqlInsertStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlInsertStatement x) {
        this.resolvedObject = x.getObjectSource().getResolvedObject();

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
        List<SqlExpr> fields = x.getFields();
        for (SqlExpr field : fields) {
            SqlExpr column = this.buildSqlExpr(field);
            this.builder.appendColumn(column);
        }
    }

    /**
     * 构建插入值的列表
     *
     * @param x
     */
    private void buildInsertValuesList(OqlInsertStatement x) {
        List<SqlInsertStatement.ValuesClause> valuesClauses = x.getValuesList();
        for (SqlInsertStatement.ValuesClause valuesClause : valuesClauses) {
            SqlInsertStatement.ValuesClause sqlValuesClause = new SqlInsertStatement.ValuesClause();
            List<SqlExpr> values = valuesClause.getValues();
            for (SqlExpr value : values) {
                SqlExpr sqlValue = this.buildSqlExpr(value);
                sqlValuesClause.addValue(sqlValue);
            }
            this.builder.appendInsertValues(sqlValuesClause);
        }
    }

}