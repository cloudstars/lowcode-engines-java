package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.commons.data.defaultvalue.XDefaultValueConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XIdentifiedConfig;

/**
 * 字段配置接口
 *
 * @author clouds
 */
public interface XFieldConfig extends XIdentifiedConfig {

    // 是否必填的属性名称
    String ATTR_REQUIRED = "required";

    // 值属性名称
    String ATTR_VALUE = "value";

    /**
     * 是否必须
     *
     * @return
     */
    boolean isRequired();

    /**
     * 获取字段的数据格式配置
     *
     * @return
     */
    XValueTypeConfig getValueType();

    /**
     * 获取字段的默认值配置
     *
     * @return
     */
    XDefaultValueConfig getDefaultValue();

}
