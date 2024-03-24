package net.cf.object.engine.oql.mongo;

import net.cf.commons.test.db.dataset.MongoDataSetOperator;
import net.cf.form.repository.mongo.MongoObjectRepositoryImpl;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.OqlEngineImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ObjectEngineOqlMongoTestConfiguration {


    @Bean("MongoObjectRepositoryImpl")
    public MongoObjectRepositoryImpl mongoObjectRepository(@Autowired MongoTemplate mongoTemplate) {
        return new MongoObjectRepositoryImpl(mongoTemplate);
    }

    @Bean
    public MongoDataSetOperator mongoDataSetOperator(@Autowired MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
    }

    @Bean
    public OqlEngine oqlEngine(@Qualifier("MongoObjectRepositoryImpl") MongoObjectRepositoryImpl repository) {
        return new OqlEngineImpl(repository);
    }

}
