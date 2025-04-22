package io.github.cloudstars.lowcode.commons;

import io.github.cloudstars.lowcode.commons.value.dynamic.ExpressionValueConfig;
import io.github.cloudstars.lowcode.commons.value.dynamic.StaticValueConfig;
import io.github.cloudstars.lowcode.commons.value.dynamic.ValueConfigClassFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsValueDynamicAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的Value值配置类型
        ValueConfigClassFactory.register(StaticValueConfig.class);
        ValueConfigClassFactory.register(ExpressionValueConfig.class);
    }

}
