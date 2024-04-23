package net.cf.object.engine;

import net.cf.object.engine.data.PageRequest;
import net.cf.object.engine.data.PageResult;

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
     * @param oql
     * @return
     */
    Map<String, Object> queryOne(String oql);

    /**
     * 查询记录，限制返回一条
     *
     * @param oql
     * @param paramMap
     * @return
     */
    Map<String, Object> queryOne(String oql, Map<String, Object> paramMap);

    /**
     * 查询记录列表
     *
     * @param oql
     * @return
     */
    List<Map<String, Object>> queryList(String oql);

    /**
     * 查询记录列表
     *
     * @param oql
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> queryList(String oql, Map<String, Object> paramMap);

    /**
     * 分页查询
     *
     * @param oql
     * @param paramMap
     * @param pageRequest
     * @return
     */
    PageResult<Map<String, Object>> queryPage(String oql, Map<String, Object> paramMap, PageRequest pageRequest);

    /**
     * 创建记录
     *
     * @param oql
     * @return 影响行数
     */
    int create(String oql);

    /**
     * 创建记录
     *
     * @param oql
     * @param paramMap
     * @return
     */
    int create(String oql, Map<String, Object> paramMap);

    /**
     * 批量创建记录
     *
     * @param oql
     * @param dataMaps
     * @return 影响行数
     */
    int[] createList(String oql, List<Map<String, Object>> dataMaps);

    /**
     * 更新记录
     *
     * @param oql
     * @return 影响行数
     */
    int modify(String oql);

    /**
     * 更新记录
     *
     * @param oql
     * @param paramMap
     * @return 影响行数
     */
    int modify(String oql, Map<String, Object> paramMap);

    /**
     * 批量更新记录
     *
     * @param oql
     * @param dataMaps
     * @return 影响行数
     */
    int[] modifyList(String oql, List<Map<String, Object>> dataMaps);

    /**
     * 移除记录
     *
     * @param oql
     * @return 影响行数
     */
    int remove(String oql);

    /**
     * 移除记录
     *
     * @param oql
     * @param paramMap
     * @return 影响行数
     */
    int remove(String oql, Map<String, Object> paramMap);

    /**
     * 批量移除记录
     *
     * @param oql
     * @param dataMaps
     * @return 影响行数
     */
    int[] removeList(String oql, List<Map<String, Object>> dataMaps);

}
