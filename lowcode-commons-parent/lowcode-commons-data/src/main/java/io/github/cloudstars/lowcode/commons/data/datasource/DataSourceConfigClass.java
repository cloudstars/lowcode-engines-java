package io.github.cloudstars.lowcode.commons.data.datasource;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceConfigClass {

    /**
     * 数据源类型的名称
     *
     * @return 数据源类型的名称
     */
    String name();

}
