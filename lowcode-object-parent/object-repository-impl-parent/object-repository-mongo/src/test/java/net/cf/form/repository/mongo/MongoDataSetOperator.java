package net.cf.form.repository.mongo;

import net.cf.form.repository.testcases.dataset.IDataSetOperator;
import net.cf.form.repository.testcases.dataset.IDataSet;
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
