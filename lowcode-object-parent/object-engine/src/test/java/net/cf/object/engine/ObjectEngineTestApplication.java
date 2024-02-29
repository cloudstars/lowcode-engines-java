package net.cf.object.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 测试启动类
 */
@SpringBootApplication(exclude = MongoAutoConfiguration.class)
public class ObjectEngineTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectEngineTestApplication.class, args);
    }
}
