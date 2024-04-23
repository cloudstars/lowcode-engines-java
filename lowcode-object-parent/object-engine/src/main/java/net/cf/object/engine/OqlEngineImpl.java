package net.cf.object.engine;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlValuableExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlInListExpr;
import net.cf.form.repository.sql.ast.statement.*;
import net.cf.object.engine.data.DefaultParameterMapper;
import net.cf.object.engine.data.DefaultResultReducer;
import net.cf.object.engine.data.ParameterMapper;
import net.cf.object.engine.data.ResultReducer;
import net.cf.object.engine.object.XField;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.object.XObjectRefField;
import net.cf.object.engine.oql.FastOqlException;
import net.cf.object.engine.oql.ast.*;
import net.cf.object.engine.oql.check.DeleteStatementChecker;
import net.cf.object.engine.oql.check.InsertStatementChecker;
import net.cf.object.engine.oql.check.SelectStatementChecker;
import net.cf.object.engine.oql.check.UpdateStatementChecker;
import net.cf.object.engine.oql.info.*;
import net.cf.object.engine.sqlbuilder.Oql2SqlUtils;
import net.cf.object.engine.sqlbuilder.delete.SqlDeleteStatementBuilder;
import net.cf.object.engine.sqlbuilder.insert.SqlInsertStatementBuilder;
import net.cf.object.engine.sqlbuilder.select.SqlSelectStatementBuilder;
import net.cf.object.engine.sqlbuilder.update.SqlUpdateStatementBuilder;
import net.cf.object.engine.util.OqlUtils;
import net.cf.object.engine.util.XObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
        // 对OQL语句进行合法性校验
        SelectStatementChecker checker = new SelectStatementChecker();
        stmt.accept(checker);

        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = Oql2SqlUtils.toSqlSelect(stmt, builder);
        Map<String, Object> resultMap = this.repository.selectOne(sqlStmt);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return resultReducer.reduceResult(resultMap);
    }

    @Override
    public Map<String, Object> queryOne(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        // 对OQL语句进行合法性校验
        SelectStatementChecker checker = new SelectStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlSelectInfoParser infoParser = new OqlSelectInfoParser(stmt, paramMap, false);
        infoParser.parse();

        // 查询本表
        OqlSelectInfo mainSelectInfo = infoParser.getSelfObjectSelectInfo();
        OqlSelectStatement mainStmt = mainSelectInfo.getStatement();
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement mainSqlStmt = Oql2SqlUtils.toSqlSelect(mainStmt, builder);
        Map<String, Object> resultMap = this.repository.selectOne(mainSqlStmt, paramMap);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        Map<String, Object> mainResultMap = resultReducer.reduceResult(resultMap);

        // 查询一对多的关联表的结果
        List<OqlSelectInfo> detailSelectInfos = infoParser.getDetailObjectSelectInfos();
        if (detailSelectInfos != null && detailSelectInfos.size() > 0) {
            XObject mainObject = mainSelectInfo.getObject();

            // 生成masterId
            String mainPrimaryFieldName = mainObject.getPrimaryField().getName();
            String masterId = (String) mainResultMap.get(mainPrimaryFieldName);

            // 循环处理子表
            for (OqlSelectInfo detailSelectInfo : detailSelectInfos) {
                OqlSelectStatement detailStmt = detailSelectInfo.getStatement();
                XObject detailObject = detailSelectInfo.getObject();
                Map<String, Object> detailParamMap = detailSelectInfo.getParamMap();
                XObjectRefField masterField = detailObject.getObjectRefField(mainObject.getName());
                detailParamMap.put(masterField.getName(), masterId);
                List<Map<String, Object>> detailResult = this.queryList(detailStmt, detailParamMap);
                XObjectRefField detailRefField = mainObject.getObjectRefField(detailObject.getName());
                String detailRefFieldName = detailRefField.getName();
                if (!detailSelectInfo.isDetailDefaultExpandQuery()) {
                    mainResultMap.put(detailRefFieldName, detailResult);
                } else {
                    List<String> detailIds = this.parseDetailObjectIds(detailObject, detailResult);
                    mainResultMap.put(detailRefFieldName, detailIds);
                }
            }
        }

        return mainResultMap;
    }

    /**
     * 从结果集中提取子表的ID列表
     *
     * @param detailObject
     * @param detailResultMaps
     * @return
     */
    private List<String> parseDetailObjectIds(XObject detailObject, List<Map<String, Object>> detailResultMaps) {
        String detailPrimaryFieldName = detailObject.getPrimaryField().getName();
        List<String> detailIds = new ArrayList<>();
        for (Map<String, Object> resultMap : detailResultMaps) {
            detailIds.add(resultMap.get(detailPrimaryFieldName).toString());
        }

        return detailIds;
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt) {
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement sqlStmt = Oql2SqlUtils.toSqlSelect(stmt, builder);
        List<Map<String, Object>> resultMapList = this.repository.selectList(sqlStmt);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        return this.convertResultMapList(resultReducer, resultMapList);
    }

    @Override
    public List<Map<String, Object>> queryList(OqlSelectStatement stmt, Map<String, Object> paramMap) {
        // OQL语句合法性校验
        SelectStatementChecker checker = new SelectStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlSelectInfoParser infoParser = new OqlSelectInfoParser(stmt, paramMap, true);
        infoParser.parse();

        // 查询本表
        OqlSelectInfo mainSelectInfo = infoParser.getSelfObjectSelectInfo();
        OqlSelectStatement mainStmt = mainSelectInfo.getStatement();
        SqlSelectStatementBuilder builder = new SqlSelectStatementBuilder();
        SqlSelectStatement selfSqlStmt = Oql2SqlUtils.toSqlSelect(mainStmt, builder);
        List<Map<String, Object>> mainResultMapList = this.repository.selectList(selfSqlStmt, paramMap);
        DefaultResultReducer resultReducer = new DefaultResultReducer(builder.getFieldMappings());
        mainResultMapList = this.convertResultMapList(resultReducer, mainResultMapList);

        // 相询一对多的关联表的结果
        List<OqlSelectInfo> detailSelectInfos = infoParser.getDetailObjectSelectInfos();
        if (detailSelectInfos != null && detailSelectInfos.size() > 0) {
            // 生成masterId列表
            XObject mainObject = mainSelectInfo.getObject();
            String mainPrimaryFieldName = mainObject.getPrimaryField().getName();
            List<String> masterIds = new ArrayList<>();
            for (Map<String, Object> mainResultMap : mainResultMapList) {
                masterIds.add((String) mainResultMap.get(mainPrimaryFieldName));
            }

            for (OqlSelectInfo detailSelectInfo : detailSelectInfos) {
                XObject detailObject = detailSelectInfo.getObject();
                String detailMasterFieldName = detailObject.getObjectRefField(mainObject.getName()).getName();
                OqlSelectStatement detailStmt = detailSelectInfo.getStatement();
                Map<String, Object> detailParamMap = detailSelectInfo.getParamMap();
                // 生成子表OQL语句时变量加了"s"复数后缀
                detailParamMap.put(detailMasterFieldName + "s", masterIds);
                List<Map<String, Object>> detailResult = this.queryList(detailStmt, detailParamMap);
                // 按照masterId聚合
                Map<String, List<Map<String, Object>>> masterIdGroupedMap = new HashMap<>();
                for (Map<String, Object> detailItem : detailResult) {
                    String masterId = detailItem.get(detailMasterFieldName).toString();
                    List<Map<String, Object>> masterIdResult = masterIdGroupedMap.get(masterId);
                    if (masterIdResult == null) {
                        masterIdResult = new ArrayList<>();
                        masterIdGroupedMap.put(masterId, masterIdResult);
                    }
                    masterIdResult.add(detailItem);
                }

                // 把前面的结果再合并到主表的返回数据中
                for (Map<String, Object> mainResultMap : mainResultMapList) {
                    XObjectRefField detailRefField = mainObject.getObjectRefField(detailObject.getName());
                    String recordId = mainResultMap.get(mainPrimaryFieldName).toString();
                    List<Map<String, Object>> masterIdResult = masterIdGroupedMap.get(recordId);
                    String detailRefFieldName = detailRefField.getName();
                    if (!detailSelectInfo.isDetailDefaultExpandQuery()) {
                        mainResultMap.put(detailRefFieldName, masterIdResult);
                    } else {
                        List<String> detailIds = this.parseDetailObjectIds(detailObject, masterIdResult);
                        mainResultMap.put(detailRefFieldName, detailIds);
                    }
                }
            }
        }

        return mainResultMapList;
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
        // 对OQL语句进行合法性校验
        InsertStatementChecker checker = new InsertStatementChecker();
        stmt.accept(checker);

        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        SqlInsertStatement sqlStmt = Oql2SqlUtils.toSqlInsert(stmt, builder);
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

        // OQL语句解析
        OqlInsertInfoParser infoParser = new OqlInsertInfoParser(stmt, paramMap);
        infoParser.parse();

        /**
         * 插入本表
         */
        OqlInsertInfo mainInsertInfo = infoParser.getSelfInsertInfo();
        OqlInsertStatement mainStmt = mainInsertInfo.getStatement();
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        SqlInsertStatement sqlStmt = Oql2SqlUtils.toSqlInsert(mainStmt, builder);
        // 作参数映射，将引擎层的参数转换驱动层的参数格式
        DefaultParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        Map<String, Object> targetParamMap = parameterMapper.mapParameter(paramMap);
        int effectedRows = this.repository.insert(sqlStmt, targetParamMap);
        if (effectedRows == 0) {
            logger.warn("未成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        } else {
            logger.info("成功创建记录，影响行数：{}，OQL：", effectedRows, stmt);
        }

        // 生成获取结果数据中的主键ID，如果是自增主键的情况，则放入输入参数中
        XObject mainObject = mainInsertInfo.getObject();
        XField mainPrimaryField = mainObject.getPrimaryField();
        if (mainPrimaryField.isAutoGen()) {
            String mainPrimaryFieldName = mainPrimaryField.getName();
            String mainPrimaryColumnName = mainPrimaryField.getColumnName();
            String mainPrimaryId = targetParamMap.get(mainPrimaryColumnName).toString();
            paramMap.put(mainPrimaryFieldName, mainPrimaryId);
        }

        /**
         * 循环插入子表
         */
        List<OqlDetailInsertInfo> detailInsertInfos = infoParser.getDetailInsertInfos();
        if (detailInsertInfos != null && detailInsertInfos.size() > 0) {
            String masterId = paramMap.get(mainPrimaryField.getName()).toString(); // 主表中的主表记录ID

            // 循环处理子表
            for (OqlDetailInsertInfo detailInsertInfo : detailInsertInfos) {
                XObject detailObject = detailInsertInfo.getObject();
                Object subParamMaps = detailInsertInfo.getParamMaps();
                if (subParamMaps == null && !(subParamMaps instanceof List)) {
                    throw new FastOqlException("子表数据不能为空，且参数格式必须是List<Map>");
                }

                // 添加主表记录ID
                String subObjectMasterFieldName = detailObject.getObjectRefField(mainObject.getName()).getName();
                List<Map<String, Object>> subParamMapList = (List<Map<String, Object>>) subParamMaps;
                for (Map<String, Object> subParamMap : subParamMapList) {
                    subParamMap.put(subObjectMasterFieldName, masterId);
                }
                OqlInsertStatement detailStmt = detailInsertInfo.getStatement();
                this.createList(detailStmt, subParamMapList);
            }
        }

        return effectedRows;
    }


    @Override
    public int[] createList(OqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        // 对OQL语句进行合法性校验
        InsertStatementChecker checker = new InsertStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlInsertInfoParser infoParser = new OqlInsertInfoParser(stmt, paramMaps);
        infoParser.parse();

        // 构建SQL语句
        OqlInsertInfo mainInsertInfo = infoParser.getSelfInsertInfo();
        OqlInsertStatement mainStmt = mainInsertInfo.getStatement();
        SqlInsertStatementBuilder builder = new SqlInsertStatementBuilder();
        SqlInsertStatement sqlStmt = Oql2SqlUtils.toSqlInsert(mainStmt, builder);
        // 作参数映射，将引擎层的参数转换驱动层的参数格式
        DefaultParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        List<Map<String, Object>> targetParamMaps = this.convertParameterMapList(parameterMapper, paramMaps);
        int[] effectedRowsArray = this.repository.batchInsert(sqlStmt, targetParamMaps);
        // 如果存在自增主键的话，将自增ID复制到参数中
        XField mainPrimaryField = mainStmt.getObjectSource().getResolvedObject().getPrimaryField();
        if (mainPrimaryField.isAutoGen()) {
            String fieldName = mainPrimaryField.getName();
            String columnName = mainPrimaryField.getColumnName();
            for (int i = 0, l = paramMaps.size(); i < l; i++) {
                Map<String, Object> paramMap = paramMaps.get(i);
                paramMap.put(fieldName, targetParamMaps.get(i).get(columnName));
            }
        }
        this.sumAndLogsumEffectedRows(effectedRowsArray, stmt);

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

        SqlUpdateStatement sqlStmt = Oql2SqlUtils.toSqlUpdate(stmt);
        int effectedRows = this.repository.update(sqlStmt);
        return effectedRows;
    }

    @Override
    public int modify(OqlUpdateStatement stmt, Map<String, Object> paramMap) {
        // 对OQL语句进行合法性校验
        UpdateStatementChecker checker = new UpdateStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlUpdateInfoParser infoParser = new OqlUpdateInfoParser(stmt, paramMap);
        infoParser.parse();

        OqlUpdateInfo mainUpdateInfo = infoParser.getSelfUpdateInfo();
        OqlUpdateStatement mainStmt = mainUpdateInfo.getStatement();
        SqlUpdateStatementBuilder builder = new SqlUpdateStatementBuilder();
        SqlUpdateStatement mainSqlStmt = Oql2SqlUtils.toSqlUpdate(mainStmt, builder);
        ParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        Map<String, Object> targetParamMap = this.convertParameterMap(parameterMapper, paramMap);
        int effectedRows = this.repository.update(mainSqlStmt, targetParamMap);

        // 计算主表记录ID
        XObject mainObject = mainUpdateInfo.getObject();
        XField mainPrimaryField = mainObject.getPrimaryField();
        String mainPrimaryFieldName = mainPrimaryField.getName();
        // TODO 主表记录ID的变量名可能不是mainObjectName，比如update ... where pk = #{pk'}，实际要取pk'的值
        String masterId = paramMap.get(mainPrimaryFieldName).toString();

        // 先删除不需要保留的子表数据（由于使用not in (...)，所以需要在新数据插入前执行）
        List<OqlDetailDeleteInfo> detailDeleteInfos = infoParser.getDetailDeleteInfos();
        if (detailDeleteInfos != null && detailDeleteInfos.size() > 0) {
            for (OqlDetailDeleteInfo detailDeleteInfo : detailDeleteInfos) {
                List<String> remainedRecordIds = detailDeleteInfo.getRemainedRecordIds();
                if (remainedRecordIds == null && remainedRecordIds.size() == 0) {
                    // 更新时没有删除旧数据
                    continue;
                }

                // 添加主表记录ID和需要保留的记录ID
                XObjectRefField detailMasterField = detailDeleteInfo.getObject().getObjectRefField(mainObject.getName());
                Map<String, Object> detailDeleteParamMap = new HashMap<>();
                detailDeleteParamMap.put(detailMasterField.getName(), masterId);
                detailDeleteParamMap.put("remainedRecordIds", remainedRecordIds);
                this.remove(detailDeleteInfo.getStatement(), detailDeleteParamMap);
            }
        }

        // 插入新增的子表数据
        List<OqlDetailInsertInfo> detailInsertInfos = infoParser.getDetailInsertInfos();
        if (detailInsertInfos != null && detailInsertInfos.size() > 0) {
            for (OqlDetailInsertInfo detailInsertInfo : detailInsertInfos) {
                List<Map<String, Object>> insertParamMaps = detailInsertInfo.getParamMaps();
                if (insertParamMaps == null && insertParamMaps.size() == 0) {
                    // 更新时没有新数据
                    continue;
                }

                // 给新插入的数据补充主表记录ID
                for (Map<String, Object> insertParamMap : insertParamMaps) {
                    insertParamMap.put(mainPrimaryFieldName, masterId);
                }

                // 批量创建子表记录
                this.createList(detailInsertInfo.getStatement(), insertParamMaps);
            }
        }

        // 更新编辑的子表数据
        List<OqlDetailUpdateInfo> detailUpdateInfos = infoParser.getDetailUpdateInfos();
        if (detailUpdateInfos != null && detailUpdateInfos.size() > 0) {
            for (OqlDetailUpdateInfo detailUpdateInfo : detailUpdateInfos) {
                List<Map<String, Object>> updateParamMaps = detailUpdateInfo.getParamMaps();
                if (updateParamMaps == null && updateParamMaps.size() == 0) {
                    // 更新时没有旧数据
                    continue;
                }

                // 批量更新子表记录
                this.modifyList(detailUpdateInfo.getStatement(), updateParamMaps);
            }
        }

        return effectedRows;
    }

    @Override
    public int[] modifyList(OqlUpdateStatement stmt, List<Map<String, Object>> paramMaps) {
        // 对OQL语句进行合法性校验
        UpdateStatementChecker checker = new UpdateStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlUpdateInfoParser infoParser = new OqlUpdateInfoParser(stmt, paramMaps);
        infoParser.parse();

        SqlUpdateStatementBuilder builder = new SqlUpdateStatementBuilder();
        SqlUpdateStatement sqlStmt = Oql2SqlUtils.toSqlUpdate(stmt, builder);
        ParameterMapper parameterMapper = new DefaultParameterMapper(builder.getFieldMappings());
        List<Map<String, Object>> targetParamMaps = this.convertParameterMapList(parameterMapper, paramMaps);
        int[] effectedRowsArray = this.repository.batchUpdate(sqlStmt, targetParamMaps);
        this.sumAndLogsumEffectedRows(effectedRowsArray, stmt);

        // TODO 批量更新子表

        return effectedRowsArray;
    }

    @Override
    public int remove(OqlDeleteStatement stmt) {
        // 对OQL语句进行合法性校验
        DeleteStatementChecker checker = new DeleteStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlDeleteInfoParser infoParser = new OqlDeleteInfoParser(stmt);
        infoParser.parse();

        // 删除主表数据
        OqlDeleteInfo mainDeleteStmt = infoParser.getSelfDeleteInfo();
        SqlDeleteStatement mainSqlStmt = Oql2SqlUtils.toSqlDelete(mainDeleteStmt.getStatement());
        int effectedRows = this.repository.delete(mainSqlStmt);

        // 循环删除子表数据
        List<OqlDetailDeleteInfo> detailDeleteInfos = infoParser.getDetailDeleteInfos();
        if (detailDeleteInfos != null && detailDeleteInfos.size() > 0) {
            // TODO 考虑用同样的where条件，把主表的记录ID查出来，再批量删除子表数据
            List<SqlExpr> masterIdExprs = checker.getMasterIdExprs();
            if (CollectionUtils.isEmpty(masterIdExprs)) {
                throw new FastOqlException("OQL where条件中未指明主表记录ID");
            }
            List<Object> masterIds = new ArrayList<>();
            for (SqlExpr masterIdExpr : masterIdExprs) {
                masterIds.add(((SqlValuableExpr) masterIdExpr).getValue());
            }
            String mainObjetName = mainDeleteStmt.getStatement().getFrom().getResolvedObject().getName();
            this.removeDetailByMasterId(mainObjetName, masterIds, detailDeleteInfos);
        }

        return effectedRows;
    }

    @Override
    public int remove(OqlDeleteStatement stmt, Map<String, Object> paramMap) {
        // 对OQL语句进行合法性校验
        DeleteStatementChecker checker = new DeleteStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlDeleteInfoParser infoParser = new OqlDeleteInfoParser(stmt);
        infoParser.parse();

        // 删除主表数据
        OqlDeleteInfo mainDeleteInfo = infoParser.getSelfDeleteInfo();
        OqlDeleteStatement mainStmt = mainDeleteInfo.getStatement();
        SqlDeleteStatementBuilder builder = new SqlDeleteStatementBuilder();
        SqlDeleteStatement mainSqlStmt = Oql2SqlUtils.toSqlDelete(mainStmt, builder);

        int effectedRows = this.repository.delete(mainSqlStmt, paramMap);

        // 循环删除子表数据
        List<OqlDetailDeleteInfo> detailDeleteInfos = infoParser.getDetailDeleteInfos();
        if (detailDeleteInfos != null && detailDeleteInfos.size() > 0) {
            // TODO 考虑用同样的where条件，把主表的记录ID查出来，再批量删除子表数据
            List<SqlExpr> masterIdExprs = checker.getMasterIdExprs();

            if (CollectionUtils.isEmpty(masterIdExprs)) {
                throw new FastOqlException("OQL where条件中未指明主表记录ID");
            }

            List<Object> masterIds = new ArrayList<>();
            if (masterIdExprs.get(0) instanceof SqlVariantRefExpr) { //applyId = #{applyId}, applyId in (#{applyIds})
                String varName = ((SqlVariantRefExpr) masterIdExprs.get(0)).getVarName();
                if (paramMap.get(varName) instanceof List) {
                    masterIds.addAll((List) paramMap.get(varName));
                } else {
                    masterIds.add(paramMap.get(varName));
                }
            } else {
                for (SqlExpr masterIdExpr : masterIdExprs) {
                    masterIds.add(((SqlValuableExpr) masterIdExpr).getValue());
                }
            }

            String mainObjetName = mainDeleteInfo.getStatement().getFrom().getResolvedObject().getName();
            this.removeDetailByMasterId(mainObjetName, masterIds, detailDeleteInfos);
        }


        return effectedRows;
    }

    /**
     * 根据masterIds来删除子表
     *
     * @param masterIds
     * @param detailDeleteInfos
     */
    private void removeDetailByMasterId(String mainObjectName, Object masterIds, List<OqlDetailDeleteInfo> detailDeleteInfos) {
        for (OqlDetailDeleteInfo detailDeleteInfo : detailDeleteInfos) {
            XObject detailObject = detailDeleteInfo.getObject();
            String masterFieldName = detailObject.getObjectRefField(mainObjectName).getName();
            Map<String, Object> detailParamMap = new HashMap<>();
            detailParamMap.put(masterFieldName + "s", masterIds);
            this.remove(detailDeleteInfo.getStatement(), detailParamMap);
        }
    }

    @Override
    public int[] removeList(OqlDeleteStatement stmt, List<Map<String, Object>> paramMaps) {
        // 对OQL语句进行合法性校验
        DeleteStatementChecker checker = new DeleteStatementChecker();
        stmt.accept(checker);

        // OQL语句解析
        OqlDeleteInfoParser infoParser = new OqlDeleteInfoParser(stmt);
        infoParser.parse();

        // 批量删除主表数据
        OqlDeleteInfo mainDeleteInfo = infoParser.getSelfDeleteInfo();
        OqlDeleteStatement mainStmt = mainDeleteInfo.getStatement();
        SqlDeleteStatementBuilder builder = new SqlDeleteStatementBuilder();
        SqlDeleteStatement mainSqlStmt = Oql2SqlUtils.toSqlDelete(mainStmt, builder);
        ;
        int[] effectedRowsArray = this.repository.batchDelete(mainSqlStmt, paramMaps);
        this.sumAndLogsumEffectedRows(effectedRowsArray, stmt);

        // 循环批量删除子表数据
        List<OqlDetailDeleteInfo> detailDeleteInfos = infoParser.getDetailDeleteInfos();
        if (detailDeleteInfos != null && detailDeleteInfos.size() > 0) {
            // 假设OQL语句中一定带了主表记录ID的条件，并且是变量，然后从paramMaps中抽取ID数据
            List<SqlExpr> masterIdExprs = checker.getMasterIdExprs();
            if (CollectionUtils.isEmpty(masterIdExprs)) {
                throw new FastOqlException("批量删除OQL where条件中未指明主表记录ID");
            }
            if (!(masterIdExprs.get(0) instanceof SqlVariantRefExpr)) {
                throw new FastOqlException("批量删除OQL where条件中未指明主表记录ID的变量表达式");
            }

            // 依次从变量中抽取ID数据
            String masterVarName = ((SqlVariantRefExpr) masterIdExprs.get(0)).getVarName();
            List<Object> recordIds = new ArrayList<>();
            for (Map<String, Object> paramMap : paramMaps) {
                recordIds.add(paramMap.get(masterVarName));
            }

            for (OqlDetailDeleteInfo detailDeleteInfo : detailDeleteInfos) {
                XObject detailObject = detailDeleteInfo.getObject();
                String mainObjetName = mainDeleteInfo.getStatement().getFrom().getResolvedObject().getName();
                XObjectRefField masterField = detailObject.getObjectRefField(mainObjetName);
                // delete from detailObject where masterId in (#{masterIds})
                SqlDeleteStatement detailDeleteSqlStmt = new SqlDeleteStatement();
                detailDeleteSqlStmt.setFrom(new SqlExprTableSource(detailObject.getTableName()));
                SqlInListExpr where = OqlUtils.buildFieldInListVarRefExpr(masterField);
                detailDeleteSqlStmt.setWhere(where);
                Map<String, Object> detailParamMap = new HashMap<>();
                detailParamMap.put(XObjectUtils.getFieldNamePlural(masterField), recordIds);
                this.repository.delete(detailDeleteSqlStmt, detailParamMap);
            }
        }

        return effectedRowsArray;
    }

    /**
     * 汇总总影响行数
     *
     * @param effectedRowsArray
     * @return
     */
    private int sumAndLogsumEffectedRows(int[] effectedRowsArray, OqlStatement stmt) {
        int totalEffectedRows = 0;
        for (int i = 0, l = effectedRowsArray.length; i < l; i++) {
            totalEffectedRows += effectedRowsArray[i];
        }

        if (totalEffectedRows == 0) {
            logger.warn("OQL批量执行异常，总影响行数：0，OQL：{}", stmt);
        } else {
            logger.warn("OQL批量执行成功，总影响行数：{}，OQL：{}", totalEffectedRows, stmt);
        }

        return totalEffectedRows;
    }

}
