package net.cf.form.engine.repository.mongo.statement.select;

import org.springframework.data.mongodb.core.aggregation.Aggregation;

public class MongoSelectCommand {

    private Aggregation aggregation;

    private String collectionName;

    public MongoSelectCommand(Aggregation aggregation, String collectionName) {
        this.aggregation = aggregation;
        this.collectionName = collectionName;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    public String getMongoOql() {
        return this.aggregation.toDocument(collectionName,null).toJson();
    }
}
