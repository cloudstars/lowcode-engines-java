package net.cf.form.engine.def.field;

import net.cf.form.engine.def.fieldtype.AttributeDescriptor;

/**
 * 字段的个性化属性
 *
 * @author clouds
 */
public class FieldAttribute {

    /**
     * 个性化属性归属的字段
     */
    private final FieldTestImpl owner;

    /**
     * 个性化属性的属性描述器
     */
    private final AttributeDescriptor descriptor;

    private String name;

    private String code;

    private Object value;

    public FieldAttribute(FieldTestImpl field, AttributeDescriptor descriptor) {
        this.owner = field;
        this.descriptor = descriptor;
    }

    /**
     * 获取个性化属性归属的字段
     *
     * @return
     */
    public FieldTestImpl getOwner() {
        return this.owner;
    }

    /**
     * 获取个性化属性的描述器
     *
     * @return
     */
    public AttributeDescriptor getDescriptor() {
        return descriptor;
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
     * 获取配置的编号
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
