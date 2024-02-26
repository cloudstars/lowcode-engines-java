package net.cf.form.repository.mongo;

import net.cf.form.repository.testcases.dataset.IDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

@Configuration
public class MongoRepositoryTestConfiguration {

    @Resource
    private MongoTemplate mongoTemplate;

    @Bean
    public IDataSetOperator initDataLoader(MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
    }
}
