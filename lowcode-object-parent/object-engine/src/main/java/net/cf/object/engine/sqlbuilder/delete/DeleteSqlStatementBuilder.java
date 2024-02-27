package net.cf.object.engine.sqlbuilder.delete;

import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;

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
