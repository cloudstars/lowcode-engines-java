package net.cf.form.repository.mongo;

import com.mongodb.client.MongoClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

//@Configuration
//@AutoConfiguration(after = MongoAutoConfiguration.class)
public class MongoObjectRepositoryAutoConfiguration {

    public MongoClient mongoClient() {
        //return new MongoClientImpl();
        return null;
    }

    @Bean
    @ConditionalOnMissingBean
    public MongoTemplate mongoTemplate(MongoClient mongoClient) {
        return new MongoTemplate(mongoClient, "");
    }

    @Bean
    @ConditionalOnMissingBean
    public MongoObjectRepositoryImpl mongoObjectRepository(MongoTemplate mongoTemplate) {
        return new MongoObjectRepositoryImpl(mongoTemplate);
    }
}
