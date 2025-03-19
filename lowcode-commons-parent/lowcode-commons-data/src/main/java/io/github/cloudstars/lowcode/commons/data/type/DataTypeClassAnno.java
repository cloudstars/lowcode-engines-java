package io.github.cloudstars.lowcode.commons.data.type;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataTypeClassAnno {

    /**
     * 数据格式类型的名称
     * @return
     */
    String name();

}
