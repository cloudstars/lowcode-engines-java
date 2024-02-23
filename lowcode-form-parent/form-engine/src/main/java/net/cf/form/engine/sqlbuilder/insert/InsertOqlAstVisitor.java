package net.cf.form.engine.sqlbuilder.insert;

import net.cf.form.engine.oql.ast.statement.OqlExprObjectSource;
import net.cf.form.engine.oql.visitor.OqlAstVisitorAdaptor;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;

/**
 * MyOql select语句输出 AST访问器
 *
 * @author clouds
 */
public final class InsertOqlAstVisitor extends OqlAstVisitorAdaptor {

    private final InsertSqlStatementBuilder builder;

    /**
     * 是否在访问 values 部分
     */
    private boolean isInValues;

    public InsertOqlAstVisitor(InsertSqlStatementBuilder builder) {
        this.builder = builder;
    }

    @Override
    public boolean visit(OqlExprObjectSource x) {
        SqlExprTableSource tableSource = new SqlExprTableSource();
        tableSource.setExpr(new SqlIdentifierExpr(tableSource.getName().getSimpleName()));
        tableSource.setAlias(x.getAlias());
        this.builder.tableSource(tableSource);

        return true;
    }

    @Override
    public boolean visit(SqlIdentifierExpr identifierExpr) {
        SqlIdentifierExpr sqlIdentifierExpr = new SqlIdentifierExpr(identifierExpr.getName());
        if (!isInValues) { // 说明在 values 前面出现
            this.builder.appendColumn(sqlIdentifierExpr);
        } else {
            this.builder.appendInsertValuesItem(sqlIdentifierExpr);
        }

        return false;
    }

    @Override
    public boolean visit(SqlCharExpr x) {
        this.builder.appendInsertValuesItem(new SqlCharExpr(x.getText()));

        return false;
    }

    @Override
    public boolean visit(SqlInsertStatement.ValuesClause insertValues) {
        this.isInValues = true;

        SqlInsertStatement.ValuesClause valuesClause = new SqlInsertStatement.ValuesClause();
        this.builder.appendInsertValues(valuesClause);

        return true;
    }



}
