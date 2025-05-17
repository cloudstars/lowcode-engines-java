package io.github.cloudstars.object.engine;


import io.github.cloudstars.lowcode.object.commons.XObjectConfig;

import java.util.Map;

/**
 * 模型
 *
 * @author clouds
 */
public interface XObject {

    /**
     * 获取模型的配置
     *
     * @return
     */
    XObjectConfig getObjectConfig();


    /**
     * 根据字段名称获取字段
     *
     * @param fieldName 字段的名称
     * @return 字段配置
     */
    //F getField(String fieldName);

/**
 * 根据关联模型的编号获取对应的字段
 *
 * @param refFieldName 引用的字段名称
 * @return 引用的字段配置
 */
    //R getObjectRefField(String refFieldName);

    /**
     * 如果是一个子模型的话，返回引用的主模型字段
     *
     * @return 主键字段的配置
     */
    //R getMasterField();


    /**
     * 获取主键值
     *
     * @return
     */
    String getId();

    /**
     * 获取某个键的值
     *
     * @param key
     * @return
     */
    Object getValue(String key);

    /**
     * 设置某个键的值
     *
     * @param key
     * @param value
     */
    void setValue(String key, Object value);

    /**
     * 模型校验
     *
     * @param dataMap 模型数据
     */
    void validate(Map<String, Object> dataMap);

}
