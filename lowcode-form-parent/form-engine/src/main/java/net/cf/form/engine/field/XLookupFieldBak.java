package net.cf.form.engine.field;

import net.cf.form.engine.fieldtype.BuiltinFieldTypes;

/**
 * 关相对象字段
 *
 * @author clouds
 */
@Deprecated
public class XLookupFieldBak extends XFieldImpl {

    /**
     * 关联对象的名称
     */
    private static final String LOOKUP_OBJECT_NAME = "lookupObjectName";


    public XLookupFieldBak(String name, String lookupObjectName) {
        super(BuiltinFieldTypes.getLookupFieldType(), name);
        this.setAttributeValue(LOOKUP_OBJECT_NAME, lookupObjectName);
    }

    public String getLookupObjectName() {
        return (String) this.getAttributeValue(LOOKUP_OBJECT_NAME);
    }
}
