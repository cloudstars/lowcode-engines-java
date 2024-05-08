package net.cf.object.engine.oql.cmd;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.sql.SqlInsertCmd;

import java.util.HashMap;
import java.util.Map;

/**
 * OQL 插入语句解析后的信息
 *
 * @author clouds
 */
public class OqlInsertInfos extends AbstractOqlInfos {

    /**
     * OQL插入语句中可能有多个values，用于标识在每一个values对应的参数在values中出现的序号（从0开始），当insert返回多个values的自动生成主键时，可以依该序号填充对应的主键值
     */
    public static final String VALUES_INDEX = "__VALUES_INDEX__";

    /**
     * 本表的主键字段的值（可能是常量SqlValuableExpr，也可能是变量SqlVariableRefExpr)
     */
    private SqlExpr selfPrimaryFieldValue;

    /**
     * 主表插入指令
     */
    private SqlInsertCmd masterInsertCmd;

    /**
     * 子表的插入指令映射表
     */
    private Map<XObject, SqlInsertCmd> detailInsertCmds;

    public SqlExpr getSelfPrimaryFieldValue() {
        return selfPrimaryFieldValue;
    }

    public void setSelfPrimaryFieldValue(SqlExpr selfPrimaryFieldValue) {
        this.selfPrimaryFieldValue = selfPrimaryFieldValue;
    }

    public SqlInsertCmd getMasterInsertCmd() {
        return masterInsertCmd;
    }

    public void setMasterInsertCmd(SqlInsertCmd masterInsertCmd) {
        this.masterInsertCmd = masterInsertCmd;
    }

    public Map<XObject, SqlInsertCmd> getDetailInsertCmds() {
        return detailInsertCmds;
    }

    public void setDetailInsertCmds(Map<XObject, SqlInsertCmd> detailInsertCmds) {
        this.detailInsertCmds = detailInsertCmds;
    }

    public void addDetailInsertCmd(SqlInsertCmd detailInsertCmd) {
        if (this.detailInsertCmds == null) {
            this.detailInsertCmds = new HashMap<>();
        }

        this.detailInsertCmds.put(detailInsertCmd.getResolvedObject(), detailInsertCmd);
    }

}
