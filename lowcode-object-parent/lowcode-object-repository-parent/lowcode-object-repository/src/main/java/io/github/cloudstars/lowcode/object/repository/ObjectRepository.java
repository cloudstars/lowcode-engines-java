package io.github.cloudstars.lowcode.object.repository;

import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlDeleteStatement;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlInsertStatement;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlSelectStatement;
import io.github.cloudstars.lowcode.object.repository.sql.ast.statement.SqlUpdateStatement;

import java.util.List;
import java.util.Map;

/**
 * 模型数据存储接口
 *
 * @author clouds
 */
public interface ObjectRepository {

    /**
     * 查询单条数据
     *
     * @param stmt
     * @return
     */
    Map<String, Object> selectOne(SqlSelectStatement stmt);

    /**
     * 查询单条数据，并携带参数
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    Map<String, Object> selectOne(SqlSelectStatement stmt, Map<String, Object> paramMap);

    /**
     * 查询列表数据
     *
     * @param stmt
     * @return
     */
    List<Map<String, Object>> selectList(SqlSelectStatement stmt);

    /**
     * 查询列表数据，并携带参数
     *
     * @param stmt
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> selectList(SqlSelectStatement stmt, Map<String, Object> paramMap);

    /**
     * 插入记录
     *
     * @param stmt
     * @return 影响行数
     */
    int insert(SqlInsertStatement stmt);

    /**
     * 插入一条记录，并携带参数
     *
     * @param stmt
     * @param paramMap
     * @return 影响行数
     */
    int insert(SqlInsertStatement stmt, Map<String, Object> paramMap);

    /**
     * 批量插入记录，并携带参数
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    int[] batchInsert(SqlInsertStatement stmt, List<Map<String, Object>> paramMaps);

    /**
     * 更新一条数据，并携带参数
     *
     * @param stmt
     * @return 影响行数
     */
    int update(SqlUpdateStatement stmt);

    /**
     * 更新一条数据
     *
     * @param stmt
     * @param paramMap
     * @return 影响行数
     */
    int update(SqlUpdateStatement stmt, Map<String, Object> paramMap);

    /**
     * 批量更新记录，并携带参数
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    int[] batchUpdate(SqlUpdateStatement stmt, List<Map<String, Object>> paramMaps);

    /**
     * 删除一条数据
     *
     * @param stmt
     * @return 影响行数
     */
    int delete(SqlDeleteStatement stmt);

    /**
     * 删除一条数据，并携带参数
     *
     * @param stmt
     * @param paramMap
     * @return 影响行数
     */
    int delete(SqlDeleteStatement stmt, Map<String, Object> paramMap);

    /**
     * 批量删除数据
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    int[] batchDelete(SqlDeleteStatement stmt, List<Map<String, Object>> paramMaps);

}
