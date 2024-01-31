package net.cf.form.engine.repository.data;

/**
 * 主对象中的子对象字段（仅在主对象中存在）
 *
 * @author clouds
 *
 */
@Deprecated
public class DetailDataField extends DataField implements ObjectJoinDataField {

    private String detailObjectName;

    public DetailDataField(String name, String detailObjectName) {
        super(name, null, null);
        this.detailObjectName = detailObjectName;
    }

    public String getDetailObjectName() {
        return detailObjectName;
    }

    public void setDetailObjectName(String detailObjectName) {
        this.detailObjectName = detailObjectName;
    }

    @Override
    public JoinType getJoinType() {
        return JoinType.DETAIL;
    }

    @Override
    public String getJoinObjectName() {
        return this.detailObjectName;
    }
}

