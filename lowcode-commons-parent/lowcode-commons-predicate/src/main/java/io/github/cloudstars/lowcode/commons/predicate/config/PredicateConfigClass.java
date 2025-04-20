package io.github.cloudstars.lowcode.commons.predicate.config;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PredicateConfigClass {

    /**
     * 断言类型的名称
     *
     * @return 断言类型的名称
     */
    String name();

}
