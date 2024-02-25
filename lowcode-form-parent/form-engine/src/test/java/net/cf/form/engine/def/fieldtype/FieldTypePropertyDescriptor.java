package net.cf.form.engine.def.fieldtype;

import net.cf.form.engine.object.DataType;

import java.util.List;

/**
 * 字段类型的子属性描述器
 *
 * @author clouds
 */
public class FieldTypePropertyDescriptor {

    private String name;

    private String code;

    private DataType dataType;

    private List<DescriptorOption> options;

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

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public List<DescriptorOption> getOptions() {
        return options;
    }

    public void setOptions(List<DescriptorOption> options) {
        this.options = options;
    }

}
