package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.config.XConfig;

/**
 * 数据格式配置接口
 *
 * @author clouds
 * @param <T>
 */
public interface XValueTypeConfig<T extends Object> extends XConfig {

    // 数据格式配置名称
    String ATTR = "valueType";

    // 是否必填配置名称
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
     * 获取默认值
     *
     * @return
     */
    default Object getDefaultValue() {
        return null;
    }

}
