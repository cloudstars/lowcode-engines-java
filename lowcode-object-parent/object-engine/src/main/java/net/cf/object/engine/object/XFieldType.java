package net.cf.object.engine.object;

import java.util.Collections;
import java.util.List;

/**
 * 字段类型接口
 *
 * @author clouds
 * @param <F> 字段类型的实现
 */
public interface XFieldType<F extends XField> {

    /**
     * 字段类型的名称
     *
     * @return
     */
    String getName();

    /**
     * 字段类型的代码
     *
     * @return
     */
    String getCode();

    /**
     * 是否集合数据
     *
     * @return
     */
    default boolean isCollection(F field) {
        return false;
    }

    /**
     * 获取字段类型
     *
     * @return
     */
    default DataType getDataType(F field) {
        return DataType.STRING;
    }

    /**
     * 获取字段类型的默认值，当用户没有输入时，如果默认值返回不为null，则会补充默认值
     *
     * @param field
     * @return
     */
    default Object getDefaultValue(F field) {
        return null;
    }

    /**
     * 批量获取字段类型的默认值
     *
     * @param field
     * @return
     */
    default List<Object> getDefaultValues(F field) {
        return Collections.emptyList();
    }

    /**
     * 根据字段获取字段的子属性列表
     *
     * @param field
     * @return
     */
    default List<XFieldProperty> getProperties(F field) {
        return Collections.emptyList();
    }

    /**
     * 根据字段获取字段的校验器列表
     *
     * @param field
     * @return
     */
    default List<XDataValidator> getValidators(F field) {
        return Collections.emptyList();
    }
}
