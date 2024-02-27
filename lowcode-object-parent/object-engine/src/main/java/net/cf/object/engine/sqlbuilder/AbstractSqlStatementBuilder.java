package net.cf.object.engine.sqlbuilder;

import net.cf.object.engine.oql.ast.OqlStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;

/**
 * SQL语句构建器
 *
 * @param <O> OQL语句
 * @param <S> SQL语句
 */
public abstract class AbstractSqlStatementBuilder<O extends OqlStatement, S extends SqlStatement> {

    protected AbstractSqlStatementBuilder() {
    }

    /**
     * 根据OQL语句构建SQL语句
     *
     * @return
     */
    protected abstract S build();

}
