package net.cf.object.engine.oqlnew.info;

import java.util.List;

/**
 * OQL查询语句分解后的执令
 *
 * @author clouds
 */
public class OqlInsertInfos extends AbstractOqlInfos {

    /**
     * 本表插入信息
     */
    private OqlInsertInfo selfInsertInfo;

    /**
     * 子表的插入信息
     */
    private List<OqlInsertInfo> detailInsertInfos;

    public OqlInsertInfo getSelfInsertInfo() {
        return selfInsertInfo;
    }

    public void setSelfInsertInfo(OqlInsertInfo selfInsertInfo) {
        this.selfInsertInfo = selfInsertInfo;
    }

    public List<OqlInsertInfo> getDetailInsertInfos() {
        return detailInsertInfos;
    }

    public void setDetailInsertInfos(List<OqlInsertInfo> detailInsertInfos) {
        this.detailInsertInfos = detailInsertInfos;
    }

}
