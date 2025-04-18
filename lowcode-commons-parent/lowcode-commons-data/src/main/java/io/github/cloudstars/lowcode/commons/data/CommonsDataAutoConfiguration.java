package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.commons.data.value.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsDataAutoConfiguration  implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据类型
        ValueTypeConfigClassFactory.register(TextValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(NumberValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ObjectValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ArrayValueTypeConfig.class);
    }

}
