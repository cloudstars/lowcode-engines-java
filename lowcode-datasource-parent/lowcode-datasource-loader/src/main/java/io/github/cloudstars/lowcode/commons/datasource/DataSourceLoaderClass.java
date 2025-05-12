package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.commons.datasource.config.XDataSourceConfig;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSourceLoaderClass {

    /**
     * 数据源加载器类型的名称
     *
     * @return 数据源加载器类型的名称
     */
    String type();

    Class<? extends XDataSourceConfig> dataSourceConfigClass();

}
