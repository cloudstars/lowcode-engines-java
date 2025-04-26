package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.form.config.field.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectFormAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的字段配置类型
        FieldConfigClassFactory.register(TextObjectFieldConfig.class);
        FieldConfigClassFactory.register(NumberObjectFieldConfig.class);
        FieldConfigClassFactory.register(JsonObjectFieldConfig.class);
        FieldConfigClassFactory.register(SubFormObjectFieldConfig.class);
    }

}
