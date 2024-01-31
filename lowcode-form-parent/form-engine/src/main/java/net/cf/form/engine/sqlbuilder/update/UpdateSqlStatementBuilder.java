package net.cf.form.engine.sqlbuilder.update;

import net.cf.form.engine.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.sqlbuilder.AbstractSqlStatementBuilder;
import net.cf.form.engine.repository.sql.ast.statement.SqlUpdateStatement;

/**
 * 更新SQL语句构建器
 *
 * @author clouds
 */
public class UpdateSqlStatementBuilder extends AbstractSqlStatementBuilder<OqlUpdateStatement, SqlUpdateStatement> {

    public UpdateSqlStatementBuilder() {
    }

    @Override
    public SqlUpdateStatement build() {
        return null;
    }
}
