package io.github.cloudstars.lowcode.object.commons.field;

import io.github.cloudstars.lowcode.commons.config.XResourceConfig;
import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;
import io.github.cloudstars.lowcode.dynamic.value.XDynamicValueConfig;

/**
 * 字段配置接口
 *
 * @author clouds
 */
public interface XObjectFieldConfig extends XResourceConfig {

    /**
     * 获取地段的标题
     *
     * @return
     */
    // String getTitle();

    /**
     * 获取字段的列名
     *
     * @return
     */
    String getColumnName();

    /**
     * 是否主键字段
     *
     * @return 是否主键字段
     */
    Boolean getPrimaryField();

    /**
     * 是否名称字段
     *
     * @return 是否名称字段
     */
    Boolean getNameField();

    /**
     * 是否自动生成的字段
     *
     * @return
     */
    Boolean getAutoGen();

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
