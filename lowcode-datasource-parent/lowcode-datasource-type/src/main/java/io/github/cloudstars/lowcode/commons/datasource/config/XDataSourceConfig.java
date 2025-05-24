package io.github.cloudstars.lowcode.commons.datasource.config;

import io.github.cloudstars.lowcode.commons.config.XConfig;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 数据源配置
 *
 * @author clouds
 */
public interface XDataSourceConfig<V extends XValueTypeConfig> extends XConfig {

    // 数据源配置名称
    String ATTR = "dataSource";

    /**
     * 获取数据源的数据格式
     *
     * @return
     */
    V getValueType();

}
