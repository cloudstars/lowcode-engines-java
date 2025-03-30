package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.commons.data.type.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsDataAutoConfiguration  implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据类型
        DataTypeConfigClassFactory.register(StringDataTypeConfig.class);
        DataTypeConfigClassFactory.register(NumberDataTypeConfig.class);
        DataTypeConfigClassFactory.register(ObjectDataTypeConfig.class);
        DataTypeConfigClassFactory.register(ArrayDataTypeConfig.class);
    }

}
