package net.cf.form.engine;

import net.cf.form.engine.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.oql.ast.statement.OqlUpdateStatement;

import java.util.List;
import java.util.Map;

/**
 * OQL数据服务接口
 *
 * @author clouds
 */
public interface ObjectQLEngine {


    /**
     * 创建记录
     *
     * @param statement
     * @return 影响行数
     */
    int create(OqlInsertStatement statement);

    /**
     * 创建记录
     *
     * @param statement
     * @param dataMap
     * @return
     */
    String create(OqlInsertStatement statement, Map<String, Object> dataMap);

    /**
     * 批量创建记录
     *
     * @param statement
     * @param dataMaps
     * @return
     */
    List<String> batchCreate(OqlInsertStatement statement, List<Map<String, Object>> dataMaps);

    /**
     * 更新记录
     *
     * @param statement
     * @param dataMap
     * @return
     */
    void modify(OqlUpdateStatement statement, Map<String, Object> dataMap);

    /**
     * 批量更新记录
     *
     * @param statement
     * @param dataMaps
     * @return
     */
    void batchModify(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps);

    /**
     * 移除记录
     *
     * @param statement
     * @param dataMap
     */
    void remove(OqlDeleteStatement statement, Map<String, Object> dataMap);

    /**
     * 批量移除记录
     *
     * @param statement
     * @param dataMaps
     */
    void batchRemove(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps);

    /**
     * 查询记录，限制返回一条
     *
     * @param statement
     * @param dataMap
     * @return
     */
    Map<String, Object> queryOne(OqlSelectStatement statement, Map<String, Object> dataMap);

    /**
     * 查询记录，不限制条件
     *
     * @param statement
     * @param dataMap
     * @return
     */
    List<Map<String, Object>> queryList(OqlSelectStatement statement, Map<String, Object> dataMap);
}
