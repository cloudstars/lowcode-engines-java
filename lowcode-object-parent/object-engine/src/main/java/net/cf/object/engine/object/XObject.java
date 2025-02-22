package net.cf.object.engine.object;

import java.util.List;
import java.util.Map;

/**
 * @author clouds
 *
 * 动态模型接口
 */
public interface XObject<F extends XField, R extends XObjectRefField> {

    /**
     * 获取模型名称
     *
     * @return
     */
    String getName();

    /**
     * 获取模型的字段列表
     *
     * @return
     */
    List<F> getFields();

    /**
     * 根据字段名称获取字段
     *
     * @param fieldName
     * @return
     */
    F getField(String fieldName);

    /**
     * 根据关联模型的编号获取对应的字段
     *
     * @param refFieldName
     * @return
     */
    R getObjectRefField2(String refFieldName);
    // 备注：设计存在缺陷，一个模型可能Lookup同一个模型多次，如“员工”引用“兴趣爱好”，有喜欢的引用和不喜欢的引用，没办法通过引用模型的名称查找到唯一的一个
    // R getObjectRefField(String refObjectName);

    /**
     * 获取主键字段
     *
     * @return
     */
    F getPrimaryField();

    /**
     * 如果是一个子模型的话，返回引用的主模型字段
     *
     * @return
     */
    R getMasterField();

    /**
     * 校验函数
     *
     * @return
     */
    void validate(Map<String, Object> dataMap);

    /**
     * 获取表的名称
     *
     * @return
     */
    String getTableName();
}
