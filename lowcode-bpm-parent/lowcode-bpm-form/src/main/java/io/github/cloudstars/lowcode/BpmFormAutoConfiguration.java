package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.bpm.form.field.*;
import io.github.cloudstars.lowcode.bpm.form.field.selector.OptionBpmFiledConfig;
import io.github.cloudstars.lowcode.bpm.form.field.selector.OrgBpmFiledConfig;
import io.github.cloudstars.lowcode.bpm.form.field.selector.TreeBpmFiledConfig;
import io.github.cloudstars.lowcode.bpm.form.field.selector.UserBpmFiledConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class BpmFormAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的BPM字段配置类型
        BpmFieldConfigClassFactory.register(TextBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(NumberBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(BooleanBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(DateBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(TimeBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(ObjectBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(ArrayBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(OptionBpmFiledConfig.class);
        BpmFieldConfigClassFactory.register(TreeBpmFiledConfig.class);
        BpmFieldConfigClassFactory.register(UserBpmFiledConfig.class);
        BpmFieldConfigClassFactory.register(OrgBpmFiledConfig.class);
        BpmFieldConfigClassFactory.register(SubFormBpmFieldConfig.class);
        BpmFieldConfigClassFactory.register(OtherBpmFieldConfig.class);
    }

}
