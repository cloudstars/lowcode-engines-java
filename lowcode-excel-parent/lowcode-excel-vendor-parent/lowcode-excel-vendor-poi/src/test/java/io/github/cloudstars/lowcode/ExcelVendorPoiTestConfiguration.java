package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.excel.engine.ExcelEngine;
import io.github.cloudstars.lowcode.excel.engine.ExcelEngineImpl;
import net.cf.excel.provider.ExcelToolFactory;
import net.cf.excel.provider.poi.PoiExcelToolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelVendorPoiTestConfiguration {

    @Bean
    public ExcelToolFactory poiToolFactory() {
        return new PoiExcelToolFactory();
    }

    @Bean
    public ExcelEngine excelEngine(ExcelToolFactory provider) {
        return new ExcelEngineImpl(provider);
    }

}
