package net.cf.object.engine.oql.testcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 测试启动类
 *
 * @author 80274507
 */
@SpringBootApplication(exclude = { MongoDataAutoConfiguration.class, MongoAutoConfiguration.class })
public class ObjectEngineStatementTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectEngineStatementTestApplication.class, args);
    }
}
