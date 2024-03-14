package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.visitor.InsertStatementCheckOqlAstVisitor;
import net.cf.object.engine.sqlbuilder.OqlStatementUtils;
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
        Map<String, Object> cParamMap = this.convertParamMap(stmt.getSelect().getFrom(), paramMap);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt, cParamMap);
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
        Map<String, Object> cParamMap = this.convertParamMap(stmt.getSelect().getFrom(), paramMap);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt, cParamMap);
        return this.convertResultMapList(stmt, resultMapList);
    }

    /**
     * 转换参数映射
     *
     * @param objectSource
     * @param paramMap
     * @return
     */
    private Map<String, Object> convertParamMap(OqlObjectSource objectSource, Map<String, Object> paramMap) {
        if (paramMap == null) {
            return Collections.emptyMap();
        } else if (paramMap.size() == 0) {
            return paramMap;
        }

        if (objectSource instanceof OqlExprObjectSource) {
            XObject object = ((OqlExprObjectSource) objectSource).getResolvedObject();
            Map<String, Object> targetParamMap = new HashMap<>();
            paramMap.forEach((k, v) -> {
                XField field = object.getField(k);
                targetParamMap.put(field.getColumnName(), v);
            });
            return targetParamMap;
        } else {
            return paramMap;
        }
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
                String targetKey = null;
                Object targetValue = null;
                if (expr instanceof SqlIdentifierExpr) {
                    String columnName = ((SqlIdentifierExpr) expr).getResolvedColumn();
                    String fieldName = ((SqlIdentifierExpr) expr).getName();
                    if (resultMap.containsKey(columnName)) {
                        targetKey = fieldName;
                        targetValue = resultMap.get(columnName);
                    } else {
                        logger.warn("未找到字段{}对应的列{}的数据！", fieldName, columnName);
                    }
                } else {
                    throw new UnsupportedOperationException("暂不支持非标识符列！");
                }

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


    @Override
    public String create(OqlInsertStatement stmt) {
        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt);
        int effectedRows = this.repository.insert(sqlStmt);
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，OQL：", stmt);
        }

        return "TODO";
    }

    @Override
    public String create(OqlInsertStatement stmt, Map<String, Object> dataMap) {
        // 语法检查
        InsertStatementCheckOqlAstVisitor checkVisitor = new InsertStatementCheckOqlAstVisitor();
        stmt.accept(checkVisitor);


        // 根据object的定义校验数据
        // TODO

        SqlInsertStatement sqlStmt = OqlStatementUtils.toSqlInsert(stmt);
        Map<String, Object> cDataMap = this.convertParamMap(stmt.getObjectSource(), dataMap);
        int effectedRows = this.repository.insert(sqlStmt, cDataMap);
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，OQL：", stmt);
        }

        Object recordId = null;
        XField primaryField = stmt.getObjectSource().getResolvedObject().getPrimaryField();
        if (primaryField != null) {
            recordId = dataMap.get(primaryField.getColumnName());
        }

        return recordId != null ? recordId.toString() : null;
    }


    @Override
    public List<String> createList(OqlInsertStatement statement, List<Map<String, Object>> dataMaps) {
        return null;
    }

    @Override
    public void modify(OqlUpdateStatement stmt) {
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        this.repository.update(sqlStmt);
    }

    @Override
    public void modify(OqlUpdateStatement stmt, Map<String, Object> dataMap) {
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        Map<String, Object> cDataMap = this.convertParamMap(stmt.getObjectSource(), dataMap);
        this.repository.update(sqlStmt, cDataMap);
    }

    @Override
    public void modifyList(OqlUpdateStatement statement, List<Map<String, Object>> dataMaps) {

    }

    @Override
    public void remove(OqlDeleteStatement stmt) {
        SqlDeleteStatement sqlStmt = OqlStatementUtils.toSqlDelete(stmt);
        this.repository.delete(sqlStmt);
    }

    @Override
    public void remove(OqlDeleteStatement stmt, Map<String, Object> dataMap) {
        SqlDeleteStatement sqlStmt = OqlStatementUtils.toSqlDelete(stmt);
        Map<String, Object> cDataMap = this.convertParamMap(stmt.getFrom(), dataMap);
        this.repository.delete(sqlStmt, cDataMap);
    }

    @Override
    public void removeList(OqlDeleteStatement statement, List<Map<String, Object>> dataMaps) {

    }

}
