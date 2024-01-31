package net.cf.form.engine.repository.mongo.statement.insert;

import net.cf.form.engine.repository.mongo.statement.AbstractDocumentsBuilder;
import net.cf.form.engine.repository.sql.ast.statement.SqlInsertStatement;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class InsertDocumentsBuilder extends AbstractDocumentsBuilder<SqlInsertStatement> {

    private final List<Document> documents = new ArrayList<>();

    private Document lastDocument = null;

    public InsertDocumentsBuilder newDocument() {
        this.appendDocument(new Document());

        return this;
    }

    private void appendDocument(Document document) {
        this.documents.add(document);
        this.lastDocument = document;
    }

    public InsertDocumentsBuilder appendDocumentKeyValue(String k, Object v) {
        if (this.lastDocument == null) {
            this.appendDocument(new Document());
        }

        this.lastDocument.put(k, v);

        return this;
    }

    @Override
    public List<Document> build() {
        return this.documents;
    }
}
