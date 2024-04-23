package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAggregateExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.data.*;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.parser.XObjectResolver;
import net.cf.object.engine.oql.stmt.OqlSelectInfo;
import net.cf.object.engine.oql.stmt.OqlSelectStatement;
import net.cf.object.engine.util.OqlStatementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * OQL记录引擎实现（NEW)
 *
 * @author clouds
 */
public class OqlEngineNewImpl implements OqlEngineNew {

    private static Logger LOGGER = LoggerFactory.getLogger(OqlEngineImpl.class);

    private final XObjectResolver resolver;

    private final ObjectRepository repository;

    private final int limitSize = -1;

    public OqlEngineNewImpl(XObjectResolver resolver, ObjectRepository repository) {
        this.resolver = resolver;
        this.repository = repository;
    }

    public int getLimitSize() {
        return limitSize;
    }

    @Override
    public Map<String, Object> queryOne(String oql) {
        return this.queryOne(oql, Collections.emptyMap());
    }

    @Override
    public Map<String, Object> queryOne(String oql, Map<String, Object> paramMap) {
        OqlSelectStatement thisOqlStmt = OqlStatementUtils.parseSingleSelectStatement(resolver, oql);
        XObject selfObject = thisOqlStmt.getResolvedSelfObject();

        // 查询本表数据
        OqlSelectInfo selfSelectInfo = thisOqlStmt.getSelfSelectInfo();
        Map<String, Object> selfResultMap = this.repository.selectOne(selfSelectInfo.getStatement(), paramMap);
        if (selfResultMap != null) {
            DefaultResultReducer resultReducer = new DefaultResultReducer(selfSelectInfo.getFieldMappings());
            selfResultMap = this.convertResultMap(resultReducer, selfResultMap);
            LOGGER.info("成功返回1条记录！");
        } else {
            LOGGER.info("未查询到记录，返回0条！");
            return null;
        }

        // 存在子表的情况下，依次查询子表
        List<OqlSelectInfo> detailSelectInfos = thisOqlStmt.getDetailSelectInfos();
        if (detailSelectInfos != null && detailSelectInfos.size() > 0) {
            // 从主表返回的数据中抽取记录ID
            String selfPrimaryFieldName = selfObject.getPrimaryField().getName();
            String selfRecordId = selfResultMap.get(selfPrimaryFieldName).toString();
            for (OqlSelectInfo detailSelectInfo : detailSelectInfos) {
                XObject detailObject = detailSelectInfo.getResolvedObject();
                // 组装子表的查询条件（where masterField = #{masterField}）所需参数
                Map<String, Object> detailParamMap = new HashMap<>();
                XObjectRefField detailMasterField = detailObject.getObjectRefField(selfObject.getName());
                String detailMasterFieldName = detailMasterField.getName();
                detailParamMap.put(detailMasterFieldName, selfRecordId);
                // 查询子表的数据并作并归并
                SqlSelectStatement detailSqlStmt = detailSelectInfo.getStatement();
                List<Map<String, Object>> detailResultMapList = this.repository.selectList(detailSqlStmt, detailParamMap);
                DefaultResultReducer resultReducer = new DefaultResultReducer(detailSelectInfo.getFieldMappings());
                detailResultMapList = this.convertResultMapList(resultReducer, detailResultMapList);
                // 将子表归并后的数据追加到主表返回结果中
                String detailResultKey = detailSelectInfo.getObjectRefResultKey();
                if (detailSelectInfo.isDetailFieldDirectQuery()) {
                    String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
                    List<String> detailRecordIds = this.extractRecordIdsFromResultMapList(detailResultMapList, detailPrimaryFieldName);
                    selfResultMap.put(detailResultKey, detailRecordIds);
                } else {
                    selfResultMap.put(detailResultKey, detailResultMapList);
                }
            }
        }

        // 存在相关表的情况下，依次查询相关表
        List<OqlSelectInfo> lookupSelectInfos = thisOqlStmt.getLookupSelectInfos();
        if (lookupSelectInfos != null && lookupSelectInfos.size() > 0) {
            for (OqlSelectInfo lookupSelectInfo : lookupSelectInfos) {
                XObject lookupObject = lookupSelectInfo.getResolvedObject();
                String selfLookupFieldName = lookupSelectInfo.getObjectRefFieldName();
                Object lookupFieldValue = selfResultMap.get(selfLookupFieldName);
                XObjectRefField selfLookupField = (XObjectRefField) selfObject.getField(selfLookupFieldName);
                // 组装子表的查询参数并独立查询子表数据
                Map<String, Object> lookupParamMap = new HashMap<>();
                String lookupPrimaryFieldName = lookupObject.getPrimaryField().getName();
                SqlSelectStatement lookupSqlStmt = lookupSelectInfo.getStatement();
                DefaultResultReducer resultReducer = new DefaultResultReducer(lookupSelectInfo.getFieldMappings());
                Object lookupResultValue;
                if (selfLookupField.isMultiRef()) {
                    //  where lookupPrimaryField in (#{lookupPrimaryFields})
                    List<String> lookupRecordIds = this.convertToLookupRecordIds(lookupFieldValue);
                    lookupParamMap.put(lookupPrimaryFieldName + 's', lookupRecordIds);
                    // 查询相关表的数据
                    List<Map<String, Object>> lookupResultMapList = this.repository.selectList(lookupSqlStmt, lookupParamMap);
                    lookupResultValue = this.convertResultMapList(resultReducer, lookupResultMapList);
                } else {
                    //  where lookupPrimaryField = #{lookupPrimaryField}
                    String lookupRecordId = this.convertToLookupRecordId(lookupFieldValue);
                    lookupParamMap.put(lookupPrimaryFieldName, lookupRecordId);
                    // 查询相关表的数据
                    Map<String, Object> lookupResultMap = this.repository.selectOne(lookupSqlStmt, lookupParamMap);
                    lookupResultValue = this.convertResultMap(resultReducer, lookupResultMap);
                }

                // 将相关表归并后的数据追加到主表返回结果中
                String lookupResultKey = lookupSelectInfo.getObjectRefResultKey();
                selfResultMap.put(lookupResultKey, lookupResultValue);
            }
        }

        return selfResultMap;
    }

