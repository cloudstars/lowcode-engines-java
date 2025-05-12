package io.github.cloudstars.lowcode.pdf.vendor.convertor;

import io.github.cloudstars.lowcode.pdf.commons.config.element.AbstractElementConfig;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementConvertorClass {

    /**
     * 转换器类型的名称
     *
     * @return 转换器类型的名称
     */
    String type();

    /**
     * 元素的配置类
     *
     * @return
     */
    Class<? extends AbstractElementConfig> elementClass();

}
