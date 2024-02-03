package net.cf.form.engine.sqlbuilder.select;

import net.cf.form.engine.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.sqlbuilder.AbstractSqlStatementBuilder;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;

/**
 * 查询SQL语句构建器
 *
 * @author clouds
 */
public class SelectSqlStatementBuilder extends AbstractSqlStatementBuilder<OqlSelectStatement, SqlSelectStatement> {

    public SelectSqlStatementBuilder() {
    }

    @Override
    public SqlSelectStatement build() {
        return null;
    }
}
