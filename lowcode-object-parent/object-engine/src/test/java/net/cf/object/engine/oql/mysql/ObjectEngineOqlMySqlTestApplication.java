package net.cf.object.engine.oql.mysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

/**
 * 测试启动类
 */
@SpringBootApplication(exclude = MongoDataAutoConfiguration.class)
public class ObjectEngineOqlMySqlTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectEngineOqlMySqlTestApplication.class, args);
    }
}
