package io.github.cloudstars.lowcode.commons.data.field;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldConfigClass {

    /**
     * 字段类型的名称
     *
     * @return 字段类型的名称
     */
    String name();

}
