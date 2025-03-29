package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.commons.data.type.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsDataAutoConfiguration  implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据类型
        DataTypeClassFactory.register(StringDataTypeConfig.class);
        DataTypeClassFactory.register(NumberDataTypeConfig.class);
        DataTypeClassFactory.register(ObjectDataTypeConfig.class);
        DataTypeClassFactory.register(ArrayDataTypeConfig.class);
    }

}
