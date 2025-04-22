package io.github.cloudstars.lowcode.commons.value.dynamic;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValueConfigClass {

    /**
     * 默认值类型的名称
     *
     * @return 默认值类型的名称
     */
    String name();

}
