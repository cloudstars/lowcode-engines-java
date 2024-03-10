package net.cf.commons.test.db.dataset.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

/**
 * MySql测试启动类
 *
 * @author clouds
 */
@SpringBootApplication(exclude = MongoDataAutoConfiguration.class)
public class CommonsTestMySqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsTestMySqlApplication.class, args);
    }
}
