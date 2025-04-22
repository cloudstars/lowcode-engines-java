package io.github.cloudstars.lowcode.object.form.config.field;

import io.github.cloudstars.lowcode.commons.lang.config.XIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.value.dynamic.XValueConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.XValueTypeConfig;

/**
 * 字段配置接口
 *
 * @author clouds
 */
public interface XFieldConfig extends XIdentifiedConfig {

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
    XValueConfig getValue();

}
