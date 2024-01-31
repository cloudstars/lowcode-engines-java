package net.cf.form.engine.repository.mongo.statement;

import net.cf.form.engine.repository.sql.ast.statement.SqlStatement;
import org.bson.Document;

import java.util.List;

/**
 * Document 构建器
 *
 * @param <S> SQL 语句
 */
public abstract class AbstractDocumentsBuilder<S extends SqlStatement> {

    protected AbstractDocumentsBuilder() {
    }

    /**
     * 根据 SQL 语句构建 Document
     *
     * @return
     */
    protected abstract List<Document> build();

}
