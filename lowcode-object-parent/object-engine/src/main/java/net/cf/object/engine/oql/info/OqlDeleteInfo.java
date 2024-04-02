package net.cf.object.engine.oql.info;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;

import java.util.Map;

public class OqlDeleteInfo {

    /**
     * 更新的模型
     */
    private XObject object;

    /**
     * 删除语句
     */
    private OqlDeleteStatement statement;

    /**
     * 删除对应的数据
     */
    private Map<String, Object> paramMap;

    public XObject getObject() {
        return object;
    }

    public void setObject(XObject object) {
        this.object = object;
    }

    public OqlDeleteStatement getStatement() {
        return statement;
    }

    public void setStatement(OqlDeleteStatement statement) {
        this.statement = statement;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
