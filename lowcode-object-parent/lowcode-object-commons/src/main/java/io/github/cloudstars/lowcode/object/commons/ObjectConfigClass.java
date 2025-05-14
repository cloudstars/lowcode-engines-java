package io.github.cloudstars.lowcode.object.commons;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectConfigClass {

    /**
     * 模型类型的名称
     *
     * @return 模型类型的名称
     */
    String name();

}
