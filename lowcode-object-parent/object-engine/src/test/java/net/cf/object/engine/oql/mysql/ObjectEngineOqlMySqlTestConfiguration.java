package net.cf.object.engine.oql.mysql;

import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.form.repository.mysql.MySqlObjectRepositoryImpl;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.OqlEngineImpl;
import net.cf.object.engine.OqlEngineNew;
import net.cf.object.engine.OqlEngineNewImpl;
import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.oql.parser.XObjectResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class ObjectEngineOqlMySqlTestConfiguration {

    @Bean
    public XObjectResolver objectResolver() {
        return new TestObjectResolver();
    }

    @Bean
    public MySqlObjectRepositoryImpl mySQLObjectRepository(NamedParameterJdbcTemplate jdbcTemplate, JdbcTemplate template) {
        return new MySqlObjectRepositoryImpl(jdbcTemplate, template);
    }

    @Bean
    public MySqlDataSetOperator mysqlDataSetOperator(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySqlDataSetOperator(jdbcTemplate);
    }

    @Bean
    public OqlEngine oqlEngine(MySqlObjectRepositoryImpl repository) {
        return new OqlEngineImpl(repository);
    }

    @Bean
    public OqlEngineNew oqlEngineNew(MySqlObjectRepositoryImpl repository) {
        return new OqlEngineNewImpl(repository);
    }

}
