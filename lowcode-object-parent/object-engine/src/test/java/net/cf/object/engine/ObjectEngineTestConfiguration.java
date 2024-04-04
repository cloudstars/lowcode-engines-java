package net.cf.object.engine;

import net.cf.commons.test.db.dataset.MongoDataSetOperator;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.form.repository.mongo.MongoObjectRepositoryImpl;
import net.cf.form.repository.mysql.MySqlObjectRepositoryImpl;
import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.oql.parser.XObjectResolver;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@AutoConfigureAfter(JdbcTemplateAutoConfiguration.class)
public class ObjectEngineTestConfiguration {

    @Bean
    public XObjectResolver objectResolver() {
        return new TestObjectResolver();
    }

    @Bean
    public MongoObjectRepositoryImpl mongoObjectRepository(MongoTemplate mongoTemplate) {
        return new MongoObjectRepositoryImpl(mongoTemplate);
    }

    @Bean
    public MongoDataSetOperator mongoDataSetOperator(MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
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

}
