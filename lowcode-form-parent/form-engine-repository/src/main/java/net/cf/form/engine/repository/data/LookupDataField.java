package net.cf.form.engine.repository.data;

/**
 * 关联字段
 *
 */
@Deprecated
public class LookupDataField extends DataField implements ObjectJoinDataField {

    /**
     * 关联的对象的名称
     */
    private String lookupObjectName;

    public LookupDataField(String name, DataType dataType, String lookupObjectName) {
        super(name, dataType);
        this.lookupObjectName = lookupObjectName;
    }

    public LookupDataField(String name, String columnName, DataType dataType, String lookupObjectName) {
        super(name, columnName, dataType);
        this.lookupObjectName = lookupObjectName;
    }

    public String getLookupObjectName() {
        return lookupObjectName;
    }

    public void setLookupObjectName(String lookupObjectName) {
        this.lookupObjectName = lookupObjectName;
    }

    @Override
    public JoinType getJoinType() {
        return JoinType.LOOKUP;
    }

    @Override
    public String getJoinObjectName() {
        return lookupObjectName;
    }
}

