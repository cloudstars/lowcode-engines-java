package io.github.cloudstars.lowcode.commons.config;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceTypeConfigClass {

    /**
     * 资源类型的名称
     *
     * @return 资源类型的名称
     */
    String name();

    /**
     * 资源类型的描述
     *
     * @return 资源类型的描述
     */
    String description();

}
