package net.cf.form.engine.repository;

import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;

import java.util.List;
import java.util.Map;

/**
 * OQL语句执行拦截器
 *
 * @author clouds
 */
@Deprecated
public interface StatementExecuteInterceptor {

    /**
     * 在插入语句执行前执行
     *
     * @param statement
     */
    void beforeInsert(OqlInsertStatement statement);

    /**
     * 在插入语句执行前执行（带参数的）
     * 
     * @param statement
     * @param paramMap
     */
    void beforeInsert(OqlInsertStatement statement, Map<String, Object> paramMap);


    /**
     * 在插入语句执行前执行（带参数的）
     *
     * @param statement
     * @param paramMaps
     */
    void beforeInsert(OqlInsertStatement statement, List<Map<String, Object>> paramMaps);

    /**
     * 在更新语句执行前执行
     *
     * @param statement
     */
    void beforeUpdate(OqlUpdateStatement statement);

    /**
     * 在更新语句执行前执行（带参数的）
     *
     * @param statement
     * @param paramMap
     */
    void beforeUpdate(OqlUpdateStatement statement, Map<String, Object> paramMap);

    /**
     * 在更新语句执行前执行（带参数的）
     *
     * @param statement
     * @param paramMapList
     */
    void beforeUpdate(OqlUpdateStatement statement, List<Map<String, Object>> paramMapList);

    /**
     * 在删除语句执行前执行
     *
     * @param statement
     */
    void beforeDelete(OqlDeleteStatement statement);

    /**
     * 在删除语句执行前执行（带变量的）
     *
     * @param statement
     * @param paramMap
     */
    void beforeDelete(OqlDeleteStatement statement, Map<String, Object> paramMap);
}
