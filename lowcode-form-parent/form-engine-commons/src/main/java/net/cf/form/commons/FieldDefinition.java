package net.cf.form.commons;

import java.util.List;

/**
 * 字段定义
 *
 * @author clouds
 */
public class FieldDefinition {

    /**
     * 类型
     */
    private String type;

    /**
     * 名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否集合
     */
    private boolean isCollection;

    /**
     * 数据类型
     */
    private DataType dataType;

    /**
     * 默认值配置
     */
    private DefaultValue defaultValue;

    /**
     * 字段的子属性
     */
    private List<FieldProperty> properties;

    public FieldDefinition(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public DefaultValue getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(DefaultValue defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<FieldProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<FieldProperty> properties) {
        this.properties = properties;
    }
}
