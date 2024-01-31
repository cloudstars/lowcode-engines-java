package net.cf.form.engine.repository.mongo.aggregation;

import org.bson.Document;
import org.springframework.data.mongodb.core.aggregation.AggregationExpression;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;

public class DocumentAggregateionExpression implements AggregationExpression {

    private Document document;

    @Override
    public Document toDocument(AggregationOperationContext context) {
        return this.document;
    }

    public DocumentAggregateionExpression() {

    }

    public DocumentAggregateionExpression(Document document) {
        this.document = document;
    }

}
