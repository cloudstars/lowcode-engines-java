package io.github.cloudstars.lowcode.object.method.engine;

import io.github.cloudstars.lowcode.object.method.editor.XObjectMethodConfig;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectMethodClass {

    /**
     * 模型方法类型的名称
     *
     * @return 模型方法类型的名称
     */
    String name();

    /**
     * 模型方法配置的类型
     * 
     * @return
     */
    Class<? extends XObjectMethodConfig> configClass();

}
