package io.github.cloudstars.lowcode.commons.config;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ResourceConfigClass {

    /**
     * 资源类型
     *
     * @return 资源类型
     */
    String type();

    /**
     * 资源名称
     *
     * @return 资源名称
     */
    String name();

}
