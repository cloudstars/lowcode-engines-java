package io.github.cloudstars.lowcode.commons;

import io.github.cloudstars.lowcode.commons.value.type.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsValueTypeAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据格式配置类型
        ValueTypeConfigClassFactory.register(TextValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(NumberValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(BooleanValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(DateValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(TimeValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ObjectValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(OptionValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ArrayValueTypeConfig.class);
    }

}
