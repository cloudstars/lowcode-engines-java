package net.cf.commons.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 测试启动类
 *
 * @author clouds
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class CommonsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonsTestApplication.class, args);
    }
}
