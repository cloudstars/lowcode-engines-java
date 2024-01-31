package net.cf.form.engine.object;

import net.cf.form.engine.fieldtype.BuiltinFieldTypes;
import net.cf.form.engine.fieldtype.XFieldType;
import net.cf.form.engine.fieldtype.XFieldTypeAttribute;
import net.cf.form.engine.fieldtype.XFieldTypeImpl;
import net.cf.form.engine.repository.data.value.DataType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试用的字段类型
 *
 * @author clouds
 */
@Deprecated
public class TestFieldTypesHolder {

    /**
     * 字段类型映射表
     */
    private static final Map<String, XFieldType> fieldTypeMap = new HashMap<>();

    private static XFieldType autoIdFieldType;

    private static XFieldType detailFieldType;

    private static XFieldType masterFieldType;

    private static XFieldType lookupFieldType;

    private static XFieldType userFieldType;

    private static XFieldType textFieldType;

    private static XFieldType numberFieldType;

    private static XFieldType dateFieldType;

    private static XFieldType optionsFieldType;

    private static XFieldType attachFieldType;

    private static XFieldType sequenceFieldType;

    static {
        // 系统字段类型
        autoIdFieldType = BuiltinFieldTypes.getAutoIdFieldType();
        detailFieldType = BuiltinFieldTypes.getDetailFieldType();
        masterFieldType = BuiltinFieldTypes.getMasterFieldType();
        lookupFieldType = BuiltinFieldTypes.getLookupFieldType();

        // 自定义字段类型
        userFieldType = buildUserFieldType();
        textFieldType = buildTextFieldType();
        numberFieldType = buildNumberFieldType();
        dateFieldType = buildDateFieldType();
        optionsFieldType = buildOptionsFieldType();
        attachFieldType = buildAttachFieldType();
        sequenceFieldType = buildSequenceFieldType();

        List<XFieldType> fieldTypes = Arrays.asList(
                autoIdFieldType, masterFieldType, lookupFieldType,
                userFieldType, textFieldType, numberFieldType,
                dateFieldType, optionsFieldType, attachFieldType,
                sequenceFieldType
        );
        for (XFieldType fieldType : fieldTypes) {
            fieldTypeMap.put(fieldType.getName().toUpperCase(), fieldType);
        }
    }

    /**
     * 根据字段类型名称获取字段类型
     *
     * @param objectName
     * @return
     */
    public static XFieldType getFieldType(String objectName) {
        return fieldTypeMap.get(objectName.toUpperCase());
    }

    public static XFieldType getAutoIdFieldType() {
        return autoIdFieldType;
    }

    public static XFieldType getDetailFieldType() {
        return detailFieldType;
    }

    public static XFieldType getMasterFieldType() {
        return masterFieldType;
    }

    public static XFieldType getLookupFieldType() {
        return lookupFieldType;
    }

    public static XFieldType getUserFieldType() {
        return userFieldType;
    }

    public static XFieldType getTextFieldType() {
        return textFieldType;
    }

    public static XFieldType getNumberFieldType() {
        return numberFieldType;
    }

    public static XFieldType getDateFieldType() {
        return dateFieldType;
    }

    public static XFieldType getOptionsFieldType() {
        return optionsFieldType;
    }

    public static XFieldType getAttachFieldType() {
        return attachFieldType;
    }

