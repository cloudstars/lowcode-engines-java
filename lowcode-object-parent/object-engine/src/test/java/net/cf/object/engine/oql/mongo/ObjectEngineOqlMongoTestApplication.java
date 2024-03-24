package net.cf.object.engine.oql.mongo;

import net.cf.form.repository.mysql.MySqlObjectRepositoryAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试启动类
 *
 * @author 80274507
 */
@SpringBootApplication(exclude = {MySqlObjectRepositoryAutoConfiguration.class})
public class ObjectEngineOqlMongoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectEngineOqlMongoTestApplication.class, args);
    }
}
