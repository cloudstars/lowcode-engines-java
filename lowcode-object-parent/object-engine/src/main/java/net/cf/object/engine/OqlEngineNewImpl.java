package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAggregateExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.object.engine.data.*;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlInsertStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oqlnew.cmd.*;
import net.cf.object.engine.oqlnew.sql.SqlCmdExecutor;
import net.cf.object.engine.oqlnew.sql.SqlDeleteCmd;
import net.cf.object.engine.oqlnew.sql.SqlInsertCmd;
import net.cf.object.engine.oqlnew.sql.SqlUpdateCmd;
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

    private static Logger LOGGER = LoggerFactory.getLogger(OqlEngineNewImpl.class);

    private final ObjectRepository repository;

    private final SqlCmdExecutor executor;

    private final int limitSize = -1;

    public OqlEngineNewImpl(ObjectRepository repository) {
        this.repository = repository;
        this.executor = new SqlCmdExecutor(repository);
    }

    public int getLimitSize() {
        return limitSize;
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt) {
        return this.queryOne(stmt, Collections.emptyMap());
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        OqlSelectInfos selectInfos = OqlStatementUtils.parseOqlSelectInfos(stmt, false);
        XObject selfObject = selectInfos.getResolvedMasterObject();

        // 查询本表数据
        OqlSelectInfo selfSelectInfo = selectInfos.getSelfSelectInfo();
        SqlSelectStatement selfSqlStmt = selfSelectInfo.getStatement();
        this.addLimitInfo(selfSqlStmt);
        Map<String, Object> selfResultMap = this.repository.selectOne(selfSqlStmt, paramMap);
        if (selfResultMap != null) {
            DefaultResultReducer resultReducer = new DefaultResultReducer(selfSelectInfo.getFieldMappings());
            selfResultMap = this.convertResultMap(resultReducer, selfResultMap);
            LOGGER.info("成功返回1条记录！");
        } else {
            LOGGER.info("未查询到记录，返回0条！");
            return null;
        }

        // 存在子表的情况下，依次查询子表
        List<OqlSelectInfo> detailSelectInfos = selectInfos.getDetailSelectInfos();
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
        List<OqlSelectInfo> lookupSelectInfos = selectInfos.getLookupSelectInfos();
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
    private List<String> convertToLookupRecordIds(final Object lookupFieldValue) {
        Object lookupFieldValueX = lookupFieldValue;
        if (lookupFieldValueX instanceof String) {
            lookupFieldValueX = DataConvert.stringToList((String) lookupFieldValueX);
        }

        if (lookupFieldValueX instanceof List) {
            List<?> lookupFieldListValue = ((List<?>) lookupFieldValueX);
            if (lookupFieldListValue.size() > 0 && !(lookupFieldListValue.get(0) instanceof String)) {
                // 如果不是字符串列表的话，则转话字符串列表
                List<String> lookupFieldListStringValue = new ArrayList<>();
                for (Object lookupFieldItem : (List) lookupFieldValueX) {
                    lookupFieldListStringValue.add(lookupFieldItem.toString());
                }
                return lookupFieldListStringValue;
            } else {
                return (List<String>) lookupFieldValueX;
            }
        } else if (lookupFieldValueX instanceof String) {
            return Arrays.asList((String) lookupFieldValueX);
        } else {
            return Arrays.asList(lookupFieldValueX.toString());
        }
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt) {
        return this.queryList(stmt, Collections.emptyMap());
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        OqlSelectInfos thisOqlStmt = OqlStatementUtils.parseOqlSelectInfos(stmt, true);
        return this.queryList(thisOqlStmt, paramMap);
    }

    @Override
    public PageResult<Map<String, Object>> queryPage(OqlSelectStatement stmt, Map<String, Object> paramMap, PageRequest pageRequest) {
        OqlSelectInfos selectInfos = OqlStatementUtils.parseOqlSelectInfos(stmt, true);
        SqlSelectStatement selfSqlStmt = selectInfos.getSelfSelectInfo().getStatement();

        // 生成count语句
        SqlSelectStatement countSqlStmt = this.getCountSqlStmt(selfSqlStmt);
        Map<String, Object> countResultMap = this.repository.selectOne(countSqlStmt, paramMap);
        int total = (Integer) countResultMap.get("total");

        // 添加分页信息后查询当前页数据
        this.addPageInfo(selfSqlStmt, pageRequest);
        List<Map<String, Object>> list = this.queryList(selectInfos, paramMap);

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
     *
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
     * @param selfOqlStmt
     * @return
     */
    private List<Map<String, Object>> queryList(OqlSelectInfos selfOqlStmt, Map<String, Object> paramMap) {
        // 当前模型
        XObject selfObject = selfOqlStmt.getResolvedMasterObject();

        // 查询本表数据
        OqlSelectInfo selfSelectInfo = selfOqlStmt.getSelfSelectInfo();
        SqlSelectStatement selfSqlStmt = selfSelectInfo.getStatement();
        this.addLimitInfo(selfSqlStmt); // 添加条数限制
        List<Map<String, Object>> selfResultMapList = this.repository.selectList(selfSqlStmt, paramMap);
        if (selfResultMapList != null && selfResultMapList.size() > 0) {
            DefaultResultReducer resultReducer = new DefaultResultReducer(selfSelectInfo.getFieldMappings());
            selfResultMapList = this.convertResultMapList(resultReducer, selfResultMapList);
            LOGGER.info("成功返回" + selfResultMapList.size() + "条记录！");
        } else {
            LOGGER.info("未查询到记录，返回0条！");
            return Collections.emptyList();
        }

        // 存在子表的情况下，依次查询子表
        List<OqlSelectInfo> detailSelectInfos = selfOqlStmt.getDetailSelectInfos();
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
                this.addLimitInfo(detailSqlStmt); // 添加条数限制
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
        List<OqlSelectInfo> lookupSelectInfos = selfOqlStmt.getLookupSelectInfos();
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
                this.addLimitInfo(lookupSqlStmt); // 添加条数限制
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
     * 添加分页信息
     *
     * @param sqlStmt
     */
    private void addLimitInfo(SqlSelectStatement sqlStmt) {
        if (this.limitSize > 0) {
            SqlSelect select = sqlStmt.getSelect();
            SqlLimit limit = select.getLimit();
            if (limit == null) {
                limit = new SqlLimit();
                select.setLimit(limit);
            }
            limit.setRowCount(limitSize);
        }
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

    @Override
    public int create(OqlInsertStatement stmt) {
        return this.create(stmt, new HashMap<>());
    }

    @Override
    public int create(OqlInsertStatement stmt, Map<String, Object> paramMap) {
        OqlInsertInfos insertInfos = OqlStatementUtils.parseOqlInsertInfos(stmt, paramMap);
        XObject masterObject = insertInfos.getResolvedMasterObject();
        String masterObjectName = masterObject.getName();
        XField masterPrimaryField = masterObject.getPrimaryField();
        String masterPrimaryFieldName = masterPrimaryField.getName();

        // 插入主表
        SqlInsertCmd masterInsertCmd = insertInfos.getMasterInsertCmd();
        int effectedRows = this.executor.insert(masterInsertCmd);
        Object masterAutoFieldValue = null;
        if (masterPrimaryField.isAutoGen()) {
            masterAutoFieldValue = masterInsertCmd.getParamMap().get(masterPrimaryFieldName);
            paramMap.put(masterPrimaryFieldName, masterAutoFieldValue);
        }

        // 不存在子表时直接返回
        List<XObject> detailObjects = insertInfos.getResolvedDetailObjects();
        if (CollectionUtils.isEmpty(detailObjects)) {
            return effectedRows;
        }

        // 循环插入子表
        Map<XObject, SqlInsertCmd> detailInsertCmds = insertInfos.getDetailInsertCmds();
        for (XObject detailObject : detailObjects) {
            SqlInsertCmd detailInsertCmd = detailInsertCmds.get(detailObject);

            // 如果存在自动生成主键的情况下，将主表插入时返回的自增主键的值添加到每一个子表参数中
            if (masterPrimaryField.isAutoGen()) {
                List<Map<String, Object>> detailParamMapList = detailInsertCmd.getParamMaps();
                String detailObjectMasterFieldName = detailObject.getObjectRefField(masterObjectName).getName();
                for (Map<String, Object> subParamMap : detailParamMapList) {
                    subParamMap.put(detailObjectMasterFieldName, masterAutoFieldValue);
                }
            }

            // 执行子表插入指令
            this.executor.executeInsert(detailInsertCmd);
        }

        return effectedRows;
    }

    @Override
    public int[] createList(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        OqlInsertInfos insertInfos = OqlStatementUtils.parseOqlInsertInfos(stmt, paramMaps);
        XObject masterObject = insertInfos.getResolvedMasterObject();

        // 批量插入本表
        SqlInsertCmd mainInsertCmd = insertInfos.getMasterInsertCmd();
        int[] effectedRowsArray = this.executor.batchInsert(mainInsertCmd);

        // 不存在子表时直接返回
        List<XObject> detailObjects = insertInfos.getResolvedDetailObjects();
        if (CollectionUtils.isEmpty(detailObjects)) {
            return effectedRowsArray;
        }

        // 循环批量插入子表
        String masterObjectName = masterObject.getName();
        XField masterPrimaryField = masterObject.getPrimaryField();
        String masterPrimaryFieldName = masterPrimaryField.getName();
        Map<XObject, SqlInsertCmd> detailInsertCmds = insertInfos.getDetailInsertCmds();
        for (XObject detailObject : detailObjects) {
            // 获取子表的插入指令
            SqlInsertCmd detailInsertCmd = detailInsertCmds.get(detailObject);

            // 如果存在自增主键的情况下，将主表插入时返回的自增主键的值按参数的顺序添加到每一个子表参数中
            List<Map<String, Object>> detailParamMapList = detailInsertCmd.getParamMaps();
            if (masterPrimaryField.isAutoGen()) {
                String detailObjectMasterFieldName = detailObject.getObjectRefField(masterObjectName).getName();
                for (Map<String, Object> subParamMap : detailParamMapList) {
                    int paramIndex = (Integer) subParamMap.get(AbstractOqlInfos.PARAM_INDEX);
                    Object masterAutoId = paramMaps.get(paramIndex).get(masterPrimaryFieldName);
                    subParamMap.put(detailObjectMasterFieldName, masterAutoId);
                }
            }

            // 批量插入子表数据
            this.executor.batchInsert(detailInsertCmd);
        }

        return effectedRowsArray;
    }

    @Override
    public int modify(OqlUpdateStatement stmt) {
        return this.modify(stmt, new HashMap<>());
    }

    @Override
    public int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        // OQL语句解析
        OqlUpdateInfos updateInfos = OqlStatementUtils.parseOqlUpdateInfos(stmt, paramMap);

        // 更新本表
        SqlUpdateCmd masterUpdateCmd = updateInfos.getMasterUpdateCmd();
        int effectedRows = this.executor.update(masterUpdateCmd);

        // 不存在子表时直接返回
        List<XObject> detailObjects = updateInfos.getResolvedDetailObjects();
        if (CollectionUtils.isEmpty(detailObjects)) {
            return effectedRows;
        }

        // 循环处理子表
        Map<XObject, SqlDeleteCmd> detailDeleteCmds = updateInfos.getDetailDeleteCmds();
        Map<XObject, SqlInsertCmd> detailInsertCmds = updateInfos.getDetailInsertCmds();
        Map<XObject, SqlUpdateCmd> detailUpdateCmds = updateInfos.getDetailUpdateCmds();
        for (XObject detailObject : detailObjects) {
            // 先删除被删除的记录
            SqlDeleteCmd deleteCmd = detailDeleteCmds.get(detailObject);
            if (deleteCmd != null) {
                this.executor.delete(deleteCmd);
            }

            // 再批量插入新添加的记录
            SqlInsertCmd insertCmd = detailInsertCmds.get(detailObject);
            if (insertCmd != null) {
                this.executor.batchInsert(insertCmd);
            }

            // 再批量更新有变更的记录
            SqlUpdateCmd updateCmd = detailUpdateCmds.get(detailObject);
            if (updateCmd != null) {
                this.executor.batchUpdate(updateCmd);
            }
        }

        return effectedRows;
    }

    @Override
    public int[] modifyList(OqlUpdateStatement stmt, List<Map<String, Object>> paramMaps) {
        // OQL语句解析
        OqlUpdateInfos updateInfos = OqlStatementUtils.parseOqlUpdateInfos(stmt, paramMaps);

        // 批量更新本表
        SqlUpdateCmd masterUpdateCmd = updateInfos.getMasterUpdateCmd();
        int[] effectedRowsArray = this.executor.batchUpdate(masterUpdateCmd);

        // 不存在子表时直接返回
        List<XObject> detailObjects = updateInfos.getResolvedDetailObjects();
        if (CollectionUtils.isEmpty(detailObjects)) {
            return effectedRowsArray;
        }

        // 循环处理子表
        Map<XObject, SqlDeleteCmd> detailDeleteCmds = updateInfos.getDetailDeleteCmds();
        Map<XObject, SqlInsertCmd> detailInsertCmds = updateInfos.getDetailInsertCmds();
        Map<XObject, SqlUpdateCmd> detailUpdateCmds = updateInfos.getDetailUpdateCmds();
        for (XObject detailObject : detailObjects) {
            // 先批量删除被删除的记录
            SqlDeleteCmd deleteCmd = detailDeleteCmds.get(detailObject);
            if (deleteCmd != null) {
                this.executor.batchDelete(deleteCmd);
            }

            // 再批量插入新添加的记录
            SqlInsertCmd insertCmd = detailInsertCmds.get(detailObject);
            if (insertCmd != null) {
                this.executor.batchInsert(insertCmd);
            }

            // 再批量更新有变更的记录
            SqlUpdateCmd updateCmd = detailUpdateCmds.get(detailObject);
            if (updateCmd != null) {
                this.executor.batchUpdate(updateCmd);
            }
        }

        return effectedRowsArray;
    }

    @Override
    public int remove(OqlDeleteStatement stmt) {
        return this.remove(stmt, new HashMap<>());
    }

    @Override
    public int remove(OqlDeleteStatement stmt, Map<String, Object> paramMap) {
        // OQL语句解析
        OqlDeleteInfos deleteInfos = OqlStatementUtils.parseOqlDeleteInfos(stmt, paramMap);

        // 删除主表
        SqlDeleteCmd masterDeleteCmd = deleteInfos.getMasterDeleteCmd();
        int effectedRows = this.executor.delete(masterDeleteCmd);

        // 不存在子表时直接返回
        List<XObject> detailObjects = deleteInfos.getResolvedDetailObjects();
        if (CollectionUtils.isEmpty(detailObjects)) {
            return effectedRows;
        }

        // 循环删除子表数据
        Map<XObject, SqlDeleteCmd> detailDeleteCmds = deleteInfos.getDetailDeleteCmds();
        for (XObject detailObject : detailObjects) {
            SqlDeleteCmd deleteCmd = detailDeleteCmds.get(detailObject);
            this.executor.delete(deleteCmd);
        }

        return effectedRows;
    }

    @Override
    public int[] removeList(OqlDeleteStatement stmt, List<Map<String, Object>> paramMaps) {
        // OQL语句解析
        OqlDeleteInfos deleteInfos = OqlStatementUtils.parseOqlDeleteInfos(stmt, paramMaps);

        // 删除主表
        SqlDeleteCmd masterDeleteCmd = deleteInfos.getMasterDeleteCmd();
        int[] effectedRowsArray = this.executor.batchDelete(masterDeleteCmd);

        // 不存在子表时直接返回
        List<XObject> detailObjects = deleteInfos.getResolvedDetailObjects();
        if (CollectionUtils.isEmpty(detailObjects)) {
            return effectedRowsArray;
        }

        // 循环删除子表数据
        Map<XObject, SqlDeleteCmd> detailDeleteCmds = deleteInfos.getDetailDeleteCmds();
        for (XObject detailObject : detailObjects) {
            SqlDeleteCmd deleteCmd = detailDeleteCmds.get(detailObject);
            this.executor.batchDelete(deleteCmd);
        }

        return effectedRowsArray;

        // 循环删除子表数据
        /*for (OqlDeleteCmd detailDeleteInfo : detailDeleteInfos) {
            // 计算主表记录ID
            SqlExpr selfPrimaryIdExpr = deleteInfos.getMainPrimaryIdExpr();
            XObject detailObject = detailDeleteInfo.getResolvedObject();
            XObjectRefField masterRefField = detailObject.getObjectRefField(mainObject.getName());
            String masterFieldName = masterRefField.getName();

            // 添加主表记录ID
            List<Map<String, Object>> detailDeleteParamMaps = detailDeleteInfo.getParamMaps();
            for (Map<String, Object> detailDeleteParamMap : detailDeleteParamMaps) {
                int paramIndex = (Integer) detailDeleteParamMap.get(AbstractOqlInfos.PARAM_INDEX);
                Object masterId = this.extractMasterId(selfPrimaryIdExpr, paramMaps.get(paramIndex));
                detailDeleteParamMap.put(masterFieldName, masterId);
            }

            SqlDeleteStatement detailDeleteSqlStmt = detailDeleteInfo.getStatement();
            this.repository.batchDelete(detailDeleteSqlStmt, detailDeleteParamMaps);
        }*/
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

    /**
     * 转换输入参数，将paramMap中的key（字段名）转换为列名
     *
     * @param paramMapper
     * @param paramMapList
     * @return 生成新的对象返回
     */
    /*private List<Map<String, Object>> convertParameterMaps(ParameterMapper paramMapper, List<Map<String, Object>> paramMapList) {
        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (Map<String, Object> paramaeterMap : paramMapList) {
            targetMapList.add(this.convertParameterMap(paramMapper, paramaeterMap));
        }
        return targetMapList;
    }*/

    /**
     * 转换输入参数，将paramMap中的key（字段名）转换为列名
     *
     * @param paramMapper
     * @param paramMap
     * @return 生成新的对象返回
     */
    /*private Map<String, Object> convertParameterMap(ParameterMapper paramMapper, Map<String, Object> paramMap) {
        return paramMapper.mapParameter(paramMap);
    }*/

    /**
     * 汇总总影响行数
     *
     * @param effectedRowsArray
     * @return
     */
    /*private int sumAndLogsumEffectedRows(int[] effectedRowsArray, OqlStatement stmt) {
        int totalEffectedRows = 0;
        for (int i = 0, l = effectedRowsArray.length; i < l; i++) {
            totalEffectedRows += effectedRowsArray[i];
        }

        if (totalEffectedRows == 0) {
            LOGGER.warn("OQL批量执行异常，总影响行数：0，OQL：{}", stmt);
        } else {
            LOGGER.warn("OQL批量执行成功，总影响行数：{}，OQL：{}", totalEffectedRows, stmt);
        }

        return totalEffectedRows;
    }*/

    /**
     * 根据主表ID表达式和入参中抽取主表记录ID
     *
     * @param masterIdExpr
     * @param paramMap
     * @return
     */
    /*private Object extractMasterId(SqlExpr masterIdExpr, Map<String, Object> paramMap) {
        if (masterIdExpr == null) {
            throw new FastOqlException("主表记录ID的值不存在");
        }

        if (masterIdExpr instanceof SqlValuableExpr) {
            return ((SqlValuableExpr) masterIdExpr).getValue();
        } else if (masterIdExpr instanceof SqlVariantRefExpr) {
            String varName = ((SqlVariantRefExpr) masterIdExpr).getVarName();
            return paramMap.get(varName);
        } else {
            throw new FastOqlException("主表记录ID的值计算出错");
        }
    }*/

}
