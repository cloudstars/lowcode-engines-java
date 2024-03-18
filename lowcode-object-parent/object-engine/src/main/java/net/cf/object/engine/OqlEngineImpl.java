package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.visitor.InsertStatementCheckOqlAstVisitor;
import net.cf.object.engine.util.OqlStatementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt);
        return this.convertResultMap(stmt, resultMap);
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt, paramMap);
        return this.convertResultMap(stmt, resultMap);
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt) {
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt);
        return this.convertResultMapList(stmt, resultMapList);
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt, paramMap);
        return this.convertResultMapList(stmt, resultMapList);
    }

    /**
     * 转换查询结果列表
     *
     * @param stmt
     * @param resultMapList
     * @return
     */
    private List<Map<String, Object>> convertResultMapList(OqlSelectStatement stmt, List<Map<String, Object>> resultMapList) {
        if (resultMapList == null || resultMapList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (Map<String, Object> resultMap : resultMapList) {
            targetMapList.add(this.convertResultMap(stmt, resultMap));
        }

        return targetMapList;
    }

    /**
     * 转换查询结果
     *
     * @param stmt
     * @param resultMap
     * @return
     */
    private Map<String, Object> convertResultMap(OqlSelectStatement stmt, Map<String, Object> resultMap) {
        Map<String, Object> targetMap = null;
        // 将resultMap中的key（列名）转换为字段名
        if (resultMap != null) {
            List<SqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
            targetMap = new HashMap<>();
            for (SqlSelectItem selectItem : selectItems) {
                SqlExpr expr = selectItem.getExpr();
                KeyValuePair keyValuePair = this.convertResultValue(expr, resultMap);
                String targetKey = keyValuePair.key;
                Object targetValue = keyValuePair.value;

                // 判断是否设置了别名
                String alias = selectItem.getAlias();
                if (targetKey != null) {
                    if (alias != null) {
                        targetKey = alias;
                    }
                    targetMap.put(targetKey, targetValue);
                }
            }
        } else {
            logger.debug("未查询到记录，语句：{}。", stmt);
        }

        return targetMap;
    }

    /**
     * 根据当前表达式来转换结果的值
     *
     * @param expr
     * @param resultMap
     * @return
     */
    private KeyValuePair convertResultValue(SqlExpr expr, Map<String, Object> resultMap) {
        KeyValuePair keyValuePair = new KeyValuePair();
        if (expr instanceof SqlIdentifierExpr) {
            SqlIdentifierExpr idExpr = (SqlIdentifierExpr) expr;
            String columnName = idExpr.getResolvedColumn();
            String fieldName = idExpr.getName();
            if (resultMap.containsKey(columnName)) {
                keyValuePair.key = fieldName;
                keyValuePair.value = resultMap.get(columnName);
            } else {
                logger.warn("未找到字段{}对应的列{}的数据！", fieldName, columnName);
            }
        } else if (expr instanceof OqlPropertyExpr) {
            OqlPropertyExpr propExpr = (OqlPropertyExpr) expr;
            String columnName = propExpr.getResolvedColumn();
            String fieldName = propExpr.getResolvedField().getName() + "." + propExpr.getProperty();
            if (resultMap.containsKey(columnName)) {
                keyValuePair.key = fieldName;
                keyValuePair.value = resultMap.get(columnName);
            } else {
                logger.warn("未找到字段{}对应的列{}的数据！", fieldName, columnName);
            }
        } else if (expr instanceof OqlFieldExpandExpr) {
            OqlFieldExpandExpr fieldExpandExpr = (OqlFieldExpandExpr) expr;
            keyValuePair.key = fieldExpandExpr.getResolvedField().getName();
            boolean isArray = fieldExpandExpr.getResolvedField().isArray();
            if (isArray) {
                int valueSize = 0;
                // 计算dataMapList的长度
                for (SqlIdentifierExpr property : fieldExpandExpr.getProperties()) {
                    Object propValue = resultMap.get(property.getResolvedColumn());
                    assert (propValue instanceof List);
                    int itemValueSize = ((List<?>) propValue).size();
                    if (itemValueSize > valueSize) {
                        valueSize = itemValueSize;
                    }
                }
                List<Map<String, Object>> dataMapList = new ArrayList<>(valueSize);
                int vi = 0;
                while (vi >= valueSize) {
                    Map<String, Object> dataMap = new HashMap<>();
                    for (SqlIdentifierExpr property : fieldExpandExpr.getProperties()) {
                        Object propValue = resultMap.get(property.getResolvedColumn());
                        dataMap.put(property.getName(), ((List<?>) propValue).get(vi));
                    }
                    dataMapList.add(dataMap);
                    vi++;
                }
                keyValuePair.value = dataMapList;
            } else {
                Map<String, Object> dataMap = new HashMap<>();
                for (SqlIdentifierExpr property : fieldExpandExpr.getProperties()) {
                    Object propValue = resultMap.get(property.getResolvedColumn());
                    dataMap.put(property.getName(), propValue);
                }
                keyValuePair.value = dataMap;
            }
        } else {
            keyValuePair.key = expr.toString();
            keyValuePair.value = resultMap.get(keyValuePair.key);
        }

        return keyValuePair;
    }

    private class KeyValuePair {
        String key;
        Object value;
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
        // 语法检查
        InsertStatementCheckOqlAstVisitor checkVisitor = new InsertStatementCheckOqlAstVisitor();
        stmt.accept(checkVisitor);

        // 根据object的定义校验数据
        // TODO

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
    public int[] createList(OqlInsertStatement statement, List<Map<String, Object>> dataMaps) {
        return new int[0];
    }

    @Override
    public int modify(OqlUpdateStatement stmt) {
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        this.repository.update(sqlStmt);
        return 0;
    }

    @Override
    public int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        this.repository.update(sqlStmt, paramMap);
        return 0;
    }

    @Override
    public int[] modifyList(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps) {
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
    public int[] removeList(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps) {
        return new int[0];
    }

}
