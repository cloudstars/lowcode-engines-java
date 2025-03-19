package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.data.type.custom.FileValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.value.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsDataTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据类型
        ValueTypeClassFactory.register(TextValueTypeConfig.class);
        ValueTypeClassFactory.register(ObjectValueTypeConfig.class);
        ValueTypeClassFactory.register(ArrayValueTypeConfig.class);

        // 注册自定义的数据类型时，如：文件、选项
        ValueTypeClassFactory.register(FileValueTypeConfig.class);
    }

    @Bean
    public ValueTypeParser valueTypeParser() {
        return new ValueTypeParser();
    }
    
}