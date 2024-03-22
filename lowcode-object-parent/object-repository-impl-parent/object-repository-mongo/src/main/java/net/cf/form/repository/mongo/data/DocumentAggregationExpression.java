package net.cf.form.repository.mongo.data;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

public class DocumentAggregationExpression implements AggregationExpression {

    private Document document;


    @Override
    public Document toDocument(AggregationOperationContext context) {
        return this.document;
    }

    public DocumentAggregationExpression(Document document) {
        this.document = document;
    }
}
