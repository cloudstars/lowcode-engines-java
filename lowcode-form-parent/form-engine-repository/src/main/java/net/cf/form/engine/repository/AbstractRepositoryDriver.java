package net.cf.form.engine.repository;

import com.alibaba.fastjson.JSON;
import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.MasterDataField;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlValuableExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlVariantRefExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlInOpExpr;
import net.cf.form.engine.repository.oql.ast.statement.*;
import net.cf.form.engine.repository.oql.util.OqlExprUtils;
import net.cf.form.engine.repository.sql_bak.*;
import net.cf.form.engine.repository.oql.ast.statement.*;
import net.cf.form.engine.repository.sql_bak.*;

import java.util.*;

/**
 * 抽象的存储层驱动
 *
 * @author clouds
 */
@Deprecated
public abstract class AbstractRepositoryDriver implements RepositoryDriver {

    private final List<StatementExecuteInterceptor> interceptors = new ArrayList<>();

    public AbstractRepositoryDriver() {
    }

    public AbstractRepositoryDriver(List<StatementExecuteInterceptor> interceptors) {
        if (interceptors != null) {
            this.interceptors.addAll(interceptors);
        }
    }

    @Override
    public int insert(OqlInsertStatement statement) {
        return this.insert(statement, null);
    }

    @Override
    public int insert(OqlInsertStatement statement, Map<String, Object> paramMap) {
        for (StatementExecuteInterceptor interceptor : interceptors) {
            interceptor.beforeInsert(statement, paramMap);
        }

        // 插入主表记录，并返回主键数组
        InsertSqlInfo sqlInfo = this.parseInsertSqlInfo(statement);
        Object[] recordIds = this.insertSelf(sqlInfo, paramMap);

        // 根据主表的记录插入的数量来循环处理
        int valuesSize = statement.getInsertInto().getValuesList().size();
        Map<String, InsertDetailSqlInfo> detailOqlInfoMap = sqlInfo.getDetailSqlInfoMap();
        for (Map.Entry<String, InsertDetailSqlInfo> entry : detailOqlInfoMap.entrySet()) {
            InsertDetailSqlInfo detailOqlInfo = entry.getValue();
            DataObject detailObject = detailOqlInfo.getObject();

            // 添加主对象字段
            List<QqlExpr> detailFields = detailOqlInfo.getFields();
            String detailMasterFieldName = detailObject.getMasterField().getName();
            detailFields.add(0, new OqlIdentifierExpr(detailMasterFieldName));

            // 处理子对象的值
            List<QqlExpr> detailValuesList = detailOqlInfo.getValuesList();
            List<OqlInsertValues> detailLiteralValuesList = new ArrayList<>();
            List<OqlInsertValues> detailVariableValuesList = new ArrayList<>();
            List<Map<String, Object>> detailVariableParamList = new ArrayList<>();
            this.processInsertDetailValuesList(detailValuesList, recordIds, paramMap, detailMasterFieldName, detailLiteralValuesList, detailVariableValuesList, detailVariableParamList);

            this.insertDetailValues(detailObject, detailFields, detailLiteralValuesList, detailVariableValuesList, detailVariableParamList);
        }

        return recordIds.length;
    }

    @Override
    public int[] batchInsert(OqlInsertStatement statement, List<Map<String, Object>> paramMapList) {
        for (StatementExecuteInterceptor interceptor : interceptors) {
            interceptor.beforeInsert(statement, paramMapList);
        }

        // 插入主表记录，并返回主键数组
        InsertSqlInfo sqlInfo = this.parseInsertSqlInfo(statement);
        Object[] recordIds = this.insertSelf(sqlInfo, paramMapList);

        // 根据主表的记录插入的量来循环处理
        int valuesSize = statement.getInsertInto().getValuesList().size();
        Map<String, InsertDetailSqlInfo> detailOqlInfoMap = sqlInfo.getDetailSqlInfoMap();
        for (Map.Entry<String, InsertDetailSqlInfo> entry : detailOqlInfoMap.entrySet()) {
            InsertDetailSqlInfo detailOqlInfo = entry.getValue();
            DataObject detailObject = detailOqlInfo.getObject();

            // 添加主对象字段
            List<QqlExpr> detailFields = detailOqlInfo.getFields();
            String detailMasterFieldName = detailObject.getMasterField().getName();
            detailFields.add(0, new OqlIdentifierExpr(detailMasterFieldName));

            // 处理子对象的值
            List<QqlExpr> detailValuesList = detailOqlInfo.getValuesList();
            List<OqlInsertValues> detailLiteralValuesList = new ArrayList<>();
            List<OqlInsertValues> detailVariableValuesList = new ArrayList<>();
            List<Map<String, Object>> detailVariableParamList = new ArrayList<>();
            int startRecordIndex = 0;
            for (Map<String, Object> paramMap : paramMapList) {
                Object[] currentRecordIds = new Object[valuesSize];
                for (int i = 0; i < valuesSize; i++) {
                    currentRecordIds[i] = recordIds[startRecordIndex + i];
                }
                this.processInsertDetailValuesList(detailValuesList, currentRecordIds, paramMap, detailMasterFieldName, detailLiteralValuesList, detailVariableValuesList, detailVariableParamList);
                startRecordIndex += valuesSize;
            }
            this.insertDetailValues(detailObject, detailFields, detailLiteralValuesList, detailVariableValuesList, detailVariableParamList);
        }

        int[] effectedRows = new int[recordIds.length];
        for (int i = 0; i < recordIds.length; i++) {
            effectedRows[i] = 1;
        }
        return effectedRows;
    }

