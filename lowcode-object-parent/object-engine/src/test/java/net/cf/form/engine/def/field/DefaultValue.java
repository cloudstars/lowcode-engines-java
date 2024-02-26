package net.cf.form.engine.def.field;

/**
 * 默认值配置
 *
 * @author clouds
 */
public class DefaultValue {

    /**
     * 默认值类型
     */
    private DefaultValueType type;

    /**
     * 具体的值
     */
    private String value;

    public DefaultValueType getType() {
        return type;
    }

    public void setType(DefaultValueType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
