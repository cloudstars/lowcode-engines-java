package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.commons.config.XConfig;

/**
 * 数据格式配置接口
 *
 * @author clouds
 */
public interface XValueTypeConfig extends XConfig {

    // 数据格式配置名称
    String ATTR = "valueType";

    // 是否必填的属性名称
    String ATTR_REQUIRED = "required";

    // 默认值配置名称
    String  ATTR_DEFAULT_VALUE = "defaultValue";

    /**
     * 获取数据格式对应的数据类型
     *
     * @return 数据类型
     */
    DataTypeEnum getDataType();

    /**
     * 获取是否必填
     *
     * @return
     */
    default Boolean getRequired() {
        return null;
    }

    /**
     * 获取默认值
     *
     * @return
     */
    default Object getDefaultValue() {
        return null;
    }

}