    /**
     * 处理子表的值列表，将处理后的结果存入detailLiteralValuesList、 detailVariableValuesList、detailVariableParamList三个变量中
     *
     * @param detailValuesList         子表的值列表
     * @param recordIds                主表插入后生成的记录ID数组
     * @param paramMap                 主表插入时输入的参数
     * @param detailMasterFieldName    子表的主表字段名称
     * @param detailLiteralValuesList  子表中以常量的方式插入的列表
     * @param detailVariableValuesList 子表中以变量的方式插入的列表
     * @param detailVariableParamList  子表中以变量的方式插入的列表所需要的参数列表
     */
    private void processInsertDetailValuesList(List<QqlExpr> detailValuesList, Object[] recordIds, Map<String, Object> paramMap, String detailMasterFieldName, List<OqlInsertValues> detailLiteralValuesList, List<OqlInsertValues> detailVariableValuesList, List<Map<String, Object>> detailVariableParamList) {
        // 将子对象的数据分成两部分，一部分是常量，一部分是变量
        for (int i = 0, l = recordIds.length; i < l; i++) {
            QqlExpr detailValues = detailValuesList.get(i);
            QqlExpr recordIdExpr = OqlExprUtils.parseObject(recordIds[i]);
            if (detailValues instanceof OqlJsonArrayExpr) {
                // [(d1, d2, ...), (d1, d2, ...)]
                OqlJsonArrayExpr detailValuesExpr = (OqlJsonArrayExpr) detailValues;
                for (QqlExpr itemExpr : detailValuesExpr.getItems()) {
                    OqlListExpr detailListExpr = (OqlListExpr) itemExpr;
                    detailListExpr.addItem(0, recordIdExpr);
                    detailLiteralValuesList.add(new OqlInsertValues(detailListExpr.getItems()));
                }
            } else if (detailValues instanceof OqlMethodInvokeExpr) {
                // detail(#{d1}, #{d2}, ...)
                OqlMethodInvokeExpr detailValuesExpr = (OqlMethodInvokeExpr) detailValues;
                if (detailVariableValuesList.size() == 0) {
                    // 仅允许添加一个，即主表有多个values带子表，那么子表的元素必须相同，即：detailName1(#{d1}, #{d2})、detailName2(#{d1}, #{d2})括号中的参数名必须是一致的
                    detailValuesExpr.addArgument(0, new OqlVariantRefExpr(detailMasterFieldName, true));
                    detailVariableValuesList.add(new OqlInsertValues(detailValuesExpr.getArguments()));
                }
                if (paramMap != null) {
                    String variableName = detailValuesExpr.getMethodName();
                    Object detailFieldValues = paramMap.get(variableName);
                    assert (detailFieldValues instanceof List);
                    for (Map<String, Object> detailFieldValue : (List<Map<String, Object>>) detailFieldValues) {
                        detailFieldValue.put(detailMasterFieldName, recordIds[i]);
                    }
                    detailVariableParamList.addAll((List) detailFieldValues);
                }
            }
        }
    }

    /**
     * 插入子表的值
     *
     * @param detailObject             子对象
     * @param detailFields             子对象插入的字段
     * @param detailLiteralValuesList  子表中以常量的方式插入的列表
     * @param detailVariableValuesList 子表中以变量的方式插入的列表
     * @param detailVariableParamList  子表中以变量的方式插入的列表所需要的参数列表
     */
    private void insertDetailValues(DataObject detailObject, List<QqlExpr> detailFields, List<OqlInsertValues> detailLiteralValuesList, List<OqlInsertValues> detailVariableValuesList, List<Map<String, Object>> detailVariableParamList) {
        // 将常量的子表的值合并后作为一条SQL插入
        OqlObjectSource detailObjectSource = new OqlExprObjectSource(detailObject.getName());
        if (detailLiteralValuesList.size() > 0) {
            OqlInsertInto insertInto = new OqlInsertInto();
            insertInto.setObjectSource(detailObjectSource);
            insertInto.addFields(detailFields);
            insertInto.addValuesList(detailLiteralValuesList);
            this.insert(new OqlInsertStatement(insertInto));
        }

        // 将变量的子表的值合并调用一个批量插入
        if (detailVariableValuesList.size() > 0) {
            OqlInsertInto insertInto = new OqlInsertInto();
            insertInto.setObjectSource(detailObjectSource);
            insertInto.addFields(detailFields);
            insertInto.addValuesList(detailVariableValuesList);
            this.batchInsert(new OqlInsertStatement(insertInto), detailVariableParamList);
        }
    }

