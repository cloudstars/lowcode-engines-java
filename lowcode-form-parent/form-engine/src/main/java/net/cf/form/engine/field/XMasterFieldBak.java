package net.cf.form.engine.field;

import net.cf.form.engine.fieldtype.BuiltinFieldTypes;

/**
 * 子对象中的主表字段（仅出现在子对象中）
 *
 * @author clouds
 */
@Deprecated
public class XMasterFieldBak extends XFieldImpl {

    /**
     * 主对象的名称
     */
    private static final String MASTER_OBJECT_NAME = "masterObjectName";

    public XMasterFieldBak(String name, String masterObjectName) {
        super(BuiltinFieldTypes.getMasterFieldType(), name);
        this.setAttributeValue(MASTER_OBJECT_NAME, masterObjectName);
    }

    public String getMasterObjectName() {
        return (String) this.getAttributeValue(MASTER_OBJECT_NAME);
    }

    public void setMasterObjectName(String masterObjectName) {
        this.setAttributeValue(MASTER_OBJECT_NAME, masterObjectName);
    }
}