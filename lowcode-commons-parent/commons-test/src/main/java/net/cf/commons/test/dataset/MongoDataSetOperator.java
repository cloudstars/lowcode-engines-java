package net.cf.commons.test.dataset;

import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoDataSetOperator implements IDataSetOperator {

    private MongoTemplate mongoTemplate;

    public MongoDataSetOperator(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void setUp(IDataSet dataSet) {

    }

    @Override
    public void tearDown(IDataSet dataSet) {

    }
}
