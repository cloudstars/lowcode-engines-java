package net.cf.object.engine.sql;


import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import net.cf.object.engine.data.DefaultParameterMapper;
import net.cf.object.engine.data.FieldMapping;
import net.cf.object.engine.data.ParameterMapper;
import net.cf.object.engine.object.XField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * SQL 指令执行器
 *
 * @author clouds
 */
public final class SqlCmdExecutor {

    private final static Logger LOGGER = LoggerFactory.getLogger(SqlCmdExecutor.class);

    private final ObjectRepository repository;

    public SqlCmdExecutor(ObjectRepository repository) {
        this.repository = repository;
    }

    /**
     * 执行插入指令
     *
     * @param insertCmd
     * @return
     */
    public Object executeInsert(SqlInsertCmd insertCmd) {
        if (!insertCmd.isBatch()) {
            return this.insert(insertCmd);
        } else {
            return this.batchInsert(insertCmd);
        }
    }

    /**
     * 执行单条插入执令
     *
     * @param insertCmd
     * @return
     */
    public int insert(SqlInsertCmd insertCmd) {
        assert (!insertCmd.isBatch());

        SqlInsertStatement stmt = insertCmd.getStatement();
        Map<String, Object> paramMap = insertCmd.getParamMap();
        List<FieldMapping> fieldMappings = insertCmd.getFieldMappings();
        Map<String, Object> targetParamMap = paramMap;
        if (paramMap != null && !CollectionUtils.isEmpty(fieldMappings)) {
            DefaultParameterMapper paramMapper = new DefaultParameterMapper(fieldMappings);
            targetParamMap = this.convertParameterMap(paramMapper, paramMap);
        }

        // 调用驱动层插入数据
        int effectedRows = this.repository.insert(stmt, targetParamMap);
        LOGGER.debug("插入SQL: {} 执行成功, 影响行数: {}", stmt, effectedRows);

        // 将自动生成的列的值放入原参数中
        if (paramMap != null) {
            XField primaryField = insertCmd.getResolvedObject().getPrimaryField();
            if (primaryField.isAutoGen()) {
                Object primaryFieldValue = targetParamMap.get(primaryField.getColumnName());
                paramMap.put(primaryField.getName(), primaryFieldValue);
            }
        }

        return effectedRows;
    }

    /**
     * 执行批量插入指入
     *
     * @param insertCmd
     * @return
     */
    public int[] batchInsert(SqlInsertCmd insertCmd) {
        assert (insertCmd.isBatch());

        SqlInsertStatement stmt = insertCmd.getStatement();
        List<Map<String, Object>> paramMaps = insertCmd.getParamMaps();
        List<FieldMapping> fieldMappings = insertCmd.getFieldMappings();
        List<Map<String, Object>> targetParamMaps = paramMaps;
        if (paramMaps != null && !CollectionUtils.isEmpty(fieldMappings)) {
            DefaultParameterMapper paramMapper = new DefaultParameterMapper(fieldMappings);
            targetParamMaps = this.convertParameterMaps(paramMapper, paramMaps);
        }

        int[] effectedRowArray = this.repository.batchInsert(stmt, targetParamMaps);
        this.logEffectedRows(stmt, effectedRowArray);

        // 将自动生成的列的值放入原参数中
        if (paramMaps != null) {
            XField primaryField = insertCmd.getResolvedObject().getPrimaryField();
            if (primaryField.isAutoGen()) {
                int paramSize = paramMaps.size();
                String primaryFieldName = primaryField.getName();
                String primaryFieldColumn = primaryField.getColumnName();
                for (int i = 0; i < paramSize; i++) {
                    Object primaryFieldValue = targetParamMaps.get(i).get(primaryFieldColumn);
                    paramMaps.get(i).put(primaryFieldName, primaryFieldValue);
                }
            }
        }

        return effectedRowArray;
    }

    /**
     * 执行更新指令
     *
     * @param updateCmd
     * @return
     */
    public Object executeUpdate(SqlUpdateCmd updateCmd) {
        if (!updateCmd.isBatch()) {
            return this.update(updateCmd);
        } else {
            return this.batchUpdate(updateCmd);
        }
    }

