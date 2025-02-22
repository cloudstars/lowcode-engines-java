package net.cf.object.engine.fieldtype;

/**
 * 字段的个性化属性
 *
 * @author clouds
 */
public class FieldAttribute {

    private String desc;

    private String name;

    private Object value;

    /**
     * 获取配置的名称
     *
     * @return
     */
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取配置的名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取个性化属性的值
     *
     * @return
     */
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
