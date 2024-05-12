package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.FastSqlException;
import net.cf.form.repository.sql.ast.SqlLimit;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAggregateExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlJsonArrayExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelect;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.util.SqlUtils;
import net.cf.object.engine.data.DataConvert;
import net.cf.object.engine.data.PageRequest;
import net.cf.object.engine.data.PageResult;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.infos.*;
import net.cf.object.engine.sql.*;
import net.cf.object.engine.util.OqlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * OQL记录引擎实现
 *
 * @author clouds
 */
public class OqlEngineImpl implements OqlEngine {

    private static Logger LOGGER = LoggerFactory.getLogger(OqlEngineImpl.class);

    private final SqlCmdExecutor executor;

    private final int limitSize = -1;

    public OqlEngineImpl(ObjectRepository repository) {
        this.executor = new SqlCmdExecutor(repository);
    }

    public OqlEngineImpl(ObjectRepository repository, int limitSize) {
        this.executor = new SqlCmdExecutor(repository, limitSize);
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
        OqlSelectInfos selectInfos = OqlInfosUtils.parseOqlSelectInfos(stmt, paramMap, false);
        XObject masterObject = selectInfos.getResolvedMasterObject();

        // 查询本表数据
        SqlSelectCmd masterSelectInfo = selectInfos.getMasterSelectCmd();
        Map<String, Object> selfResultMap = this.executor.selectOne(masterSelectInfo);
        if (selfResultMap == null) {
            LOGGER.debug("未查询到记录，返回NULL！");
            return null;
        }

        // 存在子表的情况下，依次查询子表
        List<SqlSelectCmd> detailSelectCmds = selectInfos.getDetailSelectCmds();
        if (detailSelectCmds != null && detailSelectCmds.size() > 0) {
            // 从主表返回的数据中抽取记录ID
            String masterPrimaryFieldName = masterObject.getPrimaryField().getName();
            String masterRecordId = selfResultMap.get(masterPrimaryFieldName).toString();
            for (SqlSelectCmd detailSelectCmd : detailSelectCmds) {
                XObject detailObject = detailSelectCmd.getResolvedObject();
                // 组装子表的查询条件（where masterField = #{masterField}）所需参数
                Map<String, Object> detailParamMap = detailSelectCmd.getParamMap();
                XObjectRefField detailMasterField = detailObject.getObjectRefField(masterObject.getName());
                String detailMasterFieldName = detailMasterField.getName();
                detailParamMap.put(detailMasterFieldName, masterRecordId);
                // 查询子表的数据并作并归并
                List<Map<String, Object>> detailResultMapList = this.executor.selectList(detailSelectCmd);
                // 将子表归并后的数据追加到主表返回结果中
                String detailResultKey = detailSelectCmd.getObjectRefResultKey();
                if (detailSelectCmd.isDetailFieldDirectQuery()) {
                    String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
                    List<String> detailRecordIds = this.extractRecordIdsFromResultMapList(detailResultMapList, detailPrimaryFieldName);
                    selfResultMap.put(detailResultKey, detailRecordIds);
                } else {
                    selfResultMap.put(detailResultKey, detailResultMapList);
                }
            }
        }

        // 存在相关表的情况下，依次查询相关表
        List<SqlSelectCmd> lookupSelectCmds = selectInfos.getLookupSelectCmds();
        if (lookupSelectCmds != null && lookupSelectCmds.size() > 0) {
            for (SqlSelectCmd lookupSelectCmd : lookupSelectCmds) {
                XObject lookupObject = lookupSelectCmd.getResolvedObject();
                String selfLookupFieldName = lookupSelectCmd.getObjectRefFieldName();
                Object lookupFieldValue = selfResultMap.get(selfLookupFieldName);
                XObjectRefField selfLookupField = (XObjectRefField) masterObject.getField(selfLookupFieldName);
                // 组装子表的查询参数并独立查询子表数据
                Map<String, Object> lookupParamMap = lookupSelectCmd.getParamMap();
                String lookupPrimaryFieldName = lookupObject.getPrimaryField().getName();
                Object lookupResultValue;
                if (selfLookupField.isMultiRef()) {
                    //  where lookupPrimaryField in (#{lookupPrimaryFields})
                    List<String> lookupRecordIds = this.convertToLookupRecordIds(lookupFieldValue);
                    lookupParamMap.put(lookupPrimaryFieldName + 's', lookupRecordIds);
                    // 查询相关表的数据
                    List<Map<String, Object>> lookupResultMapList = this.executor.selectList(lookupSelectCmd);
                    lookupResultValue = lookupResultMapList;
                } else {
                    //  where lookupPrimaryField = #{lookupPrimaryField}
                    String lookupRecordId = this.convertToLookupRecordId(lookupFieldValue);
                    lookupParamMap.put(lookupPrimaryFieldName, lookupRecordId);
                    // 查询相关表的数据
                    Map<String, Object> lookupResultMap = this.executor.selectOne(lookupSelectCmd);
                    lookupResultValue = lookupResultMap;
                }

                // 将相关表归并后的数据追加到主表返回结果中
                String lookupResultKey = lookupSelectCmd.getObjectRefResultKey();
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
        OqlSelectInfos thisOqlStmt = OqlInfosUtils.parseOqlSelectInfos(stmt, paramMap, true);
        return this.queryList(thisOqlStmt, paramMap);
    }

    @Override
    public PageResult<Map<String, Object>> queryPage(OqlSelectStatement stmt, Map<String, Object> paramMap, PageRequest pageRequest) {
        OqlSelectInfos selectInfos = OqlInfosUtils.parseOqlSelectInfos(stmt, true);
        SqlSelectStatement selfSqlStmt = selectInfos.getMasterSelectCmd().getStatement();

        // 生成count语句
        SqlSelectStatement countSqlStmt = this.getCountSqlStmt(selfSqlStmt);
        SqlSelectCmd selectCmd = new SqlSelectCmd();
        selectCmd.setResolvedObject(selectInfos.getResolvedMasterObject());
        selectCmd.setStatement(countSqlStmt);
        selectCmd.setParamMap(paramMap);

        Map<String, Object> countResultMap = this.executor.selectOne(selectCmd);
        long total = (Long) countResultMap.values().stream().findFirst().orElse(0);

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
     * @param selectInfos
     * @return
     */
    private List<Map<String, Object>> queryList(OqlSelectInfos selectInfos, Map<String, Object> paramMap) {
        // 当前模型
        XObject selfObject = selectInfos.getResolvedMasterObject();

        // 查询主表数据
        List<Map<String, Object>> masterResultMapList = this.executor.selectList(selectInfos.getMasterSelectCmd());

        // 存在子表的情况下，依次查询子表
        List<SqlSelectCmd> detailSelectCmds = selectInfos.getDetailSelectCmds();
        if (detailSelectCmds != null && detailSelectCmds.size() > 0) {
            String selfPrimaryFieldName = selfObject.getPrimaryField().getName();
            List<String> selfRecordIds = this.extractRecordIdsFromResultMapList(masterResultMapList, selfPrimaryFieldName);
            for (SqlSelectCmd detailSelectCmd : detailSelectCmds) {
                XObject detailObject = detailSelectCmd.getResolvedObject();
                // 组装子表的查询条件（where masterField in (#{masterFields}))所需参数
                Map<String, Object> detailParamMap = detailSelectCmd.getParamMap();
                XObjectRefField detailMasterField = detailObject.getObjectRefField(selfObject.getName());
                String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
                String detailMasterFieldName = detailMasterField.getName();
                detailParamMap.put(detailMasterFieldName + 's', selfRecordIds);
                // 查询子表的数据并作归并
                List<Map<String, Object>> detailResultMapList = this.executor.selectList(detailSelectCmd);
                // 将子表归并后的数据按主表记录ID分组追加到主表返回结果中
                String detailResultKey = detailSelectCmd.getObjectRefResultKey();
                for (Map<String, Object> selfResultMap : masterResultMapList) {
                    String masterId = selfResultMap.get(selfPrimaryFieldName).toString();
                    if (detailSelectCmd.isDetailFieldDirectQuery()) {
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
        List<SqlSelectCmd> lookupSelectCmds = selectInfos.getLookupSelectCmds();
        if (lookupSelectCmds != null && lookupSelectCmds.size() > 0) {
            for (SqlSelectCmd lookupSelectCmd : lookupSelectCmds) {
                XObject lookupObject = lookupSelectCmd.getResolvedObject();
                String selfLookupFieldName = lookupSelectCmd.getObjectRefFieldName();
                XObjectRefField selfLookupField = (XObjectRefField) selfObject.getField(selfLookupFieldName);

                // 组装子表的查询参数并独立查询子表数据并作归并
                Map<String, Object> lookupParamMap = lookupSelectCmd.getParamMap();
                List<String> lookupRecordIds = this.extractRecordIdsFromResultMapList(masterResultMapList, selfLookupFieldName);
                String lookupPrimaryFieldName = lookupObject.getPrimaryField().getName();
                lookupParamMap.put(lookupPrimaryFieldName + "s", lookupRecordIds);
                List<Map<String, Object>> lookupResultMapList = this.executor.selectList(lookupSelectCmd);
                if (CollectionUtils.isEmpty(lookupResultMapList)) {
                    continue;
                }

                // 将相关表归并后的数据按主表中相关表的记录ID分组追加到主表返回结果中
                String lookupResultKey = lookupSelectCmd.getObjectRefResultKey();
                if (selfLookupField.isMultiRef()) {
                    for (Map<String, Object> selfResultMap : masterResultMapList) {
                        Object lookupFieldValue = selfResultMap.get(selfLookupFieldName);
                        List<String> recordIds = this.convertToLookupRecordIds(lookupFieldValue);
                        Object lookupResultValue = this.extractLookupRecordMapListByField(lookupResultMapList, lookupPrimaryFieldName, recordIds);
                        selfResultMap.put(lookupResultKey, lookupResultValue);
                    }
                } else {
                    for (Map<String, Object> selfResultMap : masterResultMapList) {
                        Object lookupFieldValue = selfResultMap.get(selfLookupFieldName);
                        String recordId = this.convertToLookupRecordId(lookupFieldValue);
                        Object lookupResultValue = this.extractLookupRecordMapByField(lookupResultMapList, lookupPrimaryFieldName, recordId);
                        selfResultMap.put(lookupResultKey, lookupResultValue);
                    }
                }
            }
        }

        return masterResultMapList;
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
            if (columnResult == null) {
                continue;
            }

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
        OqlInsertInfos insertInfos = OqlInfosUtils.parseOqlInsertInfos(stmt, paramMap);
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
        OqlInsertInfos insertInfos = OqlInfosUtils.parseOqlInsertInfos(stmt, paramMaps);
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
    public int[] createSelect(OqlInsertSelectStatement stmt) {
        return this.createSelect(stmt, Collections.emptyMap());
    }

    @Override
    public int[] createSelect(OqlInsertSelectStatement stmt, Map<String, Object> paramMap) {
        OqlSelect query = stmt.getQuery();
        List<SqlExpr> valueExprs = this.parseInsertValues(stmt.getFields(), query.getSelectItems());

        // 先查询出来

        OqlSelectStatement selectStmt = new OqlSelectStatement(query);
        List<Map<String, Object>> queryResultList = this.queryList(selectStmt);
        if (queryResultList.size() == 0) {
            throw new FastOqlException("插入失败，数据行不存在");
        }


        // 再构建OQL语句
        OqlInsertInto insertInto = new OqlInsertInto();
        insertInto.setObjectSource(stmt.getObjectSource());
        insertInto.addFields(stmt.getFields());
        insertInto.addValues(new SqlInsertStatement.ValuesClause(valueExprs));

        // 再插入数据
        OqlInsertStatement insertStmt = new OqlInsertStatement(insertInto);
        int[] effectedRowsArray = this.createList(insertStmt, queryResultList);
        return effectedRowsArray;
    }

    /**
     * 从 insert ...  select ...中解析values的值
     *
     * @param insertItems
     * @param selectItems
     * @return
     */
    private List<SqlExpr> parseInsertValues(List<OqlExpr> insertItems, List<OqlSelectItem> selectItems) {
        int insertItemSize = insertItems.size();
        int selectItemSize = selectItems.size();
        if (insertItemSize != selectItemSize) {
            throw new FastOqlException("插入的字段与查询的字段个数不一致");
        }

        List<SqlExpr> insertValues = new ArrayList<>();
        for (int i = 0; i < insertItemSize; i++) {
            OqlExpr insertItem = insertItems.get(i);
            OqlSelectItem selectItem = selectItems.get(i);

            SqlExpr selectItemExpr = selectItem.getExpr();
            String alias = selectItem.getAlias();
            if (insertItem instanceof OqlFieldExpr) {
                if (alias != null) {
                    insertValues.add(SqlUtils.buildSqlVariantRefExpr(alias));
                } else {
                    if (selectItemExpr instanceof OqlFieldExpr) {
                        insertValues.add(SqlUtils.buildSqlVariantRefExpr(((OqlFieldExpr) selectItemExpr).getName()));
                    } else if (selectItemExpr instanceof SqlValuableExpr) { // 非模型字段，如：常量
                        String resultKey = alias != null ? alias : OqlUtils.getSelectItemIndexAlias(i);
                        insertValues.add(SqlUtils.buildSqlVariantRefExpr(resultKey));
                    } else {
                        throw new FastSqlException("查询字段" + OqlUtils.expr2String(selectItemExpr) + "必须配置as别名");
                    }
                }
            } else if (insertItem instanceof OqlObjectExpandExpr) {
                if (selectItemExpr instanceof OqlObjectExpandExpr) {
                    String selectItemFieldName = ((OqlObjectExpandExpr) selectItemExpr).getOwner();
                    SqlVariantRefExpr varRefExpr = SqlUtils.buildSqlVariantRefExpr(alias != null ? alias : selectItemFieldName);
                    List<SqlExpr> expandFields = ((OqlObjectExpandExpr) selectItemExpr).getFields();
                    String subVarName;
                    for (SqlExpr expandField : expandFields) {
                        if (expandField instanceof OqlFieldExpr) {
                            subVarName = ((OqlFieldExpr) expandField).getName();
                        } else if (expandField instanceof SqlSelectItem) {
                            subVarName = ((SqlSelectItem) expandField).getAlias();
                        } else {
                            subVarName = OqlUtils.expr2String(expandField);
                        }
                        varRefExpr.addSubVarName(subVarName);
                    }
                    insertValues.add(varRefExpr);
                } else if (selectItemExpr instanceof SqlJsonArrayExpr) { // 常量会通过OQL查询直接返回
                    String resultKey = alias != null ? alias : OqlUtils.getSelectItemIndexAlias(i);
                    insertValues.add(SqlUtils.buildSqlVariantRefExpr(resultKey));
                } else {
                    throw new FastSqlException("查询字段" + OqlUtils.expr2String(selectItemExpr) + "语法不支持");
                }
            } else {
                throw new FastSqlException("插入字段" + OqlUtils.expr2String(insertItem) + "语法不支持");
            }
        }

        return insertValues;
    }

    @Override
    public int modify(OqlUpdateStatement stmt) {
        return this.modify(stmt, new HashMap<>());
    }

    @Override
    public int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        // OQL语句解析
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(stmt, paramMap);

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
        OqlUpdateInfos updateInfos = OqlInfosUtils.parseOqlUpdateInfos(stmt, paramMaps);

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
        Map<XObject, SqlDeleteCmd> detailNotInDeleteCmds = updateInfos.getDetailNotInDeleteCmds();
        Map<XObject, SqlInsertCmd> detailInsertCmds = updateInfos.getDetailInsertCmds();
        Map<XObject, SqlUpdateCmd> detailUpdateCmds = updateInfos.getDetailUpdateCmds();
        for (XObject detailObject : detailObjects) {
            // 先批量删除被删除的记录（不含not in 条件）
            if (detailDeleteCmds != null) {
                SqlDeleteCmd deleteCmd = detailDeleteCmds.get(detailObject);
                if (deleteCmd != null) {
                    this.executor.batchDelete(deleteCmd);
                }
            }

            // 先批量删除被删除的记录
            if (detailNotInDeleteCmds != null) {
                SqlDeleteCmd deleteCmd = detailNotInDeleteCmds.get(detailObject);
                if (deleteCmd != null) {
                    this.executor.batchDelete(deleteCmd);
                }
            }

            // 再批量插入新添加的记录
            if (detailInsertCmds != null) {
                SqlInsertCmd insertCmd = detailInsertCmds.get(detailObject);
                if (insertCmd != null) {
                    this.executor.batchInsert(insertCmd);
                }
            }

            // 再批量更新有变更的记录
            if (detailUpdateCmds != null) {
                SqlUpdateCmd updateCmd = detailUpdateCmds.get(detailObject);
                if (updateCmd != null) {
                    this.executor.batchUpdate(updateCmd);
                }
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
        OqlDeleteInfos deleteInfos = OqlInfosUtils.parseOqlDeleteInfos(stmt, paramMap);

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
        OqlDeleteInfos deleteInfos = OqlInfosUtils.parseOqlDeleteInfos(stmt, paramMaps);

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
    }

}