    /**
     * 从相关表的字段值中获取相关表的记录ID
     *
     * @param lookupFieldValue
     * @return
     */
    private String convertToLookupRecordId(Object lookupFieldValue) {
        Object recordId = lookupFieldValue;
        if (lookupFieldValue instanceof List) {
            // 如果值是列表的话，仅取第一条
            recordId = ((List<?>) lookupFieldValue).get(0);
        }

        if (recordId instanceof String) {
            return (String) recordId;
        } else {
            return recordId.toString();
        }
    }

    /**
     * 从相关表的字段值中获取相关表的记录ID列表
     *
     * @param lookupFieldValue
     * @return
     */
    private List<String> convertToLookupRecordIds(Object lookupFieldValue) {
        if (lookupFieldValue instanceof String) {
            lookupFieldValue = DataConvert.stringToList((String) lookupFieldValue);
        }

        if (lookupFieldValue instanceof List) {
            List<?> lookupFieldListValue = ((List<?>) lookupFieldValue);
            if (lookupFieldListValue.size() > 0 && !(lookupFieldListValue.get(0) instanceof String)) {
                // 如果不是字符串列表的话，则转话字符串列表
                List<String> lookupFieldListStringValue = new ArrayList<>();
                for (Object lookupFieldItem : (List) lookupFieldValue) {
                    lookupFieldListStringValue.add(lookupFieldItem.toString());
                }
                return lookupFieldListStringValue;
            } else {
                return (List<String>) lookupFieldValue;
            }
        } else if (lookupFieldValue instanceof String) {
            return Arrays.asList((String) lookupFieldValue);
        } else {
            return Arrays.asList(lookupFieldValue.toString());
        }
    }

