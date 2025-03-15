package io.github.cloudstars.lowcode.commons.test.db.dataset.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

/**
 * MongoDB 测试启动类
 *
 * @author clouds
 */
@SpringBootApplication(exclude = JdbcTemplateAutoConfiguration.class)
public class CommonsTestMongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsTestMongoApplication.class, args);
    }
}
