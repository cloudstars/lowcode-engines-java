package net.cf.form.engine.object;

import java.util.Collections;
import java.util.List;

/**
 * 字段类型接口
 *
 * @author clouds
 */
public interface XFieldType {

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
    default <T extends XField> boolean isCollection(T field) {
        return false;
    }

    /**
     * 获取字段类型
     *
     * @return
     */
    default <T extends XField> DataType getDataType(T field) {
        return DataType.STRING;
    }

    /**
     * 获取字段类型的默认值，当用户没有输入时，如果默认值返回不为null，则会补充默认值
     *
     * @param field
     * @return
     */
    default <T extends XField> Object getDefaultValue(T field) {
        return null;
    }

    /**
     * 批量获取字段类型的默认值
     *
     * @param field
     * @return
     */
    default <T extends XField> List<Object> getDefaultValues(T field) {
        return Collections.emptyList();
    }

    /**
     * 根据字段获取字段的子属性列表
     *
     * @param field
     * @return
     * @param <T>
     */
    default <T extends XField> List<XFieldProperty> getProperties(T field) {
        return Collections.emptyList();
    }

    /**
     * 根据字段获取字段的校验器列表
     *
     * @param field
     * @return
     * @param <T>
     */
    default <T extends XField> List<XDataValidator> getValidators(T field) {
        return Collections.emptyList();
    }
}
