package net.cf.object.engine;

import net.cf.commons.test.db.dataset.MongoDataSetOperator;
import net.cf.commons.test.db.dataset.MysqlDataSetOperator;
import net.cf.form.repository.mongo.MongoObjectRepositoryImpl;
import net.cf.form.repository.mysql.MySQLObjectRepositoryImpl;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
@AutoConfigureAfter(JdbcTemplateAutoConfiguration.class)
public class ObjectEngineTestConfiguration {

    @Bean
    public MongoObjectRepositoryImpl mongoObjectRepository(MongoTemplate mongoTemplate) {
        return new MongoObjectRepositoryImpl(mongoTemplate);
    }

    @Bean
    public MongoDataSetOperator mongoDataSetOperator(MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
    }

    @Bean
    public MySQLObjectRepositoryImpl mySQLObjectRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MySQLObjectRepositoryImpl(jdbcTemplate);
    }

    @Bean
    public MysqlDataSetOperator mysqlDataSetOperator(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MysqlDataSetOperator(jdbcTemplate);
    }

    @Bean
    public OqlEngine oqlEngine(MySQLObjectRepositoryImpl repository) {
        return new OqlEngineImpl(repository);
    }

}
