package io.github.cloudstars.lowcode.commons.data.type.custom;

/**
 * 选项格式的数据
 *
 * @author clouds
 */
public class OptionValue {

    private String value;

    private String label;

    private String color;

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
}
