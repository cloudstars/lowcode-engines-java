package net.cf.form.engine.def.field;

import net.cf.form.engine.def.fieldtype.FieldTypeAttributeDescriptor;
import net.cf.form.engine.object.XFieldAttribute;

/**
 * 字段的个性化属性
 *
 * @author clouds
 */
public class FieldAttributeTestImpl implements XFieldAttribute {

    /**
     * 个性化属性归属的字段
     */
    private final FieldTestImpl owner;

    /**
     * 个性化属性的属性描述器
     */
    private final FieldTypeAttributeDescriptor attributeDescriptor;

    private String name;

    private String code;

    private Object value;

    public FieldAttributeTestImpl(FieldTestImpl field, FieldTypeAttributeDescriptor attributeDescriptor) {
        this.owner = field;
        this.attributeDescriptor = attributeDescriptor;
    }

    @Override
    public FieldTestImpl getOwner() {
        return this.owner;
    }

    public FieldTypeAttributeDescriptor getAttributeDescriptor() {
        return attributeDescriptor;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
