package io.github.cloudstars.lowcode.commons.data.valuetype.config;

import io.github.cloudstars.lowcode.commons.data.valuetype.DataTypeEnum;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;

/**
 * 数据格式配置接口
 *
 * @author clouds 
 */
public interface XValueTypeConfig<V> extends XConfig {

    // 数据格式属性名称
    String ATTR = "valueType";

    String ATTR_REQUIRED = "required";

    /**
     * 获取数据格式对应的数据类型
     *
     * @return
     */
    DataTypeEnum getDataType();

}
