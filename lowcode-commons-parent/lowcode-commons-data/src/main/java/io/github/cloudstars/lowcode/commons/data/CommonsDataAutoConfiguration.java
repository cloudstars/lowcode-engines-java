package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.DefaultValueConfigClassFactory;
import io.github.cloudstars.lowcode.commons.data.defaultvalue.ExpressionDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.defaultvalue.StaticDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.predicate.PredicateConfigClassFactory;
import io.github.cloudstars.lowcode.commons.data.predicate.json.JsonPredicateConfig;
import io.github.cloudstars.lowcode.commons.data.predicate.expression.StaticPredicatePredicateConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class CommonsDataAutoConfiguration  implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的数据格式配置类型
        ValueTypeConfigClassFactory.register(TextValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(NumberValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ObjectValueTypeConfig.class);
        ValueTypeConfigClassFactory.register(ArrayValueTypeConfig.class);

        // 注册内置的默认值配置类型
        DefaultValueConfigClassFactory.register(StaticDefaultValueConfig.class);
        DefaultValueConfigClassFactory.register(ExpressionDefaultValueConfig.class);

        // 注册内置的表达式配置类型
        PredicateConfigClassFactory.register(StaticPredicatePredicateConfig.class);
        PredicateConfigClassFactory.register(JsonPredicateConfig.class);
    }

}
