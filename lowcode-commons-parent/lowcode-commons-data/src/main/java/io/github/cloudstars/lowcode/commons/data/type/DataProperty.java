package io.github.cloudstars.lowcode.commons.data.type;

/**
 * 数据下的属性
 *
 * @author clouds
 */
public class DataProperty {

    /**
     * 属性的名称
     */
    private String name;

    /**
     * 属性的数据格式
     */
    private Class<DataType> dataTypeClass;


    public DataProperty() {
    }

    public DataProperty(String name, Class<DataType> dataTypeClass) {
        this.name = name;
        this.dataTypeClass = dataTypeClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<DataType> getDataTypeClass() {
        return dataTypeClass;
    }

    public void setDataTypeClass(Class<DataType> dataTypeClass) {
        this.dataTypeClass = dataTypeClass;
    }

}