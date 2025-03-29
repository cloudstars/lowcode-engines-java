package io.github.cloudstars.lowcode.commons.data.type;

/**
 * 对象数据下的属性
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
     * 属性的数据类型
     */
    private DataTypeConfig dataType;

    public ObjectProperty() {
    }

    public ObjectProperty(String name, DataTypeConfig dataType) {
        this.name = name;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataTypeConfig getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeConfig dataType) {
        this.dataType = dataType;
    }

}
