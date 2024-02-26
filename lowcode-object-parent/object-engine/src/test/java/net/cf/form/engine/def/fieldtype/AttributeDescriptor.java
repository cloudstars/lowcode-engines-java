package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.object.DataType;

import java.util.List;

/**
 * 字段类型的个性化属性描述器
 *
 * @author clouds
 */
public class AttributeDescriptor {

    private String name;

    private String code;

    private boolean isCollection;

    private DataType dataType;

    private Object defaultValue;

    private List<Option> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    static class Option {
        String label;
        String value;

        public Option(String label, String value) {
            this.label = label;
            this.value = value;
        }
    }
}
