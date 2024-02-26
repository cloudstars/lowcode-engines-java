package net.cf.form.repository.mongo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoObjectRepositoryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MongoObjectRepositoryImpl mongoObjectRepository() {
        return new MongoObjectRepositoryImpl();
    }
}
