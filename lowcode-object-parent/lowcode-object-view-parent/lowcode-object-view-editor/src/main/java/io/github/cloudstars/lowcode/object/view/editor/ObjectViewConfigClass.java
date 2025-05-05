package io.github.cloudstars.lowcode.object.view.editor;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectViewConfigClass {

    /**
     * 模型视图类型的名称
     *
     * @return 模型视图类型的名称
     */
    String name();

}
