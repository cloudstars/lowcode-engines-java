package net.cf.object.engine.oqlnew.info;

import java.util.List;

/**
 * OQL查询语句分解后的执令
 *
 * @author clouds
 */
public class OqlDeleteInfos extends AbstractOqlInfos {

    /**
     * 本表删除信息
     */
    private OqlDeleteInfo selfDeleteInfo;

    /**
     * 子表的删除信息
     */
    private List<OqlDeleteInfo> detailDeleteInfos;

    public OqlDeleteInfo getSelfDeleteInfo() {
        return selfDeleteInfo;
    }

    public void setSelfDeleteInfo(OqlDeleteInfo selfDeleteInfo) {
        this.selfDeleteInfo = selfDeleteInfo;
    }

    public List<OqlDeleteInfo> getDetailDeleteInfos() {
        return detailDeleteInfos;
    }

    public void setDetailDeleteInfos(List<OqlDeleteInfo> detailDeleteInfos) {
        this.detailDeleteInfos = detailDeleteInfos;
    }

}
