package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.config.XConfig;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * 数据源配置
 *
 * @author clouds
 */
public interface XDataSourceConfig extends XConfig {

    // 数据源配置名称
    String ATTR = "dataSource";

    /**
     * 获取数据源的数据格式
     *
     * @return
     */
    XValueTypeConfig getValueType();

}