    /**
     * 添加主表字段
     *
     * @param statement
     * @param masterFieldName
     * @param recordId
     * @param valuesList
     */
    private void addInsertMasterFieldValue(OqlInsertStatement statement, String masterFieldName, Object recordId, List<Map<String, Object>> valuesList) {
        OqlInsertInto insert = statement.getInsertInto();
        List<OqlInsertValues> insertValuesList = insert.getValuesList();
        for (OqlInsertValues insertValues : insertValuesList) {
            if (valuesList != null) {// 带参数
                insertValues.addValue(0, new OqlVariantRefExpr(masterFieldName, true));
            } else {
                insertValues.addValue(0, OqlExprUtils.parseObject(recordId));
            }
        }

        if (valuesList != null) {// 带参数
            for (Map<String, Object> values : valuesList) {
                values.put(masterFieldName, recordId);
            }
        }
    }

    /**
     * 准备子对象的OQL语句
     *
     * @param statement
     * @return
     */
    public abstract InsertSqlInfo parseInsertSqlInfo(OqlInsertStatement statement);

    /**
     * 插入本表
     *
     * @param sqlInfo
     * @return
     */
    public abstract Object[] insertSelf(InsertSqlInfo sqlInfo);

    /**
     * 插入本表（带参数）
     *
     * @param sqlInfo
     * @param values
     * @return
     */
    public abstract Object[] insertSelf(InsertSqlInfo sqlInfo, Map<String, Object> values);

    /**
     * 插入本表（带参数）
     *
     * @param sqlInfo
     * @param valuesList
     * @return
     */
    public abstract Object[] insertSelf(InsertSqlInfo sqlInfo, List<Map<String, Object>> valuesList);

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int update(OqlUpdateStatement statement) {
        return this.update(statement, null);
    }

    @Override
    public int update(OqlUpdateStatement statement, Map<String, Object> paramMap) {
        for (StatementExecuteInterceptor interceptor : interceptors) {
            interceptor.beforeUpdate(statement, paramMap);
        }

        // 先更新主表
        UpdateSqlInfo sqlInfo = this.parseUpdateSqlInfo(statement);
        int effectRows = 0;
        if (paramMap == null) {
            effectRows = this.updateSelf(sqlInfo);
        } else {
            effectRows = this.updateSelf(sqlInfo, paramMap);
        }

        // 逐个更新子表
        Map<String, UpdateDetailSqlInfo> updateDetailOqlInfoMap = sqlInfo.getDetailSqlInfoMap();
        for (Map.Entry<String, UpdateDetailSqlInfo> entry : updateDetailOqlInfoMap.entrySet()) {
            UpdateDetailSqlInfo detailOqlInfo = entry.getValue();
            OqlUpdateStatement detailStatement = detailOqlInfo.getStatement();

            List<OqlUpdateSetItem> detailSetItems = detailStatement.getSetItems();
            assert (detailSetItems.size() == 1);
            OqlUpdateSetItem detailSetItem = detailSetItems.get(0);

            // 先捞出子表中已经存在的匹配主表ID的子表记录，KEY是主表ID，VALUE是对应的表中已经存在的子表记录ID列表
            Map<Object, List<Object>> masterIdsMap = this.queryForUpdateMasterIdMap(detailOqlInfo, paramMap);

            // 往所有的主表记录中新增不带ID的子表记录
            Set<Object> masterIds = masterIdsMap.keySet();
            this.insertDetailRecords(detailOqlInfo, detailSetItem, masterIds, paramMap);

            // 更新子表中已经存在的子表记录
            Set<Object> detailIds = new HashSet<>();
            for (Map.Entry<Object, List<Object>> masterIdsEntry : masterIdsMap.entrySet()) {
                detailIds.addAll(masterIdsEntry.getValue());
            }
            Set<Object> updateDetailIds = this.updateDetailRecords(detailOqlInfo, detailSetItem, detailIds, paramMap);

            // 删除子表中需要删除的子表记录
            this.deleteDetailRecords(detailOqlInfo, detailIds, updateDetailIds);
        }

        return effectRows;
    }

