package net.cf.object.engine;

import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;

import java.util.List;
import java.util.Map;

/**
 * OQL数据服务接口
 *
 * @author clouds
 */
public interface OqlEngine {

    /**
     * 查询记录，限制返回一条
     *
     * @param statement
     * @return
     */
    Map<String, Object> queryOne(OqlSelectStatement statement);

    /**
     * 查询记录，限制返回一条
     *
     * @param statement
     * @param paramMap
     * @return
     */
    Map<String, Object> queryOne(OqlSelectStatement statement, Map<String, Object> paramMap);

    /**
     * 查询记录列表
     *
     * @param statement
     * @return
     */
    List<Map<String, Object>> queryList(OqlSelectStatement statement);

    /**
     * 查询记录列表
     *
     * @param statement
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> queryList(OqlSelectStatement statement, Map<String, Object> paramMap);

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
     * @param paramMap
     * @return
     */
    int create(OqlInsertStatement statement, Map<String, Object> paramMap);

    /**
     * 批量创建记录
     *
     * @param statement
     * @param dataMaps
     * @return 影响行数
     */
    int[] createList(OqlInsertStatement statement, List<Map<String, Object>> dataMaps);

    /**
     * 更新记录
     *
     * @param statement
     * @return 影响行数
     */
    int modify(OqlUpdateStatement statement);

    /**
     * 更新记录
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int modify(OqlUpdateStatement statement, Map<String, Object> paramMap);

    /**
     * 批量更新记录
     *
     * @param statement
     * @param dataMaps
     * @return 影响行数
     */
    int[] modifyList(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps);

    /**
     * 移除记录
     *
     * @param statement
     * @return 影响行数
     */
    int remove(OqlDeleteStatement statement);

    /**
     * 移除记录
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int remove(OqlDeleteStatement statement, Map<String, Object> paramMap);

    /**
     * 批量移除记录
     *
     * @param statement
     * @param dataMaps
     * @return 影响行数
     */
    int[] removeList(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps);

}
