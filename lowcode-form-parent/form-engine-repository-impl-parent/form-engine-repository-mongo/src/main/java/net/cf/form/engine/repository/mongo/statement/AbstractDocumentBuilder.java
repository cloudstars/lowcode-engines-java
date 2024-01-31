package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.sql.ast.statement.SqlStatement;
import org.bson.Document;

/**
 * Document 构建器
 *
 * @param <S> SQL 语句
 */
public abstract class AbstractDocumentBuilder<S extends SqlStatement> {

    protected AbstractDocumentBuilder() {
    }

    /**
     * 根据 SQL 语句构建 Document
     *
     * @return
     */
    protected abstract Document build();

}