    @Override
    public int[] batchUpdate(OqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
        for (StatementExecuteInterceptor interceptor : interceptors) {
            interceptor.beforeUpdate(statement, paramMapList);
        }

        // 先更新主表
        UpdateSqlInfo sqlInfo = this.parseUpdateSqlInfo(statement);
        int[] effectedRowsArr = this.batchUpdateSelf(sqlInfo, paramMapList);

        // 先通用循环调用的方式简单处理
        this.batchUpdateSelf(sqlInfo, paramMapList);

        Map<String, UpdateDetailSqlInfo> updateDetailSqlInfoMap = sqlInfo.getDetailSqlInfoMap();
        for (Map.Entry<String, UpdateDetailSqlInfo> entry : updateDetailSqlInfoMap.entrySet()) {
            UpdateDetailSqlInfo detailSqlInfo = entry.getValue();
            OqlUpdateStatement detailStatement = detailSqlInfo.getStatement();

            List<OqlUpdateSetItem> detailSetItems = detailStatement.getSetItems();
            assert (detailSetItems.size() == 1);
            OqlUpdateSetItem detailSetItem = detailSetItems.get(0);

            throw new RuntimeException("暂不支持批量更新带子表的OQL语句！");
        }

        return effectedRowsArr;
    }

    /**
     * 往每一条主表添加相应的子表数据
     * 比如：where条件命中10条主表数据，同时新增的不带ID的子表数据有3条，那么一共会新增30条子表数据
     *
     * @param detailSqlInfo
     * @param masterIds     主表查询结果命中的行的ID
     * @param paramMap      主表更新时输入的参数
     */
    private void insertDetailRecords(UpdateDetailSqlInfo detailSqlInfo, OqlUpdateSetItem detailSetItem, Set<Object> masterIds, Map<String, Object> paramMap) {
        DataObject detailObject = detailSqlInfo.getObject();
        MasterDataField masterField = detailObject.getMasterField();
        String masterFieldName = masterField.getName();

        // 构建InsertInto对象
        OqlInsertInto insertInto = new OqlInsertInto();
        insertInto.setObjectSource(new OqlExprObjectSource(detailObject.getName()));

        // 设置插入的字段
        insertInto.addField(new OqlIdentifierExpr(masterFieldName));
        List<QqlExpr> detailFields = ((OqlListExpr) detailSetItem.getField()).getItems();
        if (detailObject.getPrimaryField().isAutoGenerated()) {
            // 如果存在自动生成的主键字段，要将该字段移除
            int detailPrimaryFieldIndex = detailSqlInfo.getPrimaryFieldIndex();
            for (int i = 0; i < detailFields.size(); i++) {
                if (i != detailPrimaryFieldIndex) {
                    insertInto.addField(detailFields.get(i));
                }
            }
        } else {
            insertInto.addFields(detailFields);
        }

        // 设置插入的值，每条记录中添加主表主录ID的值
        QqlExpr detailValues = detailSetItem.getValue();
        List<Map<String, Object>> insertParamMaps = new ArrayList<>();
        this.processUpdateDetailValue(detailSqlInfo, detailValues, masterIds, paramMap, insertInto, insertParamMaps);

        // 根据是否带参数分别调用不同的接口
        OqlInsertStatement insertStatement = new OqlInsertStatement(insertInto);
        if (paramMap == null && insertStatement.getInsertInto().getValuesList().size() > 0) {
            this.insert(insertStatement);
        } else {
            this.batchInsert(insertStatement, insertParamMaps);
        }
    }


