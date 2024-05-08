package net.cf.object.engine.oql.cmd;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.sql.SqlDeleteCmd;
import net.cf.object.engine.sql.SqlInsertCmd;
import net.cf.object.engine.sql.SqlUpdateCmd;

import java.util.HashMap;
import java.util.Map;

/**
 * OQL更新语句解析后的信息
 *
 * @author clouds
 */
public class OqlUpdateInfos extends AbstractOqlInfos {

    /**
     * 本表更新指令
     */
    private SqlUpdateCmd masterUpdateCmd;

    /**
     * 子表中需要执行删除的数据的指令映射
     */
    private Map<XObject, SqlDeleteCmd> detailDeleteCmds;


    /**
     * 子表中需要执行插入的数据的指令映射
     */
    private Map<XObject, SqlInsertCmd> detailInsertCmds;


    /**
     * 子表中需要执行更新的数据的指令映射
     */
    private Map<XObject, SqlUpdateCmd> detailUpdateCmds;

    public SqlUpdateCmd getMasterUpdateCmd() {
        return masterUpdateCmd;
    }

    public void setMasterUpdateCmd(SqlUpdateCmd masterUpdateCmd) {
        this.masterUpdateCmd = masterUpdateCmd;
    }

    public Map<XObject, SqlDeleteCmd> getDetailDeleteCmds() {
        return detailDeleteCmds;
    }

    public void setDetailDeleteCmds(Map<XObject, SqlDeleteCmd> detailDeleteCmds) {
        this.detailDeleteCmds = detailDeleteCmds;
    }

    public void addDetailDeleteCmd(XObject detailObject, SqlDeleteCmd deleteCmd) {
        if (this.detailDeleteCmds == null) {
            this.detailDeleteCmds = new HashMap<>();
        }
        this.detailDeleteCmds.put(detailObject, deleteCmd);
    }

    public Map<XObject, SqlInsertCmd> getDetailInsertCmds() {
        return detailInsertCmds;
    }

    public void setDetailInsertCmds(Map<XObject, SqlInsertCmd> detailInsertCmds) {
        this.detailInsertCmds = detailInsertCmds;
    }

    public void addDetailInsertCmd(XObject detailObject, SqlInsertCmd insertCmd) {
        if (this.detailInsertCmds == null) {
            this.detailInsertCmds = new HashMap<>();
        }
        this.detailInsertCmds.put(detailObject, insertCmd);
    }

    public Map<XObject, SqlUpdateCmd> getDetailUpdateCmds() {
        return detailUpdateCmds;
    }

    public void setDetailUpdateCmds(Map<XObject, SqlUpdateCmd> detailUpdateCmds) {
        this.detailUpdateCmds = detailUpdateCmds;
    }

    public void addDetailUpdateCmd(XObject detailObject, SqlUpdateCmd updateCmd) {
        if (this.detailUpdateCmds == null) {
            this.detailUpdateCmds = new HashMap<>();
        }
        this.detailUpdateCmds.put(detailObject, updateCmd);
    }

}
