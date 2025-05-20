package io.github.cloudstars.lowcode.object.commons;

import io.github.cloudstars.lowcode.commons.config.XResourceConfig;
import io.github.cloudstars.lowcode.object.commons.field.XObjectFieldConfig;
import io.github.cloudstars.lowcode.object.commons.field.objectref.XObjectRefObjectFieldConfig;

import java.util.List;

/**
 * @author clouds
 *
 * 动态模型接口
 */
public interface XObjectConfig<F extends XObjectFieldConfig, R extends XObjectRefObjectFieldConfig> extends XResourceConfig {

    /**
     * 获取模型名称
     *
     * @return 模型的名称
     */
    String getName();

    /**
     * 获取模型的字段列表
     *
     * @return 模型的字段配置列表
     */
    List<F> getFields();

    /**
     * 根据字段编号获取字段
     *
     * @param key 字段编号
     * @return 字段配置
     */
    F getFieldByKey(String key);

    /**
     * 根据字段名称获取字段
     *
     * @param name 字段名称
     * @return 字段配置
     */
    F getFieldByName(String name);

    /**
     * 获取主键字段
     *
     * @return 主键字段的配置
     */
    // F getPrimaryField();
    String getPrimaryFieldKey();

    /**
     * 获取名称字段
     *
     * @return 名称字段的配置
     */
    // F getNameField();
    String getNameFieldKey();

    /**
     * 获取表的名称
     *
     * @return 数据库表的名称
     */
    String getTableName();
}