    /**
     * 处理子表的值列表，将处理后的结果存入detailLiteralValuesList、 detailVariableValues、detailVariableParamList三个变量中
     *
     * @param detailSqlInfo   子表的SQL信息
     * @param detailValues    子表的值
     * @param recordIds       主表命中行的记录ID
     * @param paramMap        主表更新时输入的参数
     * @param insertInto      子表的插入子句
     * @param insertParamMaps 子表中以变量的方式插入的列表所需要的参数列表
     */
    private void processUpdateDetailValue(UpdateDetailSqlInfo detailSqlInfo, QqlExpr detailValues, Set<Object> recordIds, Map<String, Object> paramMap, OqlInsertInto insertInto, List<Map<String, Object>> insertParamMaps) {
        DataObject detailObject = detailSqlInfo.getObject();
        String detailMasterFieldName = detailObject.getMasterField().getName();
        int detailPrimaryFieldIndex = detailSqlInfo.getPrimaryFieldIndex();
        boolean isAutoGenerated = detailObject.getPrimaryField().isAutoGenerated();

        // 子对象的数据分两种情况，一种是常量，一种是变量
        for (Object recordId : recordIds) {
            QqlExpr recordIdExpr = OqlExprUtils.parseObject(recordId);
            if (detailValues instanceof OqlJsonArrayExpr) {
                processLiteralUpdateValues(detailSqlInfo, (OqlJsonArrayExpr) detailValues, recordIdExpr, insertInto);
            } else if (detailValues instanceof OqlMethodInvokeExpr) {
                // detail(#{d1}, #{d2})
                OqlInsertValues insertValues = new OqlInsertValues();
                insertValues.addValue(0, new OqlVariantRefExpr(detailMasterFieldName, true));
                List<QqlExpr> itemValues = ((OqlMethodInvokeExpr) detailValues).getArguments();
                if (isAutoGenerated) {
                    for (int i = 0; i < itemValues.size(); i++) {
                        if (i != detailPrimaryFieldIndex) {
                            insertValues.addValue(itemValues.get(i));
                        }
                    }
                } else {
                    insertValues.addValues(itemValues);
                }
                insertInto.addValues(insertValues);

                // 收集插入用的参数
                String detailPrimaryFieldName = detailSqlInfo.getObject().getPrimaryField().getName();
                String detailParamName = ((OqlMethodInvokeExpr) detailValues).getMethodName();
                List<Map<String, Object>> detailParamMaps = (List<Map<String, Object>>) paramMap.get(detailParamName);
                for (Map<String, Object> detailParamMap : detailParamMaps) {
                    if (detailParamMap.get(detailPrimaryFieldName) == null) { // 不带ID的是需要新增的
                        for (Object masterId : recordIds) {
                            Map<String, Object> copyParamMap = new HashMap<>(detailParamMap);
                            copyParamMap.put(detailMasterFieldName, masterId);
                            insertParamMaps.add(copyParamMap);
                        }
                    }
                }
            }
        }
    }

    /**
     * 将常量数据中的值添加到insertInto子句中，并添加主表主录ID
     *
     * @param sqlInfo
     * @param detailValues
     * @param recordIdExpr
     * @param insertInto
     */
    private void processLiteralUpdateValues(UpdateSqlInfo sqlInfo, OqlJsonArrayExpr detailValues, QqlExpr recordIdExpr, OqlInsertInto insertInto) {
        int detailPrimaryFieldIndex = sqlInfo.getPrimaryFieldIndex();
        DataField primaryField = sqlInfo.getObject().getPrimaryField();
        boolean detailPrimaryIsAutoGenerated = primaryField.isAutoGenerated();

        // [(d1, d2), (d1, d2), ...]
        List<QqlExpr> items = detailValues.getItems();
        for (QqlExpr item : items) {
            assert (item instanceof OqlListExpr);
            List<QqlExpr> itemValues = ((OqlListExpr) item).getItems();
            QqlExpr itemValueExpr = itemValues.get(detailPrimaryFieldIndex);
            if (((QqlValuableExpr) itemValueExpr).getValue() == null) {
                // 记录ID为null的数据需要插入主表中，添加记录ID
                OqlInsertValues insertValues = new OqlInsertValues();
                insertValues.addValue(0, recordIdExpr);
                if (detailPrimaryIsAutoGenerated) {
                    for (int i = 0; i < itemValues.size(); i++) {
                        if (i != detailPrimaryFieldIndex) {
                            insertValues.addValue(itemValues.get(i));
                        }
                    }
                } else {
                    insertValues.addValues(itemValues);
                }
                insertInto.addValues(insertValues);
            }
        }
    }


