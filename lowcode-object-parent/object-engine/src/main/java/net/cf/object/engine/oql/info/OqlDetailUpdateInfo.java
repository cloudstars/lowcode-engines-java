package net.cf.object.engine.oql.info;

import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;

import java.util.List;
import java.util.Map;

/**
 * 子表更新明细
 *
 * @author clouds
 */
public class OqlDetailUpdateInfo extends AbstractOqlInfo {

    /**
     * 子表更新时新数据的插入语句
     */
    private OqlInsertStatement insertStatement;

    /**
     * 插入语句对应的数据
     */
    private List<Map<String, Object>> insertParamMaps;

    /**
     * 子表更新语句
     */
    private OqlUpdateStatement statement;

    /**
     * 更新语句对应的参数列表
     */
    private List<Map<String, Object>> paramMaps;

    /**
     * 子表更新时删除的数据的删除语句
     */
    private OqlDeleteStatement deleteStatement;

    /**
     * 输入的数据中的记录ID（引擎根据数据库中存在的记录ID，和这个输入的记录ID作对比来删除数据）
     */
    private List<String> inputRecordIds;

    /*public OqlInsertStatement getInsertStatement() {
        return insertStatement;
    }

    public void setInsertStatement(OqlInsertStatement insertStatement) {
        this.insertStatement = insertStatement;
    }

    public List<Map<String, Object>> getInsertParamMaps() {
        return insertParamMaps;
    }

    public void setInsertParamMaps(List<Map<String, Object>> insertParamMaps) {
        this.insertParamMaps = insertParamMaps;
    }*/

    public OqlUpdateStatement getStatement() {
        return statement;
    }

    public void setStatement(OqlUpdateStatement statement) {
        this.statement = statement;
    }

    public List<Map<String, Object>> getParamMaps() {
        return paramMaps;
    }

    public void setParamMaps(List<Map<String, Object>> paramMaps) {
        this.paramMaps = paramMaps;
    }

    /*public OqlDeleteStatement getDeleteStatement() {
        return deleteStatement;
    }

    public void setDeleteStatement(OqlDeleteStatement deleteStatement) {
        this.deleteStatement = deleteStatement;
    }

    public List<String> getInputRecordIds() {
        return inputRecordIds;
    }

    public void setInputRecordIds(List<String> inputRecordIds) {
        this.inputRecordIds = inputRecordIds;
    }*/

}
