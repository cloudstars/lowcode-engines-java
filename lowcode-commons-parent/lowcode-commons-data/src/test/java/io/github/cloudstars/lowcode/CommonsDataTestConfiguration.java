package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.data.type.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsDataTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据类型
        DataTypeConfigClassFactory.register(StringDataTypeConfig.class);
        DataTypeConfigClassFactory.register(ObjectDataTypeConfig.class);
        DataTypeConfigClassFactory.register(ArrayDataTypeConfig.class);
        DataTypeConfigClassFactory.register(OptionDataTypeConfig.class);
    }

    @Bean
    public DataTypeParser valueTypeParser() {
        return new DataTypeParser();
    }
    
}