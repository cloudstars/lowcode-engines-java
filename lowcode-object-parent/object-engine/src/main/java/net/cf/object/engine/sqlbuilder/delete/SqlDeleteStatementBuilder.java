package net.cf.object.engine.sqlbuilder.delete;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;

/**
 * 删除SQL语句构建器
 *
 * @author clouds
 */
public class SqlDeleteStatementBuilder extends AbstractSqlStatementBuilder<OqlDeleteStatement, SqlDeleteStatement> {

    private final SqlDeleteStatement stmt = new SqlDeleteStatement();

    public SqlDeleteStatementBuilder() {
    }

    public SqlDeleteStatementBuilder from(SqlExprTableSource tableSource) {
        this.stmt.setFrom(tableSource);
        return this;
    }

    public SqlDeleteStatementBuilder where(SqlExpr where) {
        this.stmt.setWhere(where);
        return this;
    }

    @Override
    public SqlDeleteStatement build() {
        return this.stmt;
    }


}
