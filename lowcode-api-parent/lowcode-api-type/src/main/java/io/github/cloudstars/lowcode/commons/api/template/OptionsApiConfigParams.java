package io.github.cloudstars.lowcode.commons.api.template;

/**
 * 选项列表API配置模板的参数
 *
 * @author clouds
 */
public class OptionsApiConfigParams {

    /**
     * 标签字段名称
     */
    private String labelField;

    /**
     * 值字段名称
     */
    private String valueField;

    /**
     * 选项值数据格式
     */
    private ValueDataTypeEnum valueDataType = ValueDataTypeEnum.STRING;

    public String getLabelField() {
        return labelField;
    }

    public void setLabelField(String labelField) {
        this.labelField = labelField;
    }

    public String getValueField() {
        return valueField;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public ValueDataTypeEnum getValueDataType() {
        return valueDataType;
    }

    public void setValueDataType(ValueDataTypeEnum valueDataType) {
        this.valueDataType = valueDataType;
    }

    /**
     * 选项值数据格式
     */
    public enum ValueDataTypeEnum {
        STRING,
        NUMBER;
    }

}
