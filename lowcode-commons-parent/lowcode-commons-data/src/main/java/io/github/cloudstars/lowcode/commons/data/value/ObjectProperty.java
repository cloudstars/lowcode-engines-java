package io.github.cloudstars.lowcode.commons.data.value;

/**
 * 对象下的属性
 *
 * @author clouds
 *
 */
public class ObjectProperty {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性的数值类型
     */
    private ValueTypeConfig valueType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(ValueTypeConfig valueType) {
        this.valueType = valueType;
    }

}
