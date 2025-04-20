package io.github.cloudstars.lowcode.form.commons;

import io.github.cloudstars.lowcode.commons.data.field.FieldConfigClassFactory;
import io.github.cloudstars.lowcode.form.commons.field.JsonFieldConfig;
import io.github.cloudstars.lowcode.form.commons.field.NumberFieldConfig;
import io.github.cloudstars.lowcode.form.commons.field.SubFormFieldConfig;
import io.github.cloudstars.lowcode.form.commons.field.TextFieldConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class FormCommonsAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的字段配置类型
        FieldConfigClassFactory.register(TextFieldConfig.class);
        FieldConfigClassFactory.register(NumberFieldConfig.class);
        FieldConfigClassFactory.register(JsonFieldConfig.class);
        FieldConfigClassFactory.register(SubFormFieldConfig.class);
    }

}
