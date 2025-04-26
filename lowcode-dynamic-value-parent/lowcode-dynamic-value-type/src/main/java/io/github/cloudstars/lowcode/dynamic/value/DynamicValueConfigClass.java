package io.github.cloudstars.lowcode.dynamic.value;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicValueConfigClass {

    /**
     * 默认值类型的名称
     *
     * @return 默认值类型的名称
     */
    String name();

}
