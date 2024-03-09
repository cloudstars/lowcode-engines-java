package net.cf.form.repository.mongo;

import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.commons.test.db.dataset.MongoDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoRepositoryTestConfiguration {

    @Bean
    public IDataSetOperator initDataLoader(MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
    }
}
