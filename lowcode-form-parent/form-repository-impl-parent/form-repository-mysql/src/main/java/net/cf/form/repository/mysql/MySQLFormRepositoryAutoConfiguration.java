package net.cf.form.repository.mysql;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MySQLFormRepositoryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MySQLFormRepositoryImpl mysqlFormRepository() {
        return new MySQLFormRepositoryImpl();
    }
}
