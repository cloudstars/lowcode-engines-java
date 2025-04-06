package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.excel.engine.ExcelEngine;
import io.github.cloudstars.lowcode.excel.engine.ExcelEngineImpl;
import net.cf.excel.provider.ExcelToolFactory;
import net.cf.excel.provider.fastexcel.FastExcelExcelToolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelVendorFastExcelTestConfiguration {

    @Bean
    public ExcelToolFactory fastExcelToolFactory() {
        return new FastExcelExcelToolFactory();
    }

    @Bean
    public ExcelEngine excelEngine(ExcelToolFactory provider) {
        return new ExcelEngineImpl(provider);
    }

}