    /**
     * 执行单条更新执令
     *
     * @param updateCmd
     * @return
     */
    public int update(SqlUpdateCmd updateCmd) {
        assert (!updateCmd.isBatch());

        SqlUpdateStatement stmt = updateCmd.getStatement();
        Map<String, Object> paramMap = updateCmd.getParamMap();
        Map<String, Object> targetParamMap = paramMap;
        List<FieldMapping> fieldMappings = updateCmd.getFieldMappings();
        if (paramMap != null && !CollectionUtils.isEmpty(fieldMappings)) {
            DefaultParameterMapper paramMapper = new DefaultParameterMapper(fieldMappings);
            targetParamMap = this.convertParameterMap(paramMapper, paramMap);
        }

        // 调用驱动层更新操作
        int effectedRows = this.repository.update(stmt, targetParamMap);
        LOGGER.debug("更新SQL: {} 执行成功, 影响行数: {}", stmt, effectedRows);

        return effectedRows;
    }

    /**
     * 执行批量更新执令
     *
     * @param updateCmd
     * @return
     */
    public int[] batchUpdate(SqlUpdateCmd updateCmd) {
        assert (updateCmd.isBatch());

        SqlUpdateStatement stmt = updateCmd.getStatement();
        List<Map<String, Object>> paramMaps = updateCmd.getParamMaps();

        // 调用驱动层批量更新操作
        int[] effectedRowsArray = this.repository.batchUpdate(stmt, paramMaps);
        this.logEffectedRows(stmt, effectedRowsArray);

        return effectedRowsArray;
    }

    /**
     * 执行删除指令
     *
     * @param deleteCmd
     * @return
     */
    public Object executeDelete(SqlDeleteCmd deleteCmd) {
        if (!deleteCmd.isBatch()) {
            return this.delete(deleteCmd);
        } else {
            return this.batchDelete(deleteCmd);
        }
    }

    /**
     * 执行单条删除执令
     *
     * @param deleteCmd
     * @return
     */
    public int delete(SqlDeleteCmd deleteCmd) {
        assert (!deleteCmd.isBatch());

        SqlDeleteStatement stmt = deleteCmd.getStatement();
        Map<String, Object> paramMap = deleteCmd.getParamMap();

        // 调用驱动层删除操作
        int effectedRows = this.repository.delete(stmt, paramMap);
        LOGGER.debug("删除SQL: {} 执行成功, 影响行数: {}", stmt, effectedRows);

        return effectedRows;
    }

    /**
     * 执行批量删除执令
     *
     * @param deleteCmd
     * @return
     */
    public int[] batchDelete(SqlDeleteCmd deleteCmd) {
        assert (deleteCmd.isBatch());

        SqlDeleteStatement stmt = deleteCmd.getStatement();
        List<Map<String, Object>> paramMaps = deleteCmd.getParamMaps();

        // 调用驱动层删除操作
        int[] effectedRowsArray = this.repository.batchDelete(stmt, paramMaps);
        this.logEffectedRows(stmt, effectedRowsArray);

        return effectedRowsArray;
    }

    /**
     * 打印批量执行日志
     *
     * @param stmt
     * @param effectedRowsArray
     * @return
     */
    private int logEffectedRows(SqlStatement stmt, int[] effectedRowsArray) {
        int totalEffectedRows = 0;
        for (int i = 0, l = effectedRowsArray.length; i < l; i++) {
            totalEffectedRows += effectedRowsArray[i];
        }

        if (totalEffectedRows == 0) {
            LOGGER.debug("批量SQL: {} 执行异常, 影响行数: 0", stmt);
        } else {
            LOGGER.debug("批量SQL: {} 执行成功, 影响行数: {}", stmt, totalEffectedRows);
        }

        return totalEffectedRows;
    }


    /**
     * 转换输入参数，将paramMap中的key（字段名）转换为列名
     *
     * @param paramMapper
     * @param paramMapList
     * @return 生成新的对象返回
     */
    private List<Map<String, Object>> convertParameterMaps(ParameterMapper paramMapper, List<Map<String, Object>> paramMapList) {
        List<Map<String, Object>> targetMapList = new ArrayList<>();
        for (Map<String, Object> paramaeterMap : paramMapList) {
            targetMapList.add(this.convertParameterMap(paramMapper, paramaeterMap));
        }
        return targetMapList;
    }

    /**
     * 转换输入参数，将paramMap中的key（字段名）转换为列名
     *
     * @param paramMapper
     * @param paramMap
     * @return 生成新的对象返回
     */
    private Map<String, Object> convertParameterMap(ParameterMapper paramMapper, Map<String, Object> paramMap) {
        return paramMapper.mapParameter(paramMap);
    }

}
