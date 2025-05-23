package net.cf.form.repository.mongo.data;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

public class DocumentAggregationOperation implements AggregationOperation {

    private Document document;

    @Override
    public Document toDocument(AggregationOperationContext context) {
        return this.document;
    }

    public DocumentAggregationOperation(Document document) {
        this.document = document;
    }

}
