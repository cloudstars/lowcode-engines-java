package net.cf.form.repository.mongo;

import net.cf.form.repository.testcases.statement.InitDataLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoRepositoryTestConfiguration {

    @Bean
    public InitDataLoader initDataLoader() {
        return new MongoInitDataLoader();
    }
}
