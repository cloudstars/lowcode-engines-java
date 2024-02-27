package net.cf.object.engine.sqlbuilder.update;

import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.sqlbuilder.AbstractSqlStatementBuilder;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;

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
