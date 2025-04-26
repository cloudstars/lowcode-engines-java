package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.object.bpm.form.field.*;
import io.github.cloudstars.lowcode.object.bpm.form.field.selector.OptionBpmFiledConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ObjectBpmFormAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的字段配置类型
        BpmFieldConfigClassFactory.register(TextBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(NumberBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(OptionBpmFiledConfig.class);
        BpmFieldConfigClassFactory.register(JsonBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(SubFormBpmFieldConfig.class);
    }

}
