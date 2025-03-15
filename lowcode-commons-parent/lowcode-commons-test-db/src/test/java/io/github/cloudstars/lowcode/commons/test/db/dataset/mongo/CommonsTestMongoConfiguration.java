package io.github.cloudstars.lowcode.commons.test.db.dataset.mongo;

import io.github.cloudstars.lowcode.commons.test.db.dataset.MongoDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class CommonsTestMongoConfiguration {


    @Bean
    public MongoDataSetOperator mongoDataSetOperator(MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
    }

}
