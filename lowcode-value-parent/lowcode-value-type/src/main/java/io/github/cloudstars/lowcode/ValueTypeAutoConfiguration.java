package io.github.cloudstars.lowcode;

import io.github.cloudstars.lowcode.value.type.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class ValueTypeAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的输入性组件相关的数据格式配置类型
        ValueTypeConfigClassFactory.register(TextValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(NumberValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(BooleanValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(DateValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(TimeValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(MapValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(OptionValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(TreeOptionValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(OrgValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(UserValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ArrayValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(SelectorValueTypeConfig.class);

        // 注册内置的非输入型组件相关的数据格式配置类型（一般用于数据源）
        ValueTypeConfigClassFactory.register(ListValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(TreeValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(PageListValueTypeConfig.class);
    }

}
