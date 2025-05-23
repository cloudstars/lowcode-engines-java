package net.cf.object.engine.oql.infos;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.sql.SqlDeleteCmd;

import java.util.HashMap;
import java.util.Map;

/**
 * OQL查询语句分解后的执令
 *
 * @author clouds
 */
public class OqlDeleteInfos extends AbstractOqlInfos {

    /**
     * 主表删除批令
     */
    private SqlDeleteCmd masterDeleteCmd;

    /**
     * 子表的删除指令映射表
     */
    private Map<XObject, SqlDeleteCmd> detailDeleteCmds;

    public SqlDeleteCmd getMasterDeleteCmd() {
        return masterDeleteCmd;
    }

    public void setMasterDeleteCmd(SqlDeleteCmd masterDeleteCmd) {
        this.masterDeleteCmd = masterDeleteCmd;
    }

    public Map<XObject, SqlDeleteCmd> getDetailDeleteCmds() {
        return detailDeleteCmds;
    }

    public void setDetailDeleteCmds(Map<XObject, SqlDeleteCmd> detailDeleteCmds) {
        this.detailDeleteCmds = detailDeleteCmds;
    }

    public void addDetailDeleteCmd(XObject detailObject, SqlDeleteCmd updateCmd) {
        if (this.detailDeleteCmds == null) {
            this.detailDeleteCmds = new HashMap<>();
        }
        this.detailDeleteCmds.put(detailObject, updateCmd);
    }

}
