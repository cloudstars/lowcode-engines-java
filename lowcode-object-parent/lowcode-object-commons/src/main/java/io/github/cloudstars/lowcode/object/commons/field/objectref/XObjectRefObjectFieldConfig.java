package io.github.cloudstars.lowcode.object.commons.field.objectref;

import io.github.cloudstars.lowcode.object.commons.field.XObjectFieldConfig;

/**
 * 引用字段类型
 *
 * @author clouds
 */
public interface XObjectRefObjectFieldConfig extends XObjectFieldConfig {

    /**
     * 获取引用类型
     *
     * @return
     */
    ObjectRefType getRefType();

    /**
     * 获取引用模型的名称
     *
     * @return 字段属性引用的模型的名称
     */
    String getRefObjectName();

    /**
     * 是否一对多的引用关系
     *
     * @return
     */
    boolean isMultiRef();

}
