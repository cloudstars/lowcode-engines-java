package io.github.cloudstars.lowcode.commons.value.type.config;

import io.github.cloudstars.lowcode.commons.value.type.DataTypeEnum;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;

/**
 * 数据格式配置接口
 *
 * @author clouds 
 */
public interface XValueTypeConfig extends XConfig {

    // 数据格式配置名称
    String ATTR = "valueType";

    /**
     * 获取数据格式对应的数据类型
     *
     * @return 数据类型
     */
    DataTypeEnum getDataType();

}
