package net.cf.object.engine.sql;

import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.data.FieldMapping;

import java.util.List;
import java.util.Map;

/**
 * 模型的查询信息
 *
 * @author clouds
 */
public class SqlSelectCmd extends AbstractSqlCmd<SqlSelectStatement> {

    /**
     * 查询参数
     */
    private Map<String, Object> paramMap;

    /**
     * 查询的字段映射关系
     */
    private List<FieldMapping> fieldMappings;

    /**
     * 模型引用字段的字段名
     */
    private String objectRefFieldName;

    /**
     * 模型引用字段的字段名
     */
    private String objectRefFieldAlias;

    /**
     * 是否是直接查询了子表的字段，如：select detailField from ...，并且不含子表的其它字段查询
     */
    private boolean isDetailFieldDirectQuery;

    /**
     * 可以直接返回的结果，如select [{}, {}] as detailRefField from masterObject where ...，其中[{}, {}]是可以直接作为子表返回的
     */
    private Map<String, Object> directResultMap;

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public List<FieldMapping> getFieldMappings() {
        return fieldMappings;
    }

    public void setFieldMappings(List<FieldMapping> fieldMappings) {
        this.fieldMappings = fieldMappings;
    }

    public String getObjectRefFieldName() {
        return objectRefFieldName;
    }

    public void setObjectRefFieldName(String objectRefFieldName) {
        this.objectRefFieldName = objectRefFieldName;
    }

    public String getObjectRefFieldAlias() {
        return objectRefFieldAlias;
    }

    public void setObjectRefFieldAlias(String objectRefFieldAlias) {
        this.objectRefFieldAlias = objectRefFieldAlias;
    }

    public String getObjectRefResultKey() {
        return this.objectRefFieldAlias != null ? this.objectRefFieldAlias : this.objectRefFieldName;
    }

    public boolean isDetailFieldDirectQuery() {
        return isDetailFieldDirectQuery;
    }

    public void setDetailFieldDirectQuery(boolean detailFieldDirectQuery) {
        isDetailFieldDirectQuery = detailFieldDirectQuery;
    }

    public Map<String, Object> getDirectResultMap() {
        return directResultMap;
    }

    public void setDirectResultMap(Map<String, Object> directResultMap) {
        this.directResultMap = directResultMap;
    }
}
