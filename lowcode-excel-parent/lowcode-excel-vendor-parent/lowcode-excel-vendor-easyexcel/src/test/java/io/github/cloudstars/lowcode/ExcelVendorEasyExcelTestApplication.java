package io.github.cloudstars.lowcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;

/**
 * 测试启动类
 *
 * @author 80274507
 */
@SpringBootApplication(exclude = JdbcTemplateAutoConfiguration.class)
public class ExcelVendorEasyExcelTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcelVendorEasyExcelTestApplication.class, args);
    }
}
