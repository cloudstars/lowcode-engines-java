package io.github.cloudstars.lowcode.commons.value.type.config;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueTypeConfigClass {

    /**
     * 数据格式类型的名称
     *
     * @return 数据格式类型的名称
     */
    String name();

}
