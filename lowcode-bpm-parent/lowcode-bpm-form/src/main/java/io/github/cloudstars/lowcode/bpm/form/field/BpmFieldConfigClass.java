package io.github.cloudstars.lowcode.bpm.form.field;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BpmFieldConfigClass {

    /**
     * 字段类型的名称
     *
     * @return 字段类型的名称
     */
    String name();

}
