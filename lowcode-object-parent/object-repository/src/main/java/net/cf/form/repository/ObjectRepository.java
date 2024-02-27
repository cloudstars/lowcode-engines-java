package net.cf.form.repository;

import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;

import java.util.List;
import java.util.Map;

/**
 * 模型数据存储接口
 *
 * @author clouds
 */
public interface ObjectRepository {

    /**
     * 插入记录
     *
     * @param statement
     * @return 影响行数
     */
    int insert(SqlInsertStatement statement);

    /**
     * 插入一条记录，并携带参数
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int insert(SqlInsertStatement statement, Map<String, Object> paramMap);

    /**
     * 批量插入记录，并携带参数
     *
     * @param statement
     * @param paramMapList
     * @return
     */
    int[] batchInsert(SqlInsertStatement statement, List<Map<String, Object>> paramMapList);


    /**
     * 更新一条数据，并携带参数
     *
     * @param statement
     * @return 影响行数
     */
    int update(SqlUpdateStatement statement);

    /**
     * 更新一条数据
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int update(SqlUpdateStatement statement, Map<String, Object> paramMap);


    /**
     * 批量更新记录，并携带参数
     *
     * @param statement
     * @param paramMapList
     * @return
     */
    int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMapList);

    /**
     * 删除一条数据
     *
     * @param statement
     * @return 影响行数
     */
    int delete(SqlDeleteStatement statement);

    /**
     * 删除一条数据，并携带参数
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int delete(SqlDeleteStatement statement, Map<String, Object> paramMap);

    /**
     * 批量删除数据
     *
     * @param statement
     * @param paramMapList
     * @return
     */
    int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMapList);

    /**
     * 查询单条数据
     *
     * @param statement
     * @return
     */
    Map<String, Object> selectOne(SqlSelectStatement statement);

    /**
     * 查询单条数据，并携带参数
     *
     * @param statement
     * @param paramMap
     * @return
     */
    Map<String, Object> selectOne(SqlSelectStatement statement, Map<String, Object> paramMap);

    /**
     * 查询列表数据
     *
     * @param statement
     * @return
     */
    List<Map<String, Object>> selectList(SqlSelectStatement statement);

    /**
     * 查询列表数据，并携带参数
     *
     * @param statement
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> selectList(SqlSelectStatement statement, Map<String, Object> paramMap);
}
