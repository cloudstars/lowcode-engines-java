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
     * 本表查询指令
     */
    private SqlSelectCmd masterSelectCmd;

    /**
     * 子表的查询信息（存在子表时，需要在子表的OQL语句上加上主表记录ID字段的查询）
     */
    private List<SqlSelectCmd> detailSelectCmds;

    /**
     * 相关表的查询指令（存在相关表时，需要在主表的OQL语句上加上相关表字段的查询）
     */
    private List<SqlSelectCmd> lookupSelectCmds;

    public SqlSelectCmd getMasterSelectCmd() {
        return masterSelectCmd;
    }

    public void setMasterSelectCmd(SqlSelectCmd masterSelectCmd) {
        this.masterSelectCmd = masterSelectCmd;
    }

    public List<SqlSelectCmd> getDetailSelectCmds() {
        return detailSelectCmds;
    }

    public void setDetailSelectCmds(List<SqlSelectCmd> detailSelectCmds) {
        this.detailSelectCmds = detailSelectCmds;
    }

    public void addDetailSelectInfo(SqlSelectCmd detailSelectInfo) {
        if (this.detailSelectCmds == null) {
            this.detailSelectCmds = new ArrayList<>();
        }
        this.detailSelectCmds.add(detailSelectInfo);
    }

    public List<SqlSelectCmd> getLookupSelectCmds() {
        return lookupSelectCmds;
    }

    public void setLookupSelectCmds(List<SqlSelectCmd> lookupSelectCmds) {
        this.lookupSelectCmds = lookupSelectCmds;
    }

    public void addLookupSelectCmd(SqlSelectCmd lookupSelectInfo) {
        if (this.lookupSelectCmds == null) {
            this.lookupSelectCmds = new ArrayList<>();
        }
        this.lookupSelectCmds.add(lookupSelectInfo);
    }
}
