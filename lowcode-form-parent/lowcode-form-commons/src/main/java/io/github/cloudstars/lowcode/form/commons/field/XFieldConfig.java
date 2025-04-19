package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.data.valuetype.XValueTypeConfig;

/**
 * 字段配置接口
 *
 * @author clouds
 */
public interface XFieldConfig {

    /**
     * 获取字段的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取字段的标题
     *
     * @return
     */
    String getTitle();

    /**
     * 获取字段的数据格式
     *
     * @return
     */
    XValueTypeConfig getValueType();
}
