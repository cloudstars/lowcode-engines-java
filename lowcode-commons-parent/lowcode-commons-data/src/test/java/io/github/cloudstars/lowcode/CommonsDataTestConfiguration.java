package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.data.type.*;
import io.github.cloudstars.lowcode.commons.data.type.custom.FileDataTypeConfig;
import io.github.cloudstars.lowcode.commons.data.type.custom.OptionDataTypeConfig;
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

        // 注册自定义的数据类型时，如：文件、选项
        DataTypeConfigClassFactory.register(FileDataTypeConfig.class);
        DataTypeConfigClassFactory.register(OptionDataTypeConfig.class);
    }

    @Bean
    public DataTypeParser valueTypeParser() {
        return new DataTypeParser();
    }
    
}