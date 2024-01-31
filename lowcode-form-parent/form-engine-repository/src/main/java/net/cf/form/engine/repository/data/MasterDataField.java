package net.cf.form.engine.repository.data;

/**
 * 子对象中的主对象字段（仅在子对象中存在）
 *
 * @author clouds
 *
 */
@Deprecated
public class MasterDataField extends DataField implements ObjectJoinDataField {

    private String masterObjectName;

    public MasterDataField(String name, DataType dataType, String masterObjectName) {
        this(name, name, dataType, masterObjectName);
    }

    public MasterDataField(String name, String columnName, DataType dataType, String masterObjectName) {
        super(name, columnName, dataType);
        this.masterObjectName = masterObjectName;
    }

    public String getMasterObjectName() {
        return masterObjectName;
    }

    public void setMasterObjectName(String masterObjectName) {
        this.masterObjectName = masterObjectName;
    }


    @Override
    public JoinType getJoinType() {
        return JoinType.MASTER;
    }

    @Override
    public String getJoinObjectName() {
        return masterObjectName;
    }
}