    /**
     * 更新子表中已经存在，并且输入中也有的记录
     *
     * @param detailSqlInfo 子对象更新SQL相关的信息
     * @param detailSetItem 子表更新的OQL语句，如detail(pk, d1, d2) = [(null, d1, d2), (1, d1, d2)]或者detail(pk, d1, d2) = detailVar(#{pk}, #{d1}, #{d2})
     * @param detailIds     数据库中已经存在的主表记录对应的子表记录ID
     * @param paramMap      主表更新时输入的参数
     */
    private Set<Object> updateDetailRecords(UpdateDetailSqlInfo detailSqlInfo, OqlUpdateSetItem detailSetItem, Set<Object> detailIds, Map<String, Object> paramMap) {
        DataObject detailObject = detailSqlInfo.getObject();
        String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
        int detailPrimaryFieldIndex = detailSqlInfo.getPrimaryFieldIndex();

        OqlUpdateStatement updateStatement = new OqlUpdateStatement();
        updateStatement.setObjectSource(new OqlExprObjectSource(detailObject.getName()));
        List<QqlExpr> detailFields = ((OqlListExpr) detailSetItem.getField()).getItems();
        for (QqlExpr detailField : detailFields) {
            String fieldName = ((OqlIdentifierExpr) detailField).getName();
            if (!fieldName.equalsIgnoreCase(detailPrimaryFieldName)) {
                updateStatement.addSetItem(new OqlUpdateSetItem(detailField, new OqlVariantRefExpr(fieldName, true)));
            }
        }
        OqlBinaryOpExpr whereExpr = new OqlBinaryOpExpr(new OqlIdentifierExpr(detailPrimaryFieldName), OqlBinaryOperator.Equal, new OqlVariantRefExpr(detailPrimaryFieldName, true));
        updateStatement.setWhereClause(new OqlWhereClause(whereExpr));

        Set<Object> updateIds = new HashSet<>();
        List<Map<String, Object>> updateParamMaps = new ArrayList<>();
        QqlExpr detailValues = detailSetItem.getValue();
        if (detailValues instanceof OqlJsonArrayExpr) {
            // detail(pk, d1, d2) = [(null, d1, d2), (1, d1, d2)]
            List<QqlExpr> items = ((OqlJsonArrayExpr) detailValues).getItems();
            int fieldSize = detailFields.size();
            for (QqlExpr item : items) {
                assert (item instanceof OqlListExpr);
                List<QqlExpr> itemValues = ((OqlListExpr) item).getItems();
                Object itemPkValue = ((QqlValuableExpr) itemValues.get(detailPrimaryFieldIndex)).getValue();
                if (itemPkValue != null && detailIds.contains(itemPkValue)) {
                    updateIds.add(itemPkValue);
                    // 需要更新（将常量转为变量，便于使用update(stmt, List<Map>)更新）
                    Map<String, Object> updateParamMap = new HashMap<>();
                    for (int i = 0; i < fieldSize; i++) {
                        String fieldName = ((OqlIdentifierExpr) detailFields.get(i)).getName();
                        Object fieldValue = ((QqlValuableExpr) itemValues.get(i)).getValue();
                        updateParamMap.put(fieldName, fieldValue);
                    }
                    updateParamMaps.add(updateParamMap);
                }
            }
        } else if (detailValues instanceof OqlMethodInvokeExpr) {
            // detail(pk, d1, d2) = detailVar(#{pk}, #{d1}, #{d2})
            // 收集插入用的参数
            String detailParamName = ((OqlMethodInvokeExpr) detailValues).getMethodName();
            List<Map<String, Object>> detailParamMaps = (List<Map<String, Object>>) paramMap.get(detailParamName);
            for (Map<String, Object> detailParamMap : detailParamMaps) {
                Object detailPkValue = detailParamMap.get(detailPrimaryFieldName);
                if (detailPkValue != null && detailIds.contains(detailPkValue)) {
                    // 需要更新
                    updateIds.add(detailPkValue);
                    updateParamMaps.add(detailParamMap);
                }
            }
        }

        this.batchUpdate(updateStatement, updateParamMaps);

        return updateIds;
    }

    /**
     * 删除子表中已存在，但是输入中没有的记录
     *
     * @param sqlInfo         子对象更新SQL相关的信息
     * @param detailIds       数据库中已经存在的主表记录对应的子表记录ID
     * @param updateDetailIds 数据库中已经存在的主表记录对应的被更新过的子表记录ID
     */
    private void deleteDetailRecords(UpdateDetailSqlInfo sqlInfo, Set<Object> detailIds, Set<Object> updateDetailIds) {
        // 子表的数据库中有的ID,但是输入中没有的ID要删除
        List<Object> deleteIds = new ArrayList<>();
        for (Object detailId : detailIds) {
            if (!updateDetailIds.contains(detailId)) {
                // 没有变更新的就要被删除
                deleteIds.add(detailId);
            }
        }

        DataObject detailObject = sqlInfo.getObject();
        String detailPrimaryFieldName = detailObject.getPrimaryField().getName();

        OqlDeleteStatement statement = new OqlDeleteStatement();
        statement.setObjectSource(new OqlExprObjectSource(new OqlIdentifierExpr(detailObject.getName())));

        OqlListExpr inListExpr = new OqlListExpr();
        for (Object deleteId : deleteIds) {
            inListExpr.addItem(OqlExprUtils.parseObject(deleteId));
        }
        OqlInOpExpr whereExpr = new OqlInOpExpr(new OqlIdentifierExpr(detailPrimaryFieldName), inListExpr);
        statement.setWhereClause(new OqlWhereClause(whereExpr));

        this.delete(statement);
    }


    /**
     * 准备子对象更新的OQL语句
     *
     * @param statement
     * @return
     */
    public abstract UpdateSqlInfo parseUpdateSqlInfo(OqlUpdateStatement statement);

    /**
     * 查询子表中主表的ID
     *
     * @param sqlInfo
     * @param paramMap
     * @return
     */
    public abstract Map<Object, List<Object>> queryForUpdateMasterIdMap(UpdateDetailSqlInfo sqlInfo, Map<String, Object> paramMap);

