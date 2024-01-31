package net.cf.form.engine.fieldtype;

import net.cf.form.engine.repository.data.value.DataType;

import java.util.Arrays;
import java.util.List;

/**
 * 内置的字段类型
 *
 * @author clouds
 */
@Deprecated
public final class BuiltinFieldTypes {

    private final static int INT_SIZE = 32;
    private final static int LONG_SIZE = 64;

    static {
        autoIdFieldType = buildAutoIdFieldType();
        detailFieldType = buildDetailFieldType();
        masterFieldType = buildMasterFieldType();
        lookupFieldType = buildLookupFieldType();
    }

    /**
     * 自动生成主键字段类型
     */
    private static XFieldType autoIdFieldType;

    /**
     * 子对象字段
     */
    private static XFieldType detailFieldType;

    /**
     * 主对象引用字段类型
     */
    private static XFieldType masterFieldType;

    /**
     * 关联对象引用字段类型
     */
    private static XFieldType lookupFieldType;

    /**
     * 全部的内置字段类型
     */
    private final static List<XFieldType> allFieldTypes = Arrays.asList(autoIdFieldType, detailFieldType, masterFieldType, lookupFieldType);

    /**
     * 获取自动生成主键字段类型
     *
     * @return
     */
    public static XFieldType getAutoIdFieldType() {
        return autoIdFieldType;
    }

    /**
     * 获取子对象引用字段类型
     *
     * @return
     */
    public static XFieldType getDetailFieldType() {
        return detailFieldType;
    }

    /**
     * 获取主对象引用字段类型
     *
     * @return
     */
    public static XFieldType getMasterFieldType() {
        return masterFieldType;
    }

    /**
     * 获取关联对象引用字段类型
     *
     * @return
     */
    public static XFieldType getLookupFieldType() {
        return lookupFieldType;
    }

    /**
     * 获取全部的系统字段类型
     *
     * @return
     */
    public static List<XFieldType> getAllFieldTypes() {
        return allFieldTypes;
    }

    /**
     * 构建自动生成的主键字段类型
     *
     * @return
     */
    private static XFieldType buildAutoIdFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("AutoGenId", "自动生成ID");
        fieldType.setMultipleValuesSupported(false);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.TEXT, DataType.INTEGER, DataType.LONG));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("autoGenType");
        attr0.setTitle("自动生成的方式");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.TEXT));
        attr0.setOptions(Arrays.asList("uuid", "incr"));
        XFieldTypeAttribute attr1 = new XFieldTypeAttribute();
        attr1.setName("size");
        attr1.setTitle("主键的长度");
        attr1.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.INTEGER));
        attr1.setOptions(Arrays.asList(INT_SIZE, LONG_SIZE));
        fieldType.setAttributes(Arrays.asList(attr0, attr1));
        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> {
            Object autoGenType = field.getAttributeValue("autoGenType");
            if (autoGenType != null && "uuid".equalsIgnoreCase((String) autoGenType)) {
                return new net.cf.form.engine.repository.data.DataType(DataType.TEXT);
            } else {
                Object size = field.getAttributeValue("size");
                if (size != null && INT_SIZE == (Integer) size) {
                    return new net.cf.form.engine.repository.data.DataType(DataType.INTEGER);
                } else {
                    return new net.cf.form.engine.repository.data.DataType(DataType.LONG);
                }
            }
        });

        return fieldType;
    }

    /**
     * 构建子对象字段类型
     *
     * @return
     */
    private static XFieldType buildDetailFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Detail", "子对象");
        fieldType.setMultipleValuesSupported(true);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.OBJECT));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("detailObjectName");
        attr0.setTitle("子对象的名称");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.TEXT));
        fieldType.setAttributes(Arrays.asList(attr0));
        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction(
                // 数据类型同主对象主键的数据类型
                (field) -> new net.cf.form.engine.repository.data.DataType(DataType.OBJECT, true)
        );

        return fieldType;
    }



    /**
     * 构建主对象引用字段类型
     *
     * @return
     */
    private static XFieldType buildMasterFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Master", "主对象引用");
        fieldType.setMultipleValuesSupported(false);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.TEXT, DataType.INTEGER, DataType.LONG, DataType.DATETIME));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("masterObjectName");
        attr0.setTitle("主对象的名称");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.TEXT));
        XFieldTypeAttribute attr1 = new XFieldTypeAttribute();
        attr1.setName("masterPrimaryFieldValueType");
        attr1.setTitle("主对象主键的数值类型");
        attr1.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.TEXT));
        fieldType.setAttributes(Arrays.asList(attr0, attr1));
        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction(
                // 数据类型同主对象主键的数据类型
                (field) -> new net.cf.form.engine.repository.data.DataType(DataType.valueOf(field.getAttributeValue("masterPrimaryFieldValueType").toString()))
        );

        return fieldType;
    }


    /**
     * 构建关联对象引用字段类型
     *
     * @return
     */
    private static XFieldType buildLookupFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Lookup", "关联对象引用");
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("lookupObjectName");
        attr0.setTitle("关联对象的名称");
        XFieldTypeAttribute attr1 = new XFieldTypeAttribute();
        attr1.setName("lookupPrimaryFieldValueType");
        attr1.setTitle("关联对象主键的数值类型");
        attr1.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.TEXT));
        fieldType.setAttributes(Arrays.asList(attr0, attr1));
        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction(
                // 数据类型同主对象主键的数据类型
                (field) -> new net.cf.form.engine.repository.data.DataType(DataType.valueOf(field.getAttributeValue("lookupPrimaryFieldValueType").toString()))
        );

        return fieldType;
    }
}
