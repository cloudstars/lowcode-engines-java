package net.cf.object.engine.oqlnew.info;

import java.util.List;

/**
 * OQL查询语句分解后的执令
 *
 * @author clouds
 */
public class OqlUpdateInfos extends AbstractOqlInfos {

    /**
     * 本表更新信息
     */
    private OqlUpdateInfo selfUpdateInfo;

    /**
     * 子表的插入信息
     */
    private List<OqlUpdateInfo> detailUpdateInfos;

    public OqlUpdateInfo getSelfUpdateInfo() {
        return selfUpdateInfo;
    }

    public void setSelfUpdateInfo(OqlUpdateInfo selfUpdateInfo) {
        this.selfUpdateInfo = selfUpdateInfo;
    }

    public List<OqlUpdateInfo> getDetailUpdateInfos() {
        return detailUpdateInfos;
    }

    public void setDetailUpdateInfos(List<OqlUpdateInfo> detailUpdateInfos) {
        this.detailUpdateInfos = detailUpdateInfos;
    }

}
