package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.oql.visitor.InsertStatementCheckOqlAstVisitor;
import net.cf.object.engine.sqlbuilder.select.SelectItemInfo;
import net.cf.object.engine.sqlbuilder.select.SqlSelectStatementBuilder;
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
        return this.convertResultMap(resultMap, builder.getSelectItemInfos());
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder, resolver);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt, paramMap);
        return this.convertResultMap(resultMap, builder.getSelectItemInfos());
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder, resolver);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt);
        return this.convertResultMapList(resultMapList, builder.getSelectItemInfos());
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = OqlStatementUtils.toSqlSelect(stmt, builder, resolver);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt, paramMap);
        return this.convertResultMapList(resultMapList, builder.getSelectItemInfos());
    }


    /**
     * 转换查询结果，将resultMap中的key（列名）转换为字段名
     *
     * @param resultMapList
     * @param selectItemInfos
     * @return 生成新的对象返回
     */
    private List<Map<String, Object>> convertResultMapList(List<Map<String, Object>> resultMapList, List<SelectItemInfo> selectItemInfos) {
        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (Map<String, Object> resultMap : resultMapList) {
            targetMapList.add(this.convertResultMap(resultMap, selectItemInfos));
        }
        return targetMapList;
    }

    /**
     * 转换查询结果，将resultMap中的key（列名）转换为字段名
     *
     * @param resultMap
     * @param selectItemInfos
     * @return 生成新的对象返回
     */
    private Map<String, Object> convertResultMap(Map<String, Object> resultMap, List<SelectItemInfo> selectItemInfos) {
        Map<String, Object> targetMap = new HashMap<>();
        for (SelectItemInfo selectItemInfo : selectItemInfos) {
            String targetKey = selectItemInfo.getFieldName();
            boolean isArray = selectItemInfo.isArray();
            Object targetValue;
            List<SelectItemInfo> subItems = selectItemInfo.getSubItemInfos();
            if (subItems != null && subItems.size() > 0) {// 相关模型展开或字段展开
                if (isArray) { // 字段展开且是数组的情况
                    targetValue = this.parseMapListFormResultMap(resultMap, subItems);
                } else { // 模型或字段展开且是非数组的情况
                    targetValue = this.parseMapFormResultMap(resultMap, subItems);
                }
            } else { // 普通的相关模型字段、本表字段
                String resultKey = selectItemInfo.getColumnName();
                Object resultValue = resultMap.get(resultKey);
                if (isArray) {
                    targetValue = this.parseObjectListResultValue(resultValue);
                } else {
                    targetValue = this.parseObjectResultValue(resultValue);
                }
            }

            targetMap.put(targetKey, targetValue);
        }

        return targetMap;
    }

    /**
     * 转换查询结果，将resultMap中的key（列名）转换为字段名
     *
     * @param select
     * @param resultMap
     * @return 生成新的对象返回
     */
    /*private Map<String, Object> convertResultMap(OqlSelect select, Map<String, Object> resultMap) {
        assert (resultMap != null);

        XObject selfObject = select.getFrom().getResolvedObject();
        Map<String, Object> targetMap = new HashMap<>();
        List<OqlSelectItem> selectItems = select.getSelectItems();
        for (OqlSelectItem selectItem : selectItems) {
            SqlExpr expr = selectItem.getExpr();
            if (expr instanceof SqlAllColumnExpr) {
                List<XField> fields = selfObject.getFields();
                String resultKey;
                String targetKey;
                Object targetValue;
                for (XField field : fields) {
                    resultKey = field.getColumnName();
                    targetKey = field.getName();
                    if (field.isArray()) {
                        targetValue = this.parseObjectListFromResultMap(resultMap, resultKey);
                    } else {
                        targetValue = this.parseObjectFromResultMap(resultMap, resultKey);
                    }
                    resultMap.put(targetKey, targetValue);
                }
                continue;
            }

            String targetKey = selectItem.getResolvedFieldName();
            Object targetValue = null;
            XField resolvedField = selectItem.getResolvedField();
            if (resolvedField != null) { // 字段类型
                boolean isArray = resolvedField.isArray();
                if (expr instanceof AbstractExpandableOqlExprImpl) {
                    AbstractExpandableOqlExprImpl objectExpandExpr = (AbstractExpandableOqlExprImpl) expr;
                    Map<String, String> resolvedKeyMap = objectExpandExpr.getResolvedKeyMap();
                    if (isArray) {
                        targetValue = this.parseMapListFormResultMap(resultMap, resolvedKeyMap);
                    } else {
                        targetValue = this.parseMapFormResultMap(resultMap, resolvedKeyMap);
                    }
                } else if (expr instanceof SqlName) {
                    String resultKey = selectItem.getResolvedColumnName();
                    if (isArray) {
                        targetValue = this.parseObjectListFromResultMap(resultMap, resultKey);
                    } else {
                        targetValue = this.parseObjectFromResultMap(resultMap, resultKey);
                    }
                }
            } else { // 非字段类型，可能是聚合字段、方法调用字段、case-when字段等
                String resultKey = selectItem.getResolvedColumnName();
                targetValue = resultMap.get(resultKey);
            }

            // 判断是否设置了别名
            String alias = selectItem.getAlias();
            if (alias != null) {
                targetKey = alias;
            }

            targetMap.put(targetKey, targetValue);
        }

        return targetMap;
    }*/

    /**
     * 从结果集中解析含多个key的List<Map>，用于字段（是数组）展开的情形
     *
     * @param resultMap
     * @param selectItemInfos
     * @return
     */
    private List<Map<String, Object>> parseMapListFormResultMap(Map<String, Object> resultMap, List<SelectItemInfo> selectItemInfos) {
        // 计算结果List的长度（取决于最长的一个数据的长度），同时将非List的数据转为List
        int valueSize = 0;
        for (SelectItemInfo selectItemInfo : selectItemInfos) {
            String resultKey = selectItemInfo.getColumnName();
            Object resultValue = resultMap.get(resultKey);
            List<?> listResultValue = null;
            if (resultValue instanceof List) {
                listResultValue = ((List<?>) resultValue);
            } else if (resultValue instanceof String) {
                String strV = (String) resultValue;
                listResultValue = this.stringToList(strV);
                resultMap.put(resultKey, listResultValue);
            } else {
                listResultValue = Arrays.asList(resultValue);
                resultMap.put(resultKey, listResultValue);
            }

            int listResultValueSize = listResultValue.size();
            if (listResultValueSize > valueSize) {
                valueSize = listResultValueSize;
            }
        }

        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (int i = 0; i < valueSize; i++) {
            Map<String, Object> targetMap = new HashMap<>();
            for (SelectItemInfo selectItemInfo : selectItemInfos) {
                String resultKey = selectItemInfo.getColumnName();
                String targetKey = selectItemInfo.getFieldName();
                boolean isArray = selectItemInfo.isArray();
                Object targetValue = null;
                List<?> listResultValue = (List<?>) resultMap.get(resultKey);
                ;
                if (listResultValue.size() > i) {
                    Object resultValue = listResultValue.get(i);
                    if (isArray) {
                        targetValue = this.parseObjectListResultValue(resultValue);
                    } else {
                        targetValue = this.parseObjectResultValue(resultValue);
                    }
                }
                targetMap.put(targetKey, targetValue);
            }

            targetMapList.add(targetMap);
        }


        return targetMapList;
    }

    /**
     * 从结果集中解析含多个key的Map，用于关联对象（一对一）展开，或者字段（非数组）展开的情形
     *
     * @param resultMap
     * @param selectItemInfos
     * @return
     */
    private Map<String, Object> parseMapFormResultMap(Map<String, Object> resultMap, List<SelectItemInfo> selectItemInfos) {
        Map<String, Object> targetMap = new HashMap<>();
        for (SelectItemInfo selectItemInfo : selectItemInfos) {
            String resultKey = selectItemInfo.getColumnName();
            String targetKey = selectItemInfo.getFieldName();
            boolean isArray = selectItemInfo.isArray();
            Object resultValue = resultMap.get(resultKey);
            Object targetValue;
            if (isArray) {
                targetValue = this.parseObjectListResultValue(resultValue);
            } else {
                targetValue = this.parseObjectResultValue(resultValue);
            }
            targetMap.put(targetKey, targetValue);
        }

        return targetMap;
    }

    /**
     * 从结果集中的值解析为一个List
     *
     * @param resultValue
     * @return
     */
    private List<?> parseObjectListResultValue(Object resultValue) {
        if (resultValue == null) {
            return null;
        }

        List<?> resultListValue = null;
        if (resultValue instanceof String) {
            // 历史数据中可能存在非数组转数组的情况
            resultListValue = this.stringToList((String) resultValue);
        } else if (resultValue instanceof List) {
            resultListValue = (List<?>) resultValue;
        }

        if (resultListValue != null) {
            return resultListValue;
        } else {
            return Arrays.asList(resultValue);
        }
    }

    /**
     * 从结果集中的值解析为一个Object
     *
     * @param resultValue
     * @return
     */
    private Object parseObjectResultValue(Object resultValue) {
        if (resultValue != null) {
            // 历史数据中可能存在数组转非数组的情况
            if (resultValue instanceof List) {
                resultValue = resultValue.toString();
            }
        }

        return resultValue;
    }

    /**
     * 将 String 转为 List
     *
     * @param str
     * @return
     */
    private List<String> stringToList(String str) {
        if (str.startsWith("[") && str.endsWith("]")) {
            str = str.substring(1, str.length() - 1);
        }

        String[] items = str.split(",");
        for (int i = 0, l = items.length; i < l; i++) {
            items[i] = items[i].trim();
        }

        return Arrays.asList(items);
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
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        this.repository.update(sqlStmt);
        return 0;
    }

    @Override
    public int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        SqlUpdateStatement sqlStmt = OqlStatementUtils.toSqlUpdate(stmt);
        int effectedRows = this.repository.update(sqlStmt, paramMap);
        return effectedRows;
    }

    @Override
    public int[] modifyList(OqlUpdateStatement statement, List<Map<String, Object>> paramMaps) {
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
