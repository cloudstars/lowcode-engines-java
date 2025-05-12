package io.github.cloudstars.lowcode.pdf.vendor.convertor;

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

}
