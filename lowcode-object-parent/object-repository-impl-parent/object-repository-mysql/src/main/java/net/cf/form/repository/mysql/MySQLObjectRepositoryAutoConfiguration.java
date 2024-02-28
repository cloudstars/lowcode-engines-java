package net.cf.form.repository.mysql;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class MySQLObjectRepositoryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(MySQLObjectRepositoryImpl.class)
    public MySQLObjectRepositoryImpl mysqlObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySQLObjectRepositoryImpl(jdbcTemplate);
    }
}
