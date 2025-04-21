package io.github.cloudstars.lowcode.object;

import io.github.cloudstars.lowcode.object.form.config.field.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectFormAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的字段配置类型
        FieldConfigClassFactory.register(TextFieldConfig.class);
        FieldConfigClassFactory.register(NumberFieldConfig.class);
        FieldConfigClassFactory.register(JsonFieldConfig.class);
        FieldConfigClassFactory.register(SubFormFieldConfig.class);
    }

}
