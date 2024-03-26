package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.data.DefaultResultReducer;
import net.cf.object.engine.data.ResultReducer;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.sqlbuilder.select.SqlSelectStatementBuilder;
import net.cf.object.engine.util.OqlStatementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * OQL记录引擎实现
 *
 * @author clouds
 */
public class OqlEngineImpl implements OqlEngine {

    private static Logger logger = LoggerFactory.getLogger(OqlEngineImpl.class);

    private final ObjectRepository repository;

    private final XObjectResolver resolver;

    public OqlEngineImpl(ObjectRepository repository, XObjectResolver resolver) {
        this.repository = repository;
        this.resolver = resolver;
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder, resolver);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return resultReducer.reduceResult(resultMap);
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder, resolver);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt, paramMap);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return resultReducer.reduceResult(resultMap);
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder, resolver);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return this.convertResultMapList(resultMapList, resultReducer);
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder, resolver);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt, paramMap);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return this.convertResultMapList(resultMapList, resultReducer);
    }

    /**
     * 转换查询结果，将resultMap中的key（列名）转换为字段名
     *
     * @param resultMapList
     * @param resultReducer
     * @return 生成新的对象返回
     */
    private List<Map<String, Object>> convertResultMapList(List<Map<String, Object>> resultMapList, ResultReducer resultReducer) {
        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (Map<String, Object> resultMap : resultMapList) {
            targetMapList.add(this.convertResultMap(resultMap, resultReducer));
        }
        return targetMapList;
    }

    /**
     * 转换查询结果，将resultMap中的key（列名）转换为字段名
     *
     * @param resultMap
     * @param resultReducer
     * @return 生成新的对象返回
     */
    private Map<String, Object> convertResultMap(Map<String, Object> resultMap, ResultReducer resultReducer) {
        return resultReducer.reduceResult(resultMap);
    }

    @Override
    public int create(OqlInsertStatement stmt) {
        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt);
        int effectedRows = this.repository.insert(sqlStmt);
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，OQL：", stmt);
        }

        return effectedRows;
    }

    @Override
    public int create(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        // TODO 对paramMap进行数据到驱动的映射处理

        // TODO根据object的定义校验数据

        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt);
        int effectedRows = this.repository.insert(sqlStmt, paramMap);
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        } else {
            logger.warn("成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        }

        return effectedRows;
    }


    @Override
    public int[] createList(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        // TODO 对paramMap进行数据到驱动的映射处理
        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt);
        int[] effectedRowsArray = this.repository.batchInsert(sqlStmt, paramMaps);
        int effectedRows = 0;
        for (int i = 0, l = effectedRowsArray.length; i < l; i++) {
            effectedRows += effectedRowsArray[i];
        }
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        } else {
            logger.warn("成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        }

        return effectedRowsArray;
    }

    @Override
    public int modify(OqlUpdateStatement stmt) {
        // TODO 对paramMap进行数据到驱动的映射处理
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        this.repository.update(sqlStmt);
        return 0;
    }

    @Override
    public int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        // TODO 对paramMap进行数据到驱动的映射处理
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        int effectedRows = this.repository.update(sqlStmt, paramMap);
        return effectedRows;
    }

    @Override
    public int[] modifyList(OqlUpdateStatement statement, List<Map<String, Object>> paramMaps) {
        // TODO 对paramMap进行数据到驱动的映射处理

        return new int[0];
    }

    @Override
    public int remove(OqlDeleteStatement stmt) {
        SqlDeleteStatement sqlStmt = OqlStatementUtils.toSqlDelete(stmt);
        this.repository.delete(sqlStmt);
        return 0;
    }

    @Override
    public int remove(OqlDeleteStatement stmt, Map<String, Object> paramMap) {
        SqlDeleteStatement sqlStmt = OqlStatementUtils.toSqlDelete(stmt);
        this.repository.delete(sqlStmt, paramMap);
        return 0;
    }

    @Override
    public int[] removeList(OqlDeleteStatement statement, List<Map<String, Object>> paramMaps) {
        return new int[0];
    }

}
