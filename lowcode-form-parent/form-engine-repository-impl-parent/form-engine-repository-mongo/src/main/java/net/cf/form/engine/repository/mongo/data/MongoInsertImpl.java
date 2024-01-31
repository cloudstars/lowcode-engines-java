package net.cf.form.engine.repository.mongo.data;

import net.cf.form.engine.repository.sql.ast.statement.SqlInsertStatement;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoInsertImpl extends AbstractMongoDMLImpl {

    private Logger logger = LoggerFactory.getLogger(MongoInsertImpl.class);

    public MongoInsertImpl(MongoTemplate template) {
        super(template);
    }

    public void insert(SqlInsertStatement stmt) {
        Document document = new Document();
        this.template.insert(document);

    }
}
