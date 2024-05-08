package net.cf.object.engine.oql.cmd;

import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.sql.AbstractSqlCmd;

import java.util.List;

/**
 * 模型的查询信息
 *
 * @author clouds
 */
public class OqlSelectInfo extends AbstractSqlCmd<SqlSelectStatement> {

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
}
