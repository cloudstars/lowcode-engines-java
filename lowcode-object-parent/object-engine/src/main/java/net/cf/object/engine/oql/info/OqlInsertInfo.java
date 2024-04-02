package net.cf.object.engine.oql.info;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;

import java.util.Map;

public class OqlInsertInfo {

    private XObject object;

    /**
     * 插入语句
     */
    private OqlInsertStatement statement;

    /**
     * 插入语句对应的参数
     */
    private Map<String, Object> paramMap;

    public XObject getObject() {
        return object;
    }

    public void setObject(XObject object) {
        this.object = object;
    }

    public OqlInsertStatement getStatement() {
        return statement;
    }

    public void setStatement(OqlInsertStatement statement) {
        this.statement = statement;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
