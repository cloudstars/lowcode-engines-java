package net.cf.form.repository.mysql;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class MySqlObjectRepositoryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(MySqlObjectRepositoryImpl.class)
    public MySqlObjectRepositoryImpl mysqlObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySqlObjectRepositoryImpl(jdbcTemplate);
    }
}
