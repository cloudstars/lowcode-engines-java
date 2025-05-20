package io.github.cloudstars.lowcode.object.view.engine;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectViewMethodBuilderClass {

    /**
     * 模型视图构建器类型的名称
     *
     * @return 名称
     */
    String type();

}
