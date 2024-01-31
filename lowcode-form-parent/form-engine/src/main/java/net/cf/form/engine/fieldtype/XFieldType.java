package net.cf.form.engine.fieldtype;

import net.cf.form.engine.field.XFieldImpl;
import net.cf.form.engine.repository.data.value.DataType;

import java.util.List;

/**
 * 字段的类型
 *
 * @author clouds
 */
@Deprecated
public interface XFieldType {

    /**
     * 获取字段类型的名称
     *
     * @return
     */
    String getName();

    /**
     * 获取字段类型的标题
     *
     * @return
     */
    String getTitle();

    /**
     * 是否支持数据多选
     *
     * @return
     */
    boolean isMultipleValuesSupported();

    /**
     * 获取支持的数值类型
     *
     * @return
     */
    List<DataType> getSupportedValueTypes();

    /**
     * 获取字段类型的属性列表
     *
     * @return
     */
    List<XFieldTypeAttribute> getAttributes();

    /**
     * 获取字段类型的某个属性
     *
     * @param name
     * @return
     */
    XFieldTypeAttribute getAttribute(String name);

    /**
     * 生成初始化数据
     *
     * @param data
     * @return
     */
    default Object getValue(Object data) {
        return null;
    }

    /**
     * 校验函数
     *
     * @param data
     * @return
     */
    default boolean validator(Object data) {
        return true;
    }

    /**
     * 将业务层的字段值转为驱动层的字段值
     *
     * @param value
     * @param field
     * @return
     */
    default Object toDataValue(Object value, XFieldImpl field) {
        return value;
    }

    /**
     * 将驱动层的字段值转为业务层的字段值
     *
     * @param value
     * @param field
     * @return
     */
    default Object fromDataValue(Object value, XFieldImpl field) {
        return value;
    }

    /**
     * 格式化函数
     *
     * @param data
     * @return
     */
    default Object format(Object data) {
        return data;
    }


    /**
     * 反格式化函数
     *
     * @param data
     * @return
     */
    default Object unformat(Object data) {
        return data;
    }

    /**
     * 根据字段信息获取字段的数据类型
     *
     * @return
     */
    default net.cf.form.engine.repository.data.DataType getDataType(XFieldImpl field) { return null; }
}
