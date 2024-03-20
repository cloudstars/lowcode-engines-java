package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.mongo.data.AbstractMongoCommand;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

public class MongoSelectCommand extends AbstractMongoCommand {

    private String collectionName;

    private Aggregation aggregation;


    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    public void setAggregation(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    public String getSqlExpr() {
        return this.aggregation.toDocument(this.collectionName, Aggregation.DEFAULT_CONTEXT).toJson();
    }
}
