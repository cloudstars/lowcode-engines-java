package net.cf.object.engine.oql.info;

import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlInsertStatement;

import java.util.List;
import java.util.Map;

/**
 * 子表插入明细
 *
 * @author clouds
 */
public class OqlDetailInsertInfo extends AbstractOqlInfo {

    /**
     * 子表模型
     */
    private XObject object;

    /**
     * 子表的插入语句
     */
    private OqlInsertStatement statement;

    /**
     * 子表的插入参数
     */
    private List<Map<String, Object>> paramMaps;

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

    public List<Map<String, Object>> getParamMaps() {
        return paramMaps;
    }

    public void setParamMaps(List<Map<String, Object>> paramMaps) {
        this.paramMaps = paramMaps;
    }

}
