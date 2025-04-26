package io.github.cloudstars.lowcode.commons.value.type;

/**
 * 选项格式的数据
 *
 * @author clouds
 */
public class OptionValue implements XObjectValue {

    /**
     * 选项值
     */
    private String value;

    /**
     * 选项标签
     */
    private String label;

    /**
     * 选项颜色
     */
    private String color;

    /**
     * 禁用条件
     */
    private String disabledOn;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDisabledOn() {
        return disabledOn;
    }

    public void setDisabledOn(String disabledOn) {
        this.disabledOn = disabledOn;
    }
}
