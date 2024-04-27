package net.cf.object.engine.oqlnew.info;

import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;

import java.util.List;
import java.util.Map;

/**
 * 模型的插入信息
 *
 * @author clouds
 */
public class OqlInsertInfo extends AbstractOqlInfo<SqlInsertStatement> {

    /**
     * 是否批量执行
     */
    private boolean isBatch;

    /**
     * 单条执行时的参数
     */
    private Map<String, Object> paramMap;

    /**
     * 批量执行时的参数
     */
    private List<Map<String, Object>> paramMaps;

    public boolean isBatch() {
        return isBatch;
    }

    public void setBatch(boolean batch) {
        isBatch = batch;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public List<Map<String, Object>> getParamMaps() {
        return paramMaps;
    }

    public void setParamMaps(List<Map<String, Object>> paramMaps) {
        this.paramMaps = paramMaps;
    }
}