    @Override
    public List<Map<String, Object>> queryList(String oql) {
        return this.queryList(oql, Collections.emptyMap());
    }

    @Override
    public List<Map<String, Object>> queryList(String oql, Map<String, Object> paramMap) {
        OqlSelectStatement thisOqlStmt = OqlStatementUtils.parseSingleSelectStatement(resolver, oql, true);
        return this.queryList(thisOqlStmt, paramMap);
    }

    @Override
    public PageResult<Map<String, Object>> queryPage(String oql, Map<String, Object> paramMap, PageRequest pageRequest) {
        OqlSelectStatement thisOqlStmt = OqlStatementUtils.parseSingleSelectStatement(resolver, oql, true);
        SqlSelectStatement selfSqlStmt = thisOqlStmt.getSelfSelectInfo().getStatement();

        // 生成count语句
        SqlSelectStatement countSqlStmt = this.getCountSqlStmt(selfSqlStmt);
        Map<String, Object> countResultMap = this.repository.selectOne(countSqlStmt, paramMap);
        int total = (Integer) countResultMap.get("total");

        // 添加分页信息后查询当前页数据
        this.addPageInfo(selfSqlStmt, pageRequest);
        List<Map<String, Object>> list = this.queryList(thisOqlStmt, paramMap);

        return new PageResult<>(total, list);
    }

    /**
     * 添加分页信息
     *
     * @param sqlStmt
     * @param pageRequest
     */
    private void addPageInfo(SqlSelectStatement sqlStmt, PageRequest pageRequest) {
        SqlSelect select = sqlStmt.getSelect();
        SqlLimit limit = new SqlLimit();
        limit.setOffset(pageRequest.getIndex() * pageRequest.getSize());
        limit.setRowCount(pageRequest.getSize());
        select.setLimit(limit);
    }

    /**
     * 根据SQL生成对应的COUNT语句
     * @param stmt
     * @return
     */
    private SqlSelectStatement getCountSqlStmt(SqlSelectStatement stmt) {
        SqlSelect countSelect = new SqlSelect();
        SqlAggregateExpr countAgExpr = new SqlAggregateExpr("COUNT");
        countAgExpr.addArgument(new SqlIntegerExpr(1));
        countSelect.addSelectItem(new SqlSelectItem(countAgExpr));
        countSelect.setFrom(stmt.getSelect().getFrom());
        countSelect.setWhere(stmt.getSelect().getWhere());
        SqlSelectStatement countStmt = new SqlSelectStatement(countSelect);
        return countStmt;
    }

