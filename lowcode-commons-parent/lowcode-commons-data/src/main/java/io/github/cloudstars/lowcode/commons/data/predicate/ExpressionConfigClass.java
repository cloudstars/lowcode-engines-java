package io.github.cloudstars.lowcode.commons.data.predicate;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpressionConfigClass {

    /**
     * 表达式类型的名称
     *
     * @return 表达式类型的名称
     */
    String name();

}
