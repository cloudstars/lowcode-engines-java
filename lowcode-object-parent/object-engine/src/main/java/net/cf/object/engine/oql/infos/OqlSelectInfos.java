package net.cf.object.engine.oql.infos;

import net.cf.object.engine.sql.SqlSelectCmd;

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
    private SqlSelectCmd selfSelectInfo;

    /**
     * 子表的查询信息
     */
    private List<SqlSelectCmd> detailSelectInfos;

    /**
     * 相关表的查询信息
     */
    private List<SqlSelectCmd> lookupSelectInfos;

    public SqlSelectCmd getSelfSelectInfo() {
        return selfSelectInfo;
    }

    public void setSelfSelectInfo(SqlSelectCmd selfSelectInfo) {
        this.selfSelectInfo = selfSelectInfo;
    }

    public List<SqlSelectCmd> getDetailSelectInfos() {
        return detailSelectInfos;
    }

    public void setDetailSelectInfos(List<SqlSelectCmd> detailSelectInfos) {
        this.detailSelectInfos = detailSelectInfos;
    }

    public void addDetailSelectInfo(SqlSelectCmd detailSelectInfo) {
        if (this.detailSelectInfos == null) {
            this.detailSelectInfos = new ArrayList<>();
        }
        this.detailSelectInfos.add(detailSelectInfo);
    }

    public List<SqlSelectCmd> getLookupSelectInfos() {
        return lookupSelectInfos;
    }

    public void setLookupSelectInfos(List<SqlSelectCmd> lookupSelectInfos) {
        this.lookupSelectInfos = lookupSelectInfos;
    }

    public void addLookupSelectInfo(SqlSelectCmd lookupSelectInfo) {
        if (this.lookupSelectInfos == null) {
            this.lookupSelectInfos = new ArrayList<>();
        }
        this.lookupSelectInfos.add(lookupSelectInfo);
    }
}