    /**
     * 根据OQL语句执行查询
     *
     * @param thisOqlStmt
     * @return
     */
    private List<Map<String, Object>> queryList(OqlSelectStatement thisOqlStmt, Map<String, Object> paramMap) {
        // 当前模型
        XObject selfObject = thisOqlStmt.getResolvedSelfObject();

        // 查询本表数据
        OqlSelectInfo selfSelectInfo = thisOqlStmt.getSelfSelectInfo();
        List<Map<String, Object>> selfResultMapList = this.repository.selectList(selfSelectInfo.getStatement(), paramMap);
        if (selfResultMapList != null && selfResultMapList.size() > 0) {
            DefaultResultReducer resultReducer = new DefaultResultReducer(selfSelectInfo.getFieldMappings());
            selfResultMapList = this.convertResultMapList(resultReducer, selfResultMapList);
            LOGGER.info("成功返回" + selfResultMapList.size() + "条记录！");
        } else {
            LOGGER.info("未查询到记录，返回0条！");
            return Collections.emptyList();
        }

        // 存在子表的情况下，依次查询子表
        List<OqlSelectInfo> detailSelectInfos = thisOqlStmt.getDetailSelectInfos();
        if (detailSelectInfos != null && detailSelectInfos.size() > 0) {
            String selfPrimaryFieldName = selfObject.getPrimaryField().getName();
            List<String> selfRecordIds = this.extractRecordIdsFromResultMapList(selfResultMapList, selfPrimaryFieldName);
            for (OqlSelectInfo detailSelectInfo : detailSelectInfos) {
                XObject detailObject = detailSelectInfo.getResolvedObject();
                // 组装子表的查询条件（where masterField in (#{masterFields}))所需参数
                Map<String, Object> detailParamMap = new HashMap<>();
                XObjectRefField detailMasterField = detailObject.getObjectRefField(selfObject.getName());
                String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
                String detailMasterFieldName = detailMasterField.getName();
                detailParamMap.put(detailMasterFieldName + 's', selfRecordIds);
                // 查询子表的数据并作归并
                SqlSelectStatement detailSqlStmt = detailSelectInfo.getStatement();
                List<Map<String, Object>> detailResultMapList = this.repository.selectList(detailSqlStmt, detailParamMap);
                DefaultResultReducer resultReducer = new DefaultResultReducer(detailSelectInfo.getFieldMappings());
                detailResultMapList = this.convertResultMapList(resultReducer, detailResultMapList);
                // 将子表归并后的数据按主表记录ID分组追加到主表返回结果中
                String detailResultKey = detailSelectInfo.getObjectRefResultKey();
                for (Map<String, Object> selfResultMap : selfResultMapList) {
                    String masterId = selfResultMap.get(selfPrimaryFieldName).toString();
                    if (detailSelectInfo.isDetailFieldDirectQuery()) {
                        List<String> detailRecordIds = this.extractRecordIdsFromResultMapList(detailResultMapList, detailPrimaryFieldName);
                        selfResultMap.put(detailResultKey, detailRecordIds);
                    } else {
                        List<Map<String, Object>> detailRecordList = this.extractDetailRecordListByField(detailResultMapList, detailMasterFieldName, masterId);
                        selfResultMap.put(detailResultKey, detailRecordList);
                    }
                }
            }
        }

        // 存在相关表的情况下，
        List<OqlSelectInfo> lookupSelectInfos = thisOqlStmt.getLookupSelectInfos();
        if (lookupSelectInfos != null && lookupSelectInfos.size() > 0) {
            for (OqlSelectInfo lookupSelectInfo : lookupSelectInfos) {
                XObject lookupObject = lookupSelectInfo.getResolvedObject();
                String selfLookupFieldName = lookupSelectInfo.getObjectRefFieldName();
                XObjectRefField selfLookupField = (XObjectRefField) selfObject.getField(selfLookupFieldName);
                // 组装子表的查询参数并独立查询子表数据并作归并
                Map<String, Object> lookupParamMap = new HashMap<>();
                List<String> lookupRecordIds = this.extractRecordIdsFromResultMapList(selfResultMapList, selfLookupFieldName);
                String lookupPrimaryFieldName = lookupObject.getPrimaryField().getName();
                lookupParamMap.put(lookupPrimaryFieldName + "s", lookupRecordIds);
                SqlSelectStatement lookupSqlStmt = lookupSelectInfo.getStatement();
                List<Map<String, Object>> lookupResultMapList = this.repository.selectList(lookupSqlStmt, lookupParamMap);
                if (CollectionUtils.isEmpty(lookupResultMapList)) {
                    continue;
                }
                DefaultResultReducer resultReducer = new DefaultResultReducer(lookupSelectInfo.getFieldMappings());
                lookupResultMapList = this.convertResultMapList(resultReducer, lookupResultMapList);
                // 将相关表归并后的数据按主表中相关表的记录ID分组追加到主表返回结果中
                String lookupResultKey = lookupSelectInfo.getObjectRefResultKey();
                if (selfLookupField.isMultiRef()) {
                    for (Map<String, Object> selfResultMap : selfResultMapList) {
                        Object lookupFieldValue = selfResultMap.get(selfLookupFieldName);
                        List<String> recordIds = this.convertToLookupRecordIds(lookupFieldValue);
                        Object lookupResultValue = this.extractLookupRecordMapListByField(lookupResultMapList, lookupPrimaryFieldName, recordIds);
                        selfResultMap.put(lookupResultKey, lookupResultValue);
                    }
                } else {
                    for (Map<String, Object> selfResultMap : selfResultMapList) {
                        Object lookupFieldValue = selfResultMap.get(selfLookupFieldName);
                        String recordId = this.convertToLookupRecordId(lookupFieldValue);
                        Object lookupResultValue = this.extractLookupRecordMapByField(lookupResultMapList, lookupPrimaryFieldName, recordId);
                        selfResultMap.put(lookupResultKey, lookupResultValue);
                    }
                }
            }
        }

        return selfResultMapList;
    }

    /**
     * 从子表的返回结果中根据指定字段名、字段值抽取一个匹配列表
     *
     * @param resultMapList
     * @param fieldName
     * @param fieldValue
     * @return
     */
    private List<Map<String, Object>> extractDetailRecordListByField(List<Map<String, Object>> resultMapList, String fieldName, String fieldValue) {
        List<Map<String, Object>> targetList = new ArrayList<>();
        for (Map<String, Object> resultMap : resultMapList) {
            if (fieldValue.equals(resultMap.get(fieldName).toString())) {
                targetList.add(resultMap);
            }
        }
        return targetList;
    }

    /**
     * 从引用表的返回结果中根据指定字段名、字段值抽取一个匹配列表
     *
     * @param resultMapList
     * @param fieldName
     * @param fieldValue
     * @return
     */
    private Map<String, Object> extractLookupRecordMapByField(List<Map<String, Object>> resultMapList, String fieldName, String fieldValue) {
        for (Map<String, Object> resultMap : resultMapList) {
            if (fieldValue.equals(resultMap.get(fieldName).toString())) {
                return resultMap;
            }
        }
        return null;
    }

    /**
     * 从引用表的返回结果中根据指定字段名、字段值抽取一个匹配列表
     *
     * @param resultMapList
     * @param fieldName
     * @param fieldValues
     * @return
     */
    private List<Map<String, Object>> extractLookupRecordMapListByField(List<Map<String, Object>> resultMapList, String fieldName, List<String> fieldValues) {
        List<Map<String, Object>> targetList = new ArrayList<>();
        for (Map<String, Object> resultMap : resultMapList) {
            if (fieldValues.contains(resultMap.get(fieldName).toString())) {
                targetList.add(resultMap);
            }
        }
        return targetList;
    }

    /**
     * 从结果集中中生成本表的记录ID列表
     *
     * @param resultMapList
     * @param fieldName
     * @return
     */
    private List<String> extractRecordIdsFromResultMapList(List<Map<String, Object>> resultMapList, String fieldName) {
        List<String> columnValues = new ArrayList<>();
        for (Map<String, Object> resultMap : resultMapList) {
            Object columnResult = resultMap.get(fieldName);
            if (columnResult instanceof String) {
                columnResult = DataConvert.stringToList((String) columnResult);
            }

            if (columnResult instanceof List) {
                columnValues.addAll((List) columnResult);
            } else if (columnResult instanceof String) {
                columnValues.add((String) columnResult);
            } else {
                columnValues.add(columnResult.toString());
            }
        }

        return columnValues;
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
    public int create(String oql) {
        return 0;
    }

    @Override
    public int create(String oql, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] createList(String oql, List<Map<String, Object>> dataMaps) {
        return new int[0];
    }

    @Override
    public int modify(String oql) {
        return 0;
    }

    @Override
    public int modify(String oql, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] modifyList(String oql, List<Map<String, Object>> dataMaps) {
        return new int[0];
    }

    @Override
    public int remove(String oql) {
        return 0;
    }

    @Override
    public int remove(String oql, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] removeList(String oql, List<Map<String, Object>> dataMaps) {
        return new int[0];
    }
}
