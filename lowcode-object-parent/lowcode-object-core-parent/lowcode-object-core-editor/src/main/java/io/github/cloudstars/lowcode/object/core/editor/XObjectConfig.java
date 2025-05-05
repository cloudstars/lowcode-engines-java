package io.github.cloudstars.lowcode.object.core.editor;

import io.github.cloudstars.lowcode.commons.config.XResourceConfig;
import io.github.cloudstars.lowcode.object.core.editor.field.XObjectFieldConfig;
import io.github.cloudstars.lowcode.object.core.editor.field.objectref.XObjectRefObjectFieldConfig;

import java.util.List;
import java.util.Map;

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
     * 根据字段名称获取字段
     *
     * @param fieldName 字段的名称
     * @return 字段配置
     */
    F getField(String fieldName);

    /**
     * 获取主键字段
     *
     * @return 主键字段的配置
     */
    F getPrimaryField();

    /**
     * 根据关联模型的编号获取对应的字段
     *
     * @param refFieldName 引用的字段名称
     * @return 引用的字段配置
     */
    R getObjectRefField(String refFieldName);

    /**
     * 如果是一个子模型的话，返回引用的主模型字段
     *
     * @return 主键字段的配置
     */
    R getMasterField();

    /**
     * 数据校验
     *
     * @param dataMap 数据
     */
    void validate(Map<String, Object> dataMap);

    /**
     * 获取表的名称
     *
     * @return 数据库表的名称
     */
    String getTableName();
}
