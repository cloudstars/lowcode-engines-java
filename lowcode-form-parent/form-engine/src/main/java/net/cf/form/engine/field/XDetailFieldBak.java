package net.cf.form.engine.field;

import net.cf.form.engine.fieldtype.BuiltinFieldTypes;

/**
 * 子对象中的主表字段（仅出现在子对象中）
 *
 * @author clouds
 */
@Deprecated
public class XDetailFieldBak extends XFieldImpl {

    /**
     * 子对象的名称
     */
    private static final String DON_KEY =  "detailObjectName";

    /**
     * 子模模的别名（用于OQL查询中展开查询时的名称)
     */
    private static final String DOA_KEY = "detailObjectAlias";

    public XDetailFieldBak(String name, String detailObjectName, String detailObjectAlias) {
        super(BuiltinFieldTypes.getDetailFieldType(), name);
        this.setAttributeValue(DON_KEY, detailObjectName);
        this.setAttributeValue(DOA_KEY, detailObjectAlias);
    }

    public XDetailFieldBak(String name, String detailObjectName) {
        super(BuiltinFieldTypes.getDetailFieldType(), name);
        this.setAttributeValue(DON_KEY, detailObjectName);
        this.setAttributeValue(DOA_KEY, detailObjectName);
    }

    public String getDetailObjectName() {
        return (String) this.getAttributeValue(DON_KEY);
    }

    public void setDetailObjectName(String detailObjectName) {
        this.setAttributeValue(DON_KEY, detailObjectName);
    }

    public String getDetailObjectAlias() {
        return (String) this.getAttributeValue(DOA_KEY);
    }

    public void setDetailObjectAlias(String detailObjectAlias) {
        this.setAttributeValue(DOA_KEY, detailObjectAlias);
    }
}