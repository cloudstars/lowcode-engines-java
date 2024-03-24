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
     * 获取父模型的名称
     *
     * @return
     */
    String getMasterName();

    /**
     * 获取模型的字段列表
     *
     * @return
     */
    List<F> getFields();

    /**
     * 根据字段名称获取字段
     *
     * @param fieldCode
     * @return
     */
    F getField(String fieldCode);

    /**
     * 获取主键字段
     *
     * @return
     */
    F getPrimaryField();

    /**
     * 根据关联模型的编号获取对应的字段
     *
     * @param refObjectName
     * @return
     */
    R getObjectRefField(String refObjectName);

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
