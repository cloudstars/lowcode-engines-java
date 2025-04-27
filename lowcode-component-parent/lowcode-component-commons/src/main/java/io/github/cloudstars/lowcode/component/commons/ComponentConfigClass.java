package io.github.cloudstars.lowcode.component.commons;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentConfigClass {

    /**
     * 组件类型的名称
     *
     * @return 组件类型的名称
     */
    String name();

}
