package net.cf.form.engine.repository.mongo.data;

import org.springframework.data.mongodb.core.MongoTemplate;

public class AbstractMongoDMLImpl {

    protected MongoTemplate template;

    public AbstractMongoDMLImpl(MongoTemplate template) {
        this.template = template;
    }
}
