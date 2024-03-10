package net.cf.form.repository.mysql;

import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class MySqlRepositoryTestConfiguration {

    @Bean
    public MySqlObjectRepositoryImpl mySQLObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySqlObjectRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public MySqlDataSetOperator mysqlDataSetOperator(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySqlDataSetOperator(jdbcTemplate);
    }

}
