package net.cf.form.engine.fieldtype;

import net.cf.form.engine.field.XFieldImpl;
import net.cf.form.engine.repository.data.value.DataType;

import java.util.ArrayList;
import java.util.List;

/**
 * 字段类型实现类
 *
 * @author clouds
 */
@Deprecated
public class XFieldTypeImpl implements XFieldType {

    /**
     * 字段类型的名称
     */
    private String name;

    /**
     * 字段类型的标题
     */
    private String title;

    /**
     * 是否支持数据多选
     */
    private boolean multipleValuesSupported;

    /**
     * 支持的数值类型
     */
    private List<DataType> supportedValueTypes;

    /**
     * 字段类型的属性列表
     */
    private List<XFieldTypeAttribute> attributes;

    /**
     * 字段类型的校验函数
     */
    private XFieldValidatorFunction validatorFunction;

    /**
     * 字段类型的默认值函数
     */
    private XFieldDefaultValueFunction defaultValueFunction;

    /**
     * 字段类型的格式化函数
     */
    private XFieldFormatterFunction formatterFunction;

    /**
     * 字段类型的反格式化函数
     */
    private XFieldUnformatterFunction unformatterFunction;

    /**
     * 字段类型的获取数据类型函数
     */
    private XFieldGetDataTypeFunction getDataTypeFunction;

    public XFieldTypeImpl(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public boolean isMultipleValuesSupported() {
        return this.multipleValuesSupported;
    }

    public void setMultipleValuesSupported(boolean multipleValuesSupported) {
        this.multipleValuesSupported = multipleValuesSupported;
    }

    @Override
    public List<DataType> getSupportedValueTypes() {
        return this.supportedValueTypes;
    }

    public void setSupportedValueTypes(List<DataType> supportedValueTypes) {
        this.supportedValueTypes = supportedValueTypes;
    }

    @Override
    public List<XFieldTypeAttribute> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(List<XFieldTypeAttribute> attributes) {
        this.attributes = attributes;
    }

    @Override
    public XFieldTypeAttribute getAttribute(String name) {
        for (XFieldTypeAttribute attribute : attributes) {
            if (attribute.getName().equalsIgnoreCase(name)) {
                return attribute;
            }
        }

        return null;
    }

    /**
     * 添加一个属性
     *
     * @param attribute
     */
    public void addAttribute(XFieldTypeAttribute attribute) {
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        }

        this.attributes.add(attribute);
    }

    @Override
    public Object getValue(Object data) {
        if (defaultValueFunction != null) {
            return defaultValueFunction.getValue(data);
        }

        return XFieldType.super.getValue(data);
    }

    @Override
    public boolean validator(Object data) {
        if (validatorFunction != null) {
            return validatorFunction.validate(data);
        }

        return XFieldType.super.validator(data);
    }

    @Override
    public Object format(Object data) {
        if (formatterFunction != null) {
            return formatterFunction.format(data);
        }

        return XFieldType.super.format(data);
    }

    @Override
    public Object unformat(Object data) {
        if (unformatterFunction != null) {
            return unformatterFunction.unformat(data);
        }

        return XFieldType.super.unformat(data);
    }

    @Override
    public net.cf.form.engine.repository.data.DataType getDataType(XFieldImpl field) {
        if (getDataTypeFunction != null) {
            return getDataTypeFunction.dataType(field);
        }

        return XFieldType.super.getDataType(field);
    }

    public void setValidatorFunction(XFieldValidatorFunction validatorFunction) {
        this.validatorFunction = validatorFunction;
    }

    public void setDefaultValueFunction(XFieldDefaultValueFunction defaultValueFunction) {
        this.defaultValueFunction = defaultValueFunction;
    }

    public void setFormatterFunction(XFieldFormatterFunction formatterFunction) {
        this.formatterFunction = formatterFunction;
    }

    public void setUnformatterFunction(XFieldUnformatterFunction unformatterFunction) {
        this.unformatterFunction = unformatterFunction;
    }

    public void setGetDataTypeFunction(XFieldGetDataTypeFunction getDataTypeFunction) {
        this.getDataTypeFunction = getDataTypeFunction;
    }
}
