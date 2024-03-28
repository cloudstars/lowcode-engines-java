package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.data.DefaultParameterMapper;
import net.cf.object.engine.data.DefaultResultReducer;
import net.cf.object.engine.data.ParameterMapper;
import net.cf.object.engine.data.ResultReducer;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.check.InsertStatementChecker;
import net.cf.object.engine.oql.check.UpdateStatementChecker;
import net.cf.object.engine.sqlbuilder.insert.SqlInsertStatementBuilder;
import net.cf.object.engine.sqlbuilder.select.SqlSelectStatementBuilder;
import net.cf.object.engine.sqlbuilder.update.SqlUpdateStatementBuilder;
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

    public OqlEngineImpl(ObjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return resultReducer.reduceResult(resultMap);
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt, paramMap);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return resultReducer.reduceResult(resultMap);
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return this.convertResultMapList(resultReducer, resultMapList);
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt, paramMap);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return this.convertResultMapList(resultReducer, resultMapList);
    }

    /**
     * 转换查询结果，将resultMap中的key（列名）转换为字段名
     *
     * @param resultReducer
     * @param resultMapList
     * @return 生成新的对象返回
     */
    private List<Map<String, Object>> convertResultMapList(ResultReducer resultReducer, List<Map<String, Object>> resultMapList) {
        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (Map<String, Object> resultMap : resultMapList) {
            targetMapList.add(this.convertResultMap(resultReducer, resultMap));
        }
        return targetMapList;
    }

    /**
     * 转换查询结果，将resultMap中的key（列名）转换为字段名
     *
     * @param resultReducer
     * @param resultMap
     * @return 生成新的对象返回
     */
    private Map<String, Object> convertResultMap(ResultReducer resultReducer, Map<String, Object> resultMap) {
        return resultReducer.reduceResult(resultMap);
    }

    @Override
    public int create(OqlInsertStatement stmt) {
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt, builder);
        int effectedRows = this.repository.insert(sqlStmt);
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，OQL：", stmt);
        }

        return effectedRows;
    }

    @Override
    public int create(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        // 对OQL语句进行合法性校验
        InsertStatementChecker checker = new InsertStatementChecker();
        stmt.accept(checker);

        // 构建SQL语句
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt, builder);

        // 作参数映射，将引擎层的参数转换驱动层的参数格式
        DefaultParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        Map<String, Object> targetParamMap = parameterMapper.mapParameter(paramMap);
        int effectedRows = this.repository.insert(sqlStmt, targetParamMap);
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        } else {
            logger.warn("成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        }

        return effectedRows;
    }


    @Override
    public int[] createList(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        // 对OQL语句进行合法性校验
        InsertStatementChecker checker = new InsertStatementChecker();
        stmt.accept(checker);

        // 构建SQL语句
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt, builder);

        // 作参数映射，将引擎层的参数转换驱动层的参数格式
        DefaultParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        List<Map<String, Object>> targetParamMaps = this.convertParameterMapList(parameterMapper, paramMaps);
        int[] effectedRowsArray = this.repository.batchInsert(sqlStmt, targetParamMaps);
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

    /**
     * 转换输入参数，将parameterMap中的key（字段名）转换为列名
     *
     * @param parameterMapper
     * @param parameterMapList
     * @return 生成新的对象返回
     */
    private List<Map<String, Object>> convertParameterMapList(ParameterMapper parameterMapper, List<Map<String, Object>> parameterMapList) {
        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (Map<String, Object> paramaeterMap : parameterMapList) {
            targetMapList.add(this.convertParameterMap(parameterMapper, paramaeterMap));
        }
        return targetMapList;
    }

    /**
     * 转换输入参数，将parameterMap中的key（字段名）转换为列名
     *
     * @param parameterMapper
     * @param parameterMap
     * @return 生成新的对象返回
     */
    private Map<String, Object> convertParameterMap(ParameterMapper parameterMapper, Map<String, Object> parameterMap) {
        return parameterMapper.mapParameter(parameterMap);
    }


    @Override
    public int modify(OqlUpdateStatement stmt) {
        // 对OQL语句进行合法性校验
        UpdateStatementChecker checker = new UpdateStatementChecker();
        stmt.accept(checker);

        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        this.repository.update(sqlStmt);
        return 0;
    }

    @Override
    public int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        // 对OQL语句进行合法性校验
        UpdateStatementChecker checker = new UpdateStatementChecker();
        stmt.accept(checker);

        SqlUpdateStatementBuilder builder = new SqlUpdateStatementBuilder();
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt, builder);
        ParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        Map<String, Object> targetParamMap = this.convertParameterMap(parameterMapper, paramMap);
        int effectedRows = this.repository.update(sqlStmt, targetParamMap);
        return effectedRows;
    }

    @Override
    public int[] modifyList(OqlUpdateStatement stmt, List<Map<String, Object>> paramMaps) {
        // 对OQL语句进行合法性校验
        UpdateStatementChecker checker = new UpdateStatementChecker();
        stmt.accept(checker);

        SqlUpdateStatementBuilder builder = new SqlUpdateStatementBuilder();
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt, builder);
        ParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        List<Map<String, Object>> targetParamMaps = this.convertParameterMapList(parameterMapper, paramMaps);
        int[] effectedRowsArray = this.repository.batchUpdate(sqlStmt, targetParamMaps);
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
