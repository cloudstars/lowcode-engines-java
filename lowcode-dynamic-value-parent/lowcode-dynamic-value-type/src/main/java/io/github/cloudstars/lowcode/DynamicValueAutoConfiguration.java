package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.dynamic.value.DynamicValueConfigClassFactory;
import io.github.cloudstars.lowcode.dynamic.value.ExpressionDynamicValueConfig;
import io.github.cloudstars.lowcode.dynamic.value.SystemDynamicValueConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class DynamicValueAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的动态值配置类型
        DynamicValueConfigClassFactory.register(SystemDynamicValueConfig.class);
        DynamicValueConfigClassFactory.register(ExpressionDynamicValueConfig.class);
    }

}
