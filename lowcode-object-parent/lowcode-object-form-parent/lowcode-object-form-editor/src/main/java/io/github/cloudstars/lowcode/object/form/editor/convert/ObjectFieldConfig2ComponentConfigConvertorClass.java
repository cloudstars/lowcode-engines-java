package io.github.cloudstars.lowcode.object.form.editor.convert;

import java.lang.annotation.*;

/**
 * 模型字段配置到组件配置转换器类注解
 *
 * @author clouds
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ObjectFieldConfig2ComponentConfigConvertorClass {

    /**
     * 声明转换器类型的名称
     *
     * @return 转换器类型的名称
     */
    String type();

}
