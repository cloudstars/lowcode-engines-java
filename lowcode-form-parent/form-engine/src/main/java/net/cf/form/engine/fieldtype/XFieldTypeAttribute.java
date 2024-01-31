package net.cf.form.engine.fieldtype;

import net.cf.form.engine.repository.data.DataType;

import java.util.List;

/**
 * 字段类型的属性
 *
 * @author clouds
 */
@Deprecated
public class XFieldTypeAttribute {

    /**
     * 属性的名称
     */
    private String name;

    /**
     * 属性的标题
     */
    private String title;

    /**
     * 属性的数据类型
     */
    private DataType dataType;

    /**
     * 默认值
     */
    private Object defaultValue;

    /**
     * 属性值的可选项
     */
    private List<Object> options;

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

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public List<Object> getOptions() {
        return options;
    }

    public void setOptions(List<Object> options) {
        this.options = options;
    }
}
