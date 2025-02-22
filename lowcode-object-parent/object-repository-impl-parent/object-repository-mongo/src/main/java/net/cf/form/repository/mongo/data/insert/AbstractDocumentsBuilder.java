package net.cf.form.repository.mongo.data.insert;

import net.cf.form.repository.sql.ast.statement.SqlStatement;
import org.bson.Document;

import java.util.List;

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
@Deprecated
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