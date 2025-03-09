package io.github.cloudstars.lowcode.commons.lang.type;

import io.github.cloudstars.lowcode.commons.lang.DataType;
import io.github.cloudstars.lowcode.commons.lang.value.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 选项数据格式
 *
 * @author clouds
 */
public class OptionsValueType extends AbstractValueType {

    /**
     * 标签域
     */
    private String labelField = "label";

    /**
     * 值域
     */
    private String valueField = "value";

    /**
     * 数据类型
     */
    private DataType dataType = DataType.TEXT;



    /**
     * 选项下的属性列表
     */
    private List<ObjectValueType.ObjectProperty> properties;

    public OptionsValueType() {
    }

    public OptionsValueType(String labelField, String valueField) {
        this(labelField, valueField, null);
    }

    public OptionsValueType(String labelField, String valueField, OptionDataType optionDataType) {
        this.labelField = labelField;
        this.valueField = valueField;
        this.dataType = DataType.OBJECT;
        this.properties = new ArrayList<>();
        if (optionDataType == OptionDataType.TEXT) {
            this.properties.add(new ObjectValueType.ObjectProperty(labelField, new TextValueType(false)));
            this.properties.add(new ObjectValueType.ObjectProperty(valueField, new TextValueType(false)));
        } else {
            this.properties.add(new ObjectValueType.ObjectProperty(labelField, new NumberValueType(false)));
            this.properties.add(new ObjectValueType.ObjectProperty(valueField, new NumberValueType(false)));
        }
    }

    @Override
    public DataType getDataType() {
        return this.dataType;
    }

    /**
     * 选择选项数据格式下的属性列表
     *
     * @return
     */
    public List<ObjectValueType.ObjectProperty> getProperties() {
        return properties;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
    }

    @Override
    protected Object getCorrectNonNullValue(Object value) {
        if (this.dataType == DataType.OBJECT) {
            if (value instanceof String) {
                throw new InvalidDataFormatException("冗余标签时必须以对象的形式传入lable和value，当前值{}" + value.toString() + "不合法");
            }
        }

        return value;
    }


    /**
     * 选项数据格式
     */
    enum OptionDataType {
        TEXT, /* 文本 */
        NUMBER /* 数字 */
    }

}
