package io.github.cloudstars.lowcode.object.method.editor;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectMethodConfigClass {

    /**
     * 模型方法类型的名称
     *
     * @return 模型方法类型的名称
     */
    String name();

}
