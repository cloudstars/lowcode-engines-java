package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueTypeClass {

    /**
     * 数据格式类型的名称
     *
     * @return 数据格式类型的名称
     */
    String name();

    /**
     * 数据格式配置的类型
     * 
     * @return
     */
    Class<? extends XValueTypeConfig> valueTypeConfigClass();

}
