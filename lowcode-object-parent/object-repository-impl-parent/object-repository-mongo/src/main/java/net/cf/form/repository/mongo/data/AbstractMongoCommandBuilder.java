package net.cf.form.repository.mongo.data;

import net.cf.form.repository.sql.ast.statement.SqlStatement;

/**
 * Document 构建器
 *
 * @param <S> SQL 语句
 */

/**
 * Document 构建器
 *
 * @param <S> SQL 语句
 */
public abstract class AbstractMongoCommandBuilder<S extends SqlStatement, C extends AbstractMongoCommand> {

    protected AbstractMongoCommandBuilder() {
    }

    /**
     * 根据 SQL 语句构建 Document
     *
     * @return
     */
    protected abstract C build();

}