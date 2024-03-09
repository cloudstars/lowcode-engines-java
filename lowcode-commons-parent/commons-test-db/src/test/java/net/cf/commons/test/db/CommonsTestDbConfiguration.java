package net.cf.commons.test.db;

import net.cf.commons.test.db.dataset.MongoDataSetOperator;
import net.cf.commons.test.db.dataset.MysqlDataSetOperator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class CommonsTestDbConfiguration {

    @Bean
    public MongoDataSetOperator mongoDataSetOperator(MongoTemplate mongoTemplate) {
        return new MongoDataSetOperator(mongoTemplate);
    }

    @Bean
    public MysqlDataSetOperator mysqlDataSetOperator(NamedParameterJdbcTemplate jdbcTemplate) {
        return new MysqlDataSetOperator(jdbcTemplate);
    }
}
