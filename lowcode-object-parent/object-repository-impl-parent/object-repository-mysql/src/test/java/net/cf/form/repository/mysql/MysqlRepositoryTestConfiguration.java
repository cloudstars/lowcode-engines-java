package net.cf.form.repository.mysql;

import net.cf.form.repository.testcases.dataset.IDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MysqlRepositoryTestConfiguration {

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template;
    }

    @Bean
    public IDataSetOperator initDataLoader(JdbcTemplate jdbcTemplate) {
        return new MysqlDataSetOperator(jdbcTemplate);
    }

}
