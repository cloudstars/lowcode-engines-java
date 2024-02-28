package net.cf.object.engine.fieldtype;

import net.cf.object.engine.object.DataType;
import net.cf.object.engine.object.XFieldType;

import java.util.Collections;
import java.util.List;

/**
 * 字段类型接口
 *
 * @author clouds
 */
/**
 * 抽象的字段类型实现类
 *
 * @author clouds
 */
public interface XDevFieldType extends XFieldType {

    /**
     * 获取字段类型的个性化属性描述列表
     *
     * @return
     */
    default List<AttributeDescriptor> getAttributeDescriptors() {
        return Collections.emptyList();
    }

    /**
     * 根据字段Schema获取是否集合数据
     *
     * @return
     */
    default boolean isCollection(FieldSchema fieldSchema) {
        return false;
    }

    /**
     * 根据字段Schema获取数字类型
     *
     * @return
     */
    default DataType getDataType(FieldSchema fieldSchema) {
        return null;
    }

    /**
     * 根据字段Schema获取数字长度
     *
     * @return
     */
    default Integer getDataLength(FieldSchema fieldSchema) {
        return null;
    }

    /**
     * 根据字段Schema获取数据精度
     *
     * @return
     */
    default Integer getDataPrecision(FieldSchema fieldSchema) {
        return null;
    }

    /**
     * 根据字段Schema获取字段的默认值
     * 
     * @param fieldSchema
     * @return
     */
    default Object getDefaultValue(FieldSchema fieldSchema) {
        DefaultValueConfig defaultValueConfig = fieldSchema.getDefaultValueConfig();
        if (defaultValueConfig != null) {
            if (defaultValueConfig.getType() == DefaultValueType.LITERAL) {
                return defaultValueConfig.getValue();
            }
        }

        return null;
    }

    /**
     * 根据字段Schema获取字段的子属性列表
     *
     * @param fieldSchema
     * @return
     */
    default List<FieldProperty> getProperties(FieldSchema fieldSchema) {
        return Collections.emptyList();
    }

    /**
     * 根据字段Schema获取字段的个性化属性列表
     *
     * @param fieldSchema
     * @return
     */
    default List<FieldAttribute> getAttributes(FieldSchema fieldSchema) {
        return Collections.emptyList();
    }
}
