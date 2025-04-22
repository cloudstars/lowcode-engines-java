package io.github.cloudstars.lowcode.commons.value.type.config.defaultvalue;

import java.lang.annotation.*;

/**
 * 默认值（不存在时的缺省值）配置类型注解
 *
 * @author clouds
 */
@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultValueConfigClass {

    /**
     * 默认值类型的名称
     *
     * @return 默认值类型的名称
     */
    String name();

}
