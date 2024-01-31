package net.cf.form.engine.repository;

import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;

import java.util.List;
import java.util.Map;

/**
 * 数据存储接口
 *
 * @author clouds
 */
@Deprecated
public interface RepositoryDriver {

    /**
     * 插入一条数据
     *
     * @param statement
     * @return 影响行数
     */
    int insert(OqlInsertStatement statement);

    /**
     * 插入一条数据，并携带参数
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int insert(OqlInsertStatement statement, Map<String, Object> paramMap);

    /**
     * 批量插入数据，并携带参数
     *
     * @param statement
     * @param paramMapList
     * @return
     */
    int[] batchInsert(OqlInsertStatement statement, List<Map<String, Object>> paramMapList);


    /**
     * 更新一条数据，并携带参数
     *
     * @param statement
     * @return 影响行数
     */
    int update(OqlUpdateStatement statement);

    /**
     * 更新一条数据
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int update(OqlUpdateStatement statement, Map<String, Object> paramMap);


    /**
     * 批量更新数据，并携带参数
     *
     * @param statement
     * @param paramMapList
     * @return
     */
    int[] batchUpdate(OqlUpdateStatement statement, List<Map<String, Object>> paramMapList);

    /**
     * 删除一条数据
     *
     * @param statement
     * @return 影响行数
     */
    int delete(OqlDeleteStatement statement);

    /**
     * 删除一条数据，并携带参数
     *
     * @param statement
     * @param paramMap
     * @return 影响行数
     */
    int delete(OqlDeleteStatement statement, Map<String, Object> paramMap);
    
    /**
     * 批量删除数据，并携带参数
     *
     * @param statement
     * @param paramMapList
     * @return 影响行数
     */
    int delete(OqlDeleteStatement statement, List<Map<String, Object>> paramMapList);

    /**
     * 查询列表数据
     *
     * @param statement
     * @return
     */
    List<Map<String, Object>> select(OqlSelectStatement statement);

    /**
     * 查询列表数据，并携带参数
     *
     * @param statement
     * @param paramMap
     * @return
     */
    List<Map<String, Object>> select(OqlSelectStatement statement, Map<String, Object> paramMap);
}
