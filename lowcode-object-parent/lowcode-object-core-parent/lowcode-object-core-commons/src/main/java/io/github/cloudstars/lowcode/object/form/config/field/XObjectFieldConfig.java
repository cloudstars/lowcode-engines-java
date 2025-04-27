package io.github.cloudstars.lowcode.object.form.config.field;

import io.github.cloudstars.lowcode.commons.config.XResourceConfig;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;
import io.github.cloudstars.lowcode.dynamic.value.XDynamicValueConfig;

/**
 * 字段配置接口
 *
 * @author clouds
 */
public interface XObjectFieldConfig extends XResourceConfig {

    // 是否必填的属性名称
    String ATTR_REQUIRED = "required";

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
     * 获取字段的动态值配置
     *
     * @return
     */
    XDynamicValueConfig getDynamicValue();

}
