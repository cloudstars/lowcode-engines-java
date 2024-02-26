package net.cf.object.engine.record.bean;

/**
 * 出差申请单
 *
 * @author clouds
 */
public class TravelApply {

    /**
     * 申请单编号
     */
    private String applyId;

    /**
     * 申请单名称
     */
    private String applyName;

    /**
     * 企业编号
     */
    private String enterpriseKey;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getEnterpriseKey() {
        return enterpriseKey;
    }

    public void setEnterpriseKey(String enterpriseKey) {
        this.enterpriseKey = enterpriseKey;
    }

}
