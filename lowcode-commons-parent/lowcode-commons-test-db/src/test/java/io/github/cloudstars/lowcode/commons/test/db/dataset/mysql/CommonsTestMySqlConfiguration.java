
package io.github.cloudstars.lowcode.commons.test.db.dataset.mysql;

import io.github.cloudstars.lowcode.commons.test.db.dataset.MySqlDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class CommonsTestMySqlConfiguration {

    @Bean
    public MySqlDataSetOperator mysqlDataSetOperator(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySqlDataSetOperator(jdbcTemplate);
    }
}
