package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.excel.engine.ExcelEngine;
import io.github.cloudstars.lowcode.excel.engine.ExcelEngineImpl;
import net.cf.excel.provider.ExcelToolFactory;
import net.cf.excel.provider.easyexcel.EasyExcelExcelToolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelVendorEasyExcelTestConfiguration {

    @Bean
    public ExcelToolFactory easyExcelToolFactory() {
        return new EasyExcelExcelToolFactory();
    }

    @Bean
    public ExcelEngine excelEngine(ExcelToolFactory provider) {
        return new ExcelEngineImpl(provider);
    }

}
