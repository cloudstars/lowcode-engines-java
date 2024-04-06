package net.cf.object.engine.oql.info;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;

import java.util.Map;

/**
 * 子表查询明细
 *
 * @author clouds
 */
public class OqlSelectInfo {

    /**
     * 模型的名称
     */
    private XObject object;

    /**
     * 查询语句
     */
    private OqlSelectStatement statement;

    /**
     * 查询语句对应的参数
     */
    private Map<String, Object> paramMap;

    /**
     * 是否子表默认展开的查询
     */
    private boolean isDetailDefaultExpandQuery;

    public XObject getObject() {
        return object;
    }

    public void setObject(XObject object) {
        this.object = object;
    }

    public OqlSelectStatement getStatement() {
        return statement;
    }

    public void setStatement(OqlSelectStatement statement) {
        this.statement = statement;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public boolean isDetailDefaultExpandQuery() {
        return isDetailDefaultExpandQuery;
    }

    public void setDetailDefaultExpandQuery(boolean detailDefaultExpandQuery) {
        isDetailDefaultExpandQuery = detailDefaultExpandQuery;
    }

}
