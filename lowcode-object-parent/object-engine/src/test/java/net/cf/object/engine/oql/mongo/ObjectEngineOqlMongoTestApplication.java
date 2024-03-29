package net.cf.object.engine.oql.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

/**
 * 测试启动类
 *
 * @author 80274507
 */
@SpringBootApplication(exclude = JdbcTemplateAutoConfiguration.class)
public class ObjectEngineOqlMongoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectEngineOqlMongoTestApplication.class, args);
    }
}
