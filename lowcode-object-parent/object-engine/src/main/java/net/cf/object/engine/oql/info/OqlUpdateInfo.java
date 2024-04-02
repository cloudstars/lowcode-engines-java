package net.cf.object.engine.oql.info;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;

import java.util.Map;

public class OqlUpdateInfo {

    /**
     * 更新的模型
     */
    private XObject object;

    /**
     * 更新语句
     */
    private OqlUpdateStatement statement;

    /**
     * 更新语句对应的数据
     */
    private Map<String, Object> paramMap;


    public XObject getObject() {
        return object;
    }

    public void setObject(XObject object) {
        this.object = object;
    }

    public OqlUpdateStatement getStatement() {
        return statement;
    }

    public void setStatement(OqlUpdateStatement statement) {
        this.statement = statement;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
