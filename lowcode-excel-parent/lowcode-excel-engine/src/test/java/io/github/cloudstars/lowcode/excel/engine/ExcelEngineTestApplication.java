package io.github.cloudstars.lowcode.excel.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

/**
 * 测试启动类
 *
 * @author 80274507
 */
@SpringBootApplication(exclude = JdbcTemplateAutoConfiguration.class)
public class ExcelEngineTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelEngineTestApplication.class, args);
    }
}
