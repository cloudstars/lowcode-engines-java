package io.github.cloudstars.lowcode.commons.test.db.dataset.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * MySql测试启动类
 *
 * @author clouds
 */
@SpringBootApplication(exclude = {MongoDataAutoConfiguration.class, MongoAutoConfiguration.class})
public class CommonsTestMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsTestMySqlApplication.class, args);
    }
}
