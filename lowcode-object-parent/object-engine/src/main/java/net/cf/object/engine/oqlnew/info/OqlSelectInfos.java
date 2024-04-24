package net.cf.object.engine.oqlnew.info;

import java.util.ArrayList;
import java.util.List;

/**
 * OQL查询语句分解后的执令
 *
 * @author clouds
 */
public class OqlSelectInfos extends AbstractOqlInfos {

    /**
     * 本表查询信息
     */
    private OqlSelectInfo selfSelectInfo;

    /**
     * 子表的查询信息
     */
    private List<OqlSelectInfo> detailSelectInfos;

    /**
     * 相关表的查询信息
     */
    private List<OqlSelectInfo> lookupSelectInfos;

    public OqlSelectInfo getSelfSelectInfo() {
        return selfSelectInfo;
    }

    public void setSelfSelectInfo(OqlSelectInfo selfSelectInfo) {
        this.selfSelectInfo = selfSelectInfo;
    }

    public List<OqlSelectInfo> getDetailSelectInfos() {
        return detailSelectInfos;
    }

    public void setDetailSelectInfos(List<OqlSelectInfo> detailSelectInfos) {
        this.detailSelectInfos = detailSelectInfos;
    }

    public void addDetailSelectInfo(OqlSelectInfo detailSelectInfo) {
        if (this.detailSelectInfos == null) {
            this.detailSelectInfos = new ArrayList<>();
        }
        this.detailSelectInfos.add(detailSelectInfo);
    }

    public List<OqlSelectInfo> getLookupSelectInfos() {
        return lookupSelectInfos;
    }

    public void setLookupSelectInfos(List<OqlSelectInfo> lookupSelectInfos) {
        this.lookupSelectInfos = lookupSelectInfos;
    }

    public void addLookupSelectInfo(OqlSelectInfo lookupSelectInfo) {
        if (this.lookupSelectInfos == null) {
            this.lookupSelectInfos = new ArrayList<>();
        }
        this.lookupSelectInfos.add(lookupSelectInfo);
    }
}
