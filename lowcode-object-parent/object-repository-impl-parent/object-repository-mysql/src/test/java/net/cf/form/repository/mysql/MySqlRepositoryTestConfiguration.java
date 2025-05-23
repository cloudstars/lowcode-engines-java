package net.cf.form.repository.mysql;

import io.github.cloudstars.lowcode.commons.test.db.dataset.MySqlDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class MySqlRepositoryTestConfiguration {

    @Bean
    public MySqlObjectRepositoryImpl mySQLObjectRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcTemplate template) {
        return new MySqlObjectRepositoryImpl(jdbcTemplate, template);
    }

    @Bean
    public MySqlDataSetOperator mysqlDataSetOperator(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySqlDataSetOperator(jdbcTemplate);
    }

}
