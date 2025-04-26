package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.XConfig;

/**
 * 数据格式配置接口
 *
 * @author clouds
 */
public interface XValueTypeConfig extends XConfig {

    // 数据格式配置名称
    String ATTR = "valueType";

    // 默认值配置名称
    String  ATTR_DEFAULT_VALUE = "defaultValue";

    /**
     * 获取数据格式对应的数据类型
     *
     * @return 数据类型
     */
    DataTypeEnum getDataType();

    /**
     * 获取默认值
     *
     * @return
     */
    default Object getDefaultValue() {
        return null;
    }

}