    /**
     * 更新本表
     *
     * @param sqlInfo
     * @return
     */
    public abstract int updateSelf(UpdateSqlInfo sqlInfo);

    /**
     * 更新本表
     *
     * @param sqlInfo
     * @param paramMap
     * @return
     */
    public abstract int updateSelf(UpdateSqlInfo sqlInfo, Map<String, Object> paramMap);

    /**
     * 批量更新本表
     *
     * @param sqlInfo
     * @param paramMapList
     * @return
     */
    public abstract int[] batchUpdateSelf(UpdateSqlInfo sqlInfo, List<Map<String, Object>> paramMapList);

    @Override
    public int delete(OqlDeleteStatement statement, List<Map<String, Object>> paramMapList) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> select(OqlSelectStatement statement) {
        return this.select(statement, null);
    }

    @Override
    public List<Map<String, Object>> select(OqlSelectStatement statement, Map<String, Object> paramMap) {
        // 当前查询SQL信息
        SelectSqlInfo sqlInfo = this.parseSelectSqlInfo(statement, paramMap);
        List<Map<String, Object>> dataMapList = this.select(sqlInfo, paramMap);
        // 逐个执行子查询（一对多关联的相关对象）
        Map<String, SelectDetailSqlInfo> selectDetailOqlInfoMap = sqlInfo.getDetailSqlInfoMap();
        for (Map.Entry<String, SelectDetailSqlInfo> entry : selectDetailOqlInfoMap.entrySet()) {
            SelectDetailSqlInfo detailSqlInfo = entry.getValue();
            String alias = detailSqlInfo.getAlias();
            List<Map<String, Object>> refDataMapList = this.select(detailSqlInfo, null);
            // 将相关对象的数据归并到主数据中
            mergeRefData(dataMapList, refDataMapList, entry.getKey(), entry.getValue());
        }
        return dataMapList;
    }


    /**
     * 合并多选相关表数据到主数据集
     *
     * @param dataMapList     主数据集
     * @param refDataMapList  多选相关表数据
     * @param objectFieldName 相关表在主表的字段名
     * @param sqlInfo         相关表sqlinfo
     */
    private void mergeRefData(List<Map<String, Object>> dataMapList, List<Map<String, Object>> refDataMapList, String objectFieldName, SelectDetailSqlInfo sqlInfo) {
        String field = objectFieldName;
        // 如果有别名，使用别名
        if (sqlInfo.getAlias() != null) {
            field = sqlInfo.getAlias();
        }
        // 获取相关表主键字段
        String detailPrimaryField = sqlInfo.getObject().getPrimaryField().getName();
        // 相关表主键默认为不返回
        boolean hasDeitalPrimary = false;
        if (sqlInfo.getPrimaryFieldIndex() > -1) {
            // 如果指定返回相关表主键，则使用别名
            detailPrimaryField = sqlInfo.getSelfSelectItems().get(sqlInfo.getPrimaryFieldIndex()).getAlias();
            // 如果指定返回相关表主键，则置为true
            hasDeitalPrimary = true;
        }

        // 将相关表数据转为 map ，key为主键id
        Map<Object, Map<String, Object>> refDataMapMap = new HashMap<>();
        for (Map<String, Object> refDataMap : refDataMapList) {
            Object detailPrimaryId = refDataMap.get(detailPrimaryField);
            // 如果不返回主键，则数据删除主键
            if (!hasDeitalPrimary) {
                refDataMap.remove(detailPrimaryField);
            }
            refDataMapMap.put(detailPrimaryId, refDataMap);
        }

        for (Map<String, Object> dataMap : dataMapList) {
            if (!dataMap.containsKey(field)) {
                continue;
            }
            // 获取主数据集中多选相关表的主键，并反序列化为真实主键值
            String refIdStr = String.valueOf(dataMap.get(field));
            List<Object> refIds = JSON.parseArray(refIdStr);
            List<Map<String, Object>> refAppendDataList = new ArrayList<>();

            // 从多选相关表的数据中捞取对应的数据
            for (Object refId : refIds) {
                if (refDataMapMap.containsKey(refId)) {
                    refAppendDataList.add(refDataMapMap.get(refId));
                }
            }
            // 将相关表数据拼接到主数据集
            dataMap.put(field, refAppendDataList);
        }
    }


    /**
     * 解析查询SQL信息
     *
     * @param statement
     * @param paramMap
     * @return
     */
    protected abstract SelectSqlInfo parseSelectSqlInfo(OqlSelectStatement statement, Map<String, Object> paramMap);

    /**
     * 查询SQL执行
     *
     * @param sqlInfo
     * @param paramMap
     * @return
     */
    protected abstract List<Map<String, Object>> select(SelectSqlInfo sqlInfo, Map<String, Object> paramMap);

