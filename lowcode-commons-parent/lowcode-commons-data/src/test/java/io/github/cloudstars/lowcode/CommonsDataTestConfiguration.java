package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.commons.data.expression.json.JsonExpressParser;
import io.github.cloudstars.lowcode.commons.data.valuetype.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonsDataTestConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据类型
        ValueTypeConfigClassFactory.register(TextValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ObjectValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ArrayValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(OptionValueTypeConfig.class);
    }

    @Bean
    public ValueTypeConfigParser valueTypeParser() {
        return new ValueTypeConfigParser();
    }

    @Bean
    public JsonExpressParser jsonExpressParser() {
        return new JsonExpressParser();
    }

}