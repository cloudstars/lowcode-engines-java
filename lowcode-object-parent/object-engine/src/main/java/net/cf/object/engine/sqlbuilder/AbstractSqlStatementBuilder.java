package net.cf.object.engine.sqlbuilder;

import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.object.engine.oql.ast.OqlStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * SQL语句构建器
 *
 * @param <O> OQL语句
 * @param <S> SQL语句
 */
public abstract class AbstractSqlStatementBuilder<O extends OqlStatement, S extends SqlStatement> {

    protected final Map<String, Object> paramMap = new HashMap<>();

    protected AbstractSqlStatementBuilder() {
    }

    /**
     * 添加一个SQL语句的参数
     *
     * @param key
     * @param value
     */
    protected void addParameter(String key, Object value) {
        this.paramMap.put(key, value);
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    /**
     * 根据OQL语句构建SQL语句
     *
     * @return
     */
    protected abstract S build();

}
