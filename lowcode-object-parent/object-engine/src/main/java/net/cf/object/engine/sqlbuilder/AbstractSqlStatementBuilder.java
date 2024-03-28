package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.oql.ast.OqlStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * SQL语句构建器
 *
 * @param <O> OQL语句
 * @param <S> SQL语句
 */
public abstract class AbstractSqlStatementBuilder<O extends OqlStatement, S extends SqlStatement> {

    /**
     * 字段映射列表（支持套嵌字段，即展开字段）
     */
    protected final List<FieldMapping> fieldMappings = new ArrayList<>();

    protected AbstractSqlStatementBuilder() {
    }

    /**
     * 根据OQL语句构建SQL语句
     *
     * @return
     */
    protected abstract S build();

    public List<FieldMapping> getFieldMappings() {
        return fieldMappings;
    }

    /**
     * 添加字段映射
     *
     * @param fieldMapping
     */
    public void appendFieldMapping(FieldMapping fieldMapping) {
        this.fieldMappings.add(fieldMapping);
    }

}