    @Override
    public int delete(OqlDeleteStatement statement) {
        for (StatementExecuteInterceptor interceptor : interceptors) {
            interceptor.beforeDelete(statement);
        }

        DeleteSqlInfo masterSqlInfo = this.parseDeleteSqlInfo(statement);
        List<Object> masterIds = new ArrayList<>();
        // 如果有子表，则需要先获取主表的主键id
        if (masterSqlInfo.getDetailSqlInfoMap() != null && !masterSqlInfo.getDetailSqlInfoMap().isEmpty()) {
            masterIds = this.queryForMasterIds(masterSqlInfo, null);
            if (masterIds == null || masterIds.isEmpty()) {
                return 0;
            }
        }

        // 如果有子表，删除子表
        if (masterSqlInfo.getDetailSqlInfoMap() != null && !masterSqlInfo.getDetailSqlInfoMap().isEmpty()) {
            for (Map.Entry<String, DeleteDetailSqlInfo> entry : masterSqlInfo.getDetailSqlInfoMap().entrySet()) {
                deleteDetail(entry.getValue(), masterIds, null);
            }
        }
        // 删除主表
        int effectRows = this.deleteSelf(masterSqlInfo);

        return effectRows;
    }

    @Override
    public int delete(OqlDeleteStatement statement, Map<String, Object> paramMap) {
        for (StatementExecuteInterceptor interceptor : interceptors) {
            interceptor.beforeDelete(statement);
        }

        DeleteSqlInfo masterSqlInfo = this.parseDeleteSqlInfo(statement);
        List<Object> masterIds = new ArrayList<>();
        // 如果有子表，则需要先获取主表的主键id
        if (masterSqlInfo.getDetailSqlInfoMap() != null && !masterSqlInfo.getDetailSqlInfoMap().isEmpty()) {
            masterIds = this.queryForMasterIds(masterSqlInfo, paramMap);
            if (masterIds == null || masterIds.isEmpty()) {
                return 0;
            }
        }

        // 如果有子表，删除子表
        if (masterSqlInfo.getDetailSqlInfoMap() != null && !masterSqlInfo.getDetailSqlInfoMap().isEmpty()) {
            for (Map.Entry<String, DeleteDetailSqlInfo> entry : masterSqlInfo.getDetailSqlInfoMap().entrySet()) {
                deleteDetail(entry.getValue(), masterIds, paramMap);
            }
        }
        // 删除主表
        int effectRows = this.deleteSelf(masterSqlInfo, paramMap);

        return effectRows;
    }

    /**
     * 删除子表
     *
     * @param deleteDetailSqlInfo
     * @param masterIds
     * @param paramMap
     */
    private void deleteDetail(DeleteDetailSqlInfo deleteDetailSqlInfo, List<Object> masterIds, Map<String, Object> paramMap) {
        DataObject detailObject = deleteDetailSqlInfo.getObject();
        String detailMasterField = detailObject.getMasterField().getName();
        OqlDeleteStatement detailStatement = new OqlDeleteStatement();
        detailStatement.setObjectSource(new OqlExprObjectSource(new OqlIdentifierExpr(detailObject.getName())));
        OqlListExpr inListExpr = OqlExprUtils.parseList(masterIds);
        OqlInOpExpr whereExpr = new OqlInOpExpr(new OqlIdentifierExpr(detailMasterField), inListExpr);
        detailStatement.setWhereClause(new OqlWhereClause(whereExpr));
        DeleteSqlInfo deleteSqlInfo = this.parseDeleteSqlInfo(detailStatement);
        this.deleteSelf(deleteSqlInfo, paramMap);
    }


    /**
     * 获取本表的主键
     *
     * @param masterOqlInfo
     * @param paramMap
     * @return
     */
    public abstract List<Object> queryForMasterIds(AbstractQueryableSqlInfo masterOqlInfo, Map<String, Object> paramMap);

    /**
     * 获取子表内主表和子表主键的映射
     *
     * @param detailSqlInfo
     * @param masterIds
     * @param paramMap
     * @return
     */
    public abstract Map<Object, List<Object>> queryForMasterAndDetailIdMap(AbstractQueryableSqlInfo detailSqlInfo, List<Object> masterIds, Map<String, Object> paramMap);

    /**
     * 执行删除语句
     *
     * @param deleteSqlInfo
     * @return
     */
    public abstract int deleteSelf(DeleteSqlInfo deleteSqlInfo);

    /**
     * 执行删除语句（带变量）
     *
     * @param deleteSqlInfo
     * @param paramMap
     * @return
     */
    public abstract int deleteSelf(DeleteSqlInfo deleteSqlInfo, Map<String, Object> paramMap);

    /**
     * 解析删除语句
     *
     * @param statement
     * @return
     */
    public abstract DeleteSqlInfo parseDeleteSqlInfo(OqlDeleteStatement statement);

}