    public static XFieldType getSequenceFieldType() {
        return sequenceFieldType;
    }
    /**
     * 构建用户字段类型
     *
     * @return
     */
    private static XFieldType buildUserFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("User", "用户");
        fieldType.setMultipleValuesSupported(true);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.TEXT));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("multiple");
        attr0.setTitle("是否支持多选");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.BOOLEAN));
        XFieldTypeAttribute attr1 = new XFieldTypeAttribute();
        attr1.setName("maxSize");
        attr1.setTitle("最多选择人数");
        attr1.setDefaultValue(5);
        attr1.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.INTEGER));
        fieldType.setAttributes(Arrays.asList(attr0, attr1));
        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> {
            Object multiple = field.getAttributeValue("multiple");
            boolean multi = multiple != null && (Boolean) multiple;;
            return new net.cf.form.engine.repository.data.DataType(DataType.TEXT, multi);
        });

        return fieldType;
    }

    /**
     * 构建文本字段类型
     *
     * @return
     */
    private static XFieldType buildTextFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Text", "文本");
        fieldType.setMultipleValuesSupported(false);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.TEXT));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("multiLine");
        attr0.setTitle("是否多行文本");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.BOOLEAN));
        XFieldTypeAttribute attr1 = new XFieldTypeAttribute();
        attr1.setName("maxLength");
        attr1.setTitle("最大长度");
        attr1.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.INTEGER));
        fieldType.setAttributes(Arrays.asList(attr0, attr1));

        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> new net.cf.form.engine.repository.data.DataType(DataType.TEXT));

        return fieldType;
    }

    /**
     * 构值数字字段类型
     *
     * @return
     */
    private static XFieldType buildNumberFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Number", "数字");
        fieldType.setMultipleValuesSupported(false);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.INTEGER, DataType.LONG, DataType.DECIMAL));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("length");
        attr0.setTitle("长度");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.INTEGER));
        XFieldTypeAttribute attr1 = new XFieldTypeAttribute();
        attr1.setName("precision");
        attr1.setTitle("精度");
        attr1.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.INTEGER));
        fieldType.setAttributes(Arrays.asList(attr0, attr1));
        XFieldTypeAttribute attr2 = new XFieldTypeAttribute();
        attr2.setName("maxValue");
        attr2.setTitle("最大值");
        attr2.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.LONG));
        XFieldTypeAttribute attr3 = new XFieldTypeAttribute();
        attr3.setName("minValue");
        attr3.setTitle("最小值");
        attr3.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.LONG));
        fieldType.setAttributes(Arrays.asList(attr0, attr1, attr2, attr3));

        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> {
            Object precision = field.getAttributeValue("precision");
            if (precision != null) {
                Object length = field.getAttributeValue("length");
                net.cf.form.engine.repository.data.DataType dataType = new net.cf.form.engine.repository.data.DataType(DataType.DECIMAL);
                dataType.setLength((Integer) length);
                dataType.setPrecision((Integer) precision);
                return dataType;
            } else {
                Object maxValue = field.getAttributeValue("maxValue");
                if (maxValue != null && ((Long) maxValue) > Integer.MAX_VALUE) {
                    return new net.cf.form.engine.repository.data.DataType(DataType.LONG);
                }
                Object minValue = field.getAttributeValue("minValue");
                if (minValue != null && ((Long) minValue) < Integer.MIN_VALUE) {
                    return new net.cf.form.engine.repository.data.DataType(DataType.LONG);
                }

                return new net.cf.form.engine.repository.data.DataType(DataType.INTEGER);
            }
        });

        return fieldType;
    }

    /**
     * 构建日期字段类型
     *
     * @return
     */
    private static XFieldType buildDateFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Date", "日期");
        fieldType.setMultipleValuesSupported(false);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.DATETIME));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("format");
        attr0.setTitle("日期格式");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.TEXT));
        attr0.setOptions(Arrays.asList("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"));
        fieldType.setAttributes(Arrays.asList(attr0));

        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> new net.cf.form.engine.repository.data.DataType(DataType.DATETIME));

        return fieldType;
    }

    /**
     * 构建选项字段类型
     *
     * @return
     */
    private static XFieldType buildOptionsFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Options", "选项");
        fieldType.setMultipleValuesSupported(true);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.TEXT, DataType.INTEGER));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("valueType");
        attr0.setTitle("值类型");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.TEXT));
        attr0.setDefaultValue("text");
        attr0.setOptions(Arrays.asList("text", "integer"));
        XFieldTypeAttribute attr1 = new XFieldTypeAttribute();
        attr1.setName("multiple");
        attr1.setTitle("是否允许多选");
        attr1.setDefaultValue(false);
        attr1.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.BOOLEAN));
        XFieldTypeAttribute attr2 = new XFieldTypeAttribute();
        attr2.setName("options");
        attr2.setTitle("选项列表");
        attr2.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.OBJECT, true));
        fieldType.setAttributes(Arrays.asList(attr0, attr1, attr2));

        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> {
            Object valueType = field.getAttributeValue("valueType");
            Object multiple = field.getAttributeValue("multiple");
            boolean multi = valueType != null && (Boolean) multiple;
            DataType valType = DataType.valueOf(valueType.toString());
            return new net.cf.form.engine.repository.data.DataType(valType, multi);
        });

        return fieldType;
    }

    /**
     * 构建附件字段类型
     *
     * @return
     */
    private static XFieldType buildAttachFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Attach", "附件");
        fieldType.setMultipleValuesSupported(true);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.OBJECT));
        // 设置属性
        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
        attr0.setName("maxSize");
        attr0.setTitle("最大上传量");
        attr0.setDataType(new net.cf.form.engine.repository.data.DataType(DataType.INTEGER));
        attr0.setDefaultValue(1);
        fieldType.setAttributes(Arrays.asList(attr0));

        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> {
            Object maxSize = field.getAttributeValue("maxSize");;
            boolean multi = maxSize != null && (Integer) maxSize > 1;
            return new net.cf.form.engine.repository.data.DataType(DataType.OBJECT, multi);
        });

        return fieldType;
    }

    private static XFieldType buildSequenceFieldType() {
        XFieldTypeImpl fieldType = new XFieldTypeImpl("Sequence", "流水号");
        fieldType.setMultipleValuesSupported(false);
        fieldType.setSupportedValueTypes(Arrays.asList(DataType.TEXT));
        // 设置属性
//        XFieldTypeAttribute attr0 = new XFieldTypeAttribute();
//        attr0.setName("maxSize");
//        attr0.setTitle("最大上传量");
//        attr0.setDataType(new DataType(ValueType.INTEGER));
//        attr0.setDefaultValue(1);
//        fieldType.setAttributes(Arrays.asList(attr0));

        // 设置生成数据类型的函数
        fieldType.setGetDataTypeFunction((field) -> new net.cf.form.engine.repository.data.DataType(DataType.TEXT));

        return fieldType;
    }
}
