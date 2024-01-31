package net.cf.form.engine.sqlbuilder.delete;

import net.cf.form.engine.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.sqlbuilder.AbstractSqlStatementBuilder;
import net.cf.form.engine.repository.sql.ast.statement.SqlDeleteStatement;

/**
 * 删除SQL语句构建器
 *
 * @author clouds
 */
public class DeleteSqlStatementBuilder extends AbstractSqlStatementBuilder<OqlDeleteStatement, SqlDeleteStatement> {

    public DeleteSqlStatementBuilder() {
    }

    @Override
    public SqlDeleteStatement build() {
        return null;
    }
}
