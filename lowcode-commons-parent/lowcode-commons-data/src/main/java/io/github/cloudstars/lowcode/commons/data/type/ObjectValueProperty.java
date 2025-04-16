package io.github.cloudstars.lowcode.commons.data.type;

/**
 * 对象数据下的属性
 *
 * @author clouds
 *
 */
public class ObjectValueProperty {

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性的数据类型
     */
    private ValueTypeConfig valueType;

    public ObjectValueProperty() {
    }

    public ObjectValueProperty(String name, ValueTypeConfig valueType) {
        this.name = name;
        this.valueType = valueType;
    }

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
