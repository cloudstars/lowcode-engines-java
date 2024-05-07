package net.cf.object.engine;

import net.cf.object.engine.data.PageRequest;
import net.cf.object.engine.data.PageResult;
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
public interface OqlEngineNew {

    /**
     * 查询记录，限制返回一条
     *
     * @param stmt
     * @return
     */
    Map<String, Object> queryOne(OqlSelectStatement stmt);

    /**
     * 查询记录，限制返回一条
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    Map<String, Object> queryOne(OqlSelectStatement stmt, Map<String, Object> paramMap);

    /**
     * 查询记录列表
     *
     * @param stmt
     * @return
     */
    List<Map<String, Object>> queryList(OqlSelectStatement stmt);

    /**
     * 查询记录列表
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> queryList(OqlSelectStatement stmt, Map<String, Object> paramMap);

    /**
     * 分页查询
     *
     * @param stmt
     * @param paramMap
     * @param pageRequest
     * @return
     */
    PageResult<Map<String, Object>> queryPage(OqlSelectStatement stmt, Map<String, Object> paramMap, PageRequest pageRequest);

    /**
     * 创建记录
     *
     * @param stmt
     * @return 影响行数
     */
    int create(OqlInsertStatement stmt);

    /**
     * 创建记录
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    int create(OqlInsertStatement stmt, Map<String, Object> paramMap);

    /**
     * 批量创建记录
     *
     * @param stmt
     * @param paramMaps
     * @return 影响行数
     */
    int[] createList(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps);

    /**
     * 更新记录
     *
     * @param stmt
     * @return 影响行数
     */
    int modify(OqlUpdateStatement stmt);

    /**
     * 更新记录
     *
     * @param stmt
     * @param paramMap
     * @return 影响行数
     */
    int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap);

    /**
     * 批量更新记录
     *
     * @param stmt
     * @param paramMaps
     * @return 影响行数
     */
    int[] modifyList(OqlUpdateStatement stmt, List<Map<String, Object>> paramMaps);

    /**
     * 移除记录
     *
     * @param stmt
     * @return 影响行数
     */
    int remove(OqlDeleteStatement stmt);

    /**
     * 移除记录
     *
     * @param stmt
     * @param paramMap
     * @return 影响行数
     */
    int remove(OqlDeleteStatement stmt, Map<String, Object> paramMap);

    /**
     * 批量移除记录
     *
     * @param stmt
     * @param paramMaps
     * @return 影响行数
     */
    int[] removeList(OqlDeleteStatement stmt, List<Map<String, Object>> paramMaps);

}
