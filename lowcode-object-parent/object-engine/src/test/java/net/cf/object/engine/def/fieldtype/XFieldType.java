package net.cf.object.engine.def.fieldtype;

import net.cf.object.engine.def.field.FieldTestImpl;
import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XFieldProperty;
import net.cf.object.engine.fieldtype.DefaultValueConfig;
import net.cf.object.engine.fieldtype.DefaultValueType;

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
     * 获取字段类型的个性化属性描述列表
     *
     * @return
     */
    default List<AttributeDescriptor> getAttributeDescriptors() {
        return Collections.emptyList();
    }

    /**
     * 是否集合数据
     *
     * @return
     */
    default boolean isCollection(FieldTestImpl field) {
        return false;
    }

    /**
     * 获取字段类型
     *
     * @return
     */
    default DataType getDataType(FieldTestImpl field) {
        return DataType.STRING;
    }

    /**
     * 获取字段类型的默认值，当用户没有输入时，如果默认值返回不为null，则会补充默认值
     *
     * @param field
     * @return
     */
    default Object getDefaultValue(FieldTestImpl field) {
        DefaultValueConfig defaultValueConfig = field.getFieldDef().getDefaultValueConfig();
        if (defaultValueConfig != null) {
            if (defaultValueConfig.getType() == DefaultValueType.LITERAL) {
                return defaultValueConfig.getValue();
            }
        }

        return null;
    }

    /**
     * 批量获取字段类型的默认值
     *
     * @param field
     * @return
     */
    default List<Object> getDefaultValues(FieldTestImpl field) {
        return Collections.emptyList();
    }

    /**
     * 获取子属性的定义
     *
     * @param field
     * @return
     */
    default List<XFieldProperty> getProperties(FieldTestImpl field) {
        return Collections.emptyList();
    }

}
