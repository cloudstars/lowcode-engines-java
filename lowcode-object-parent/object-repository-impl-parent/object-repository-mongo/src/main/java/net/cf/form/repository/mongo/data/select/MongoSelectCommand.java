package net.cf.form.repository.mongo.data.select;

import net.cf.form.repository.mongo.data.AbstractMongoCommand;
import org.springframework.data.mongodb.core.aggregation.Aggregation;

import java.util.Map;

public class MongoSelectCommand extends AbstractMongoCommand {

    private String collectionName;

    private Aggregation aggregation;

    private Map<String, String> replaceFields;


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

    public Map<String, String> getReplaceFields() {
        return replaceFields;
    }

    public void setReplaceFields(Map<String, String> replaceFields) {
        this.replaceFields = replaceFields;
    }

    public String getSqlExpr() {
        return this.aggregation.toDocument(this.collectionName, Aggregation.DEFAULT_CONTEXT).toJson();
    }
}
