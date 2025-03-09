package net.cf.excel.engine2;

import net.cf.excel.provider.ExcelToolFactory;
import net.cf.excel.provider.fastexcel.FastExcelExcelToolFactory;
import net.cf.excel.provider.poi.PoiExcelToolFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ExcelEngineTestConfiguration {

    @Profile("poi")
    @Bean
    public ExcelToolFactory poiToolFactory() {
        return new PoiExcelToolFactory();
    }

    @Profile("fastexcel")
    @Bean
    public ExcelToolFactory fastExcelToolFactory() {
        return new FastExcelExcelToolFactory();
    }

    @Bean
    public ExcelEngine excelEngine(ExcelToolFactory provider) {
        return new ExcelEngineImpl(provider);
    }


}
