package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.object.engine.data.FieldMapping;

import java.util.List;
import java.util.Map;

/**
 * SQL 插入指令
 *
 * @author clouds
 */
public class SqlInsertCmd extends AbstractSqlCmd<SqlInsertStatement> {

    /**
     * 单条执行时的参数
     */
    private Map<String, Object> paramMap;

    /**
     * 批量执行时的参数
     */
    private List<Map<String, Object>> paramMaps;

    /**
     * 字段映射表
     */
    private List<FieldMapping> fieldMappings;

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

    public List<FieldMapping> getFieldMappings() {
        return fieldMappings;
    }

    public void setFieldMappings(List<FieldMapping> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }

}
