package io.github.cloudstars.lowcode.object.commons.field.property;

import io.github.cloudstars.lowcode.value.type.XValueTypeConfig;

/**
 * 字段的数据子属性（当字段需要拆子属性存储时需要定义）
 *
 * @author clouds
 */
public interface XFieldPropertyConfig {

    /**
     * 获取子属性归属的字段
     *
     * @return 字段属性的归属字段的名称
     */
    String getOwner();

    /**
     * 获取字段属性名称
     *
     * @return 字段属性的名称
     */
    String getName();

    /**
     * 获取字段属性的列名称
     *
     * @return 字段属性的列名称
     */
    String getColumnName();

    /**
     * 获取属性胡数据格式配置
     *
     * @return 属性胡数据格式配置
     */
    XValueTypeConfig getValueType();
}
