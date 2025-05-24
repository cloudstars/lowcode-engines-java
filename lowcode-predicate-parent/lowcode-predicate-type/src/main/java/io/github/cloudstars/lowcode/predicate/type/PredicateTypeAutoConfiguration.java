package io.github.cloudstars.lowcode.predicate.type;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class PredicateTypeAutoConfiguration implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 注册内置的断言配置
        PredicateConfigClassFactory.register(FormulaPredicateConfig.class);
        PredicateConfigClassFactory.register(JsonPredicateConfig.class);
    }

}
