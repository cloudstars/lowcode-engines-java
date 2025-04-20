package io.github.cloudstars.lowcode.commons;

import io.github.cloudstars.lowcode.commons.value.ValueConfigClassFactory;
import io.github.cloudstars.lowcode.commons.value.ExpressionValueConfig;
import io.github.cloudstars.lowcode.commons.value.StaticValueConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsValueAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据格式配置类型
        ValueTypeConfigClassFactory.register(TextValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(NumberValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ObjectValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(OptionValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ArrayValueTypeConfig.class);

        // 注册内置的Value值配置类型
        ValueConfigClassFactory.register(StaticValueConfig.class);
        ValueConfigClassFactory.register(ExpressionValueConfig.class);
    }

}
