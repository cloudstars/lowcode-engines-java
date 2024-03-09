package net.cf.form.repository.mysql;

import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.commons.test.db.dataset.MysqlDataSetOperator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MysqlRepositoryTestConfiguration {

    @Bean
    @ConditionalOnMissingBean(NamedParameterJdbcTemplate.class)
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template;
    }

    @Bean
    @ConditionalOnBean(NamedParameterJdbcTemplate.class)
    public IDataSetOperator initDataLoader(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MysqlDataSetOperator(jdbcTemplate);
    }

}
