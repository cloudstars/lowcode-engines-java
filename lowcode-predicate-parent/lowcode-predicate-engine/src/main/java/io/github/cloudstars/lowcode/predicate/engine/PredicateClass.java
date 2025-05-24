package io.github.cloudstars.lowcode.predicate.engine;

import io.github.cloudstars.lowcode.predicate.type.XPredicateConfig;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PredicateClass {

    /**
     * 断言类型的名称
     *
     * @return 断言类型的名称
     */
    String name();

    /**
     * 断言配置的类型
     * 
     * @return
     */
    Class<? extends XPredicateConfig> predicateConfigClass();

}
