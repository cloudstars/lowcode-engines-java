package io.github.cloudstars.lowcode.commons.lang.config;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigTypeClass {

    /**
     * 配置类型的代码
     *
     * @return 配置类型的代码
     */
    String code();

    /**
     * 配置类型的名称
     *
     * @return 配置类型的名称
     */
    String name();

}
