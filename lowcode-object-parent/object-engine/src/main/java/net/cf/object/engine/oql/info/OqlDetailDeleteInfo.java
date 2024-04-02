package net.cf.object.engine.oql.info;

import net.cf.object.engine.oql.ast.OqlDeleteStatement;

import java.util.List;

/**
 * 子表更新明细
 *
 * @author clouds
 */
public class OqlDetailDeleteInfo extends AbstractOqlInfo {

    /**
     * 子表删除语句
     */
    private OqlDeleteStatement statement;

    /**
     * 需要保留的主键ID
     */
    private List<String> remainedRecordIds;

    public OqlDeleteStatement getStatement() {
        return statement;
    }

    public void setStatement(OqlDeleteStatement statement) {
        this.statement = statement;
    }

    public List<String> getRemainedRecordIds() {
        return remainedRecordIds;
    }

    public void setRemainedRecordIds(List<String> remainedRecordIds) {
        this.remainedRecordIds = remainedRecordIds;
    }
}
