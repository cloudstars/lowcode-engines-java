package net.cf.form.engine.repository.mysql.data;

import net.cf.form.engine.repository.data.JoinType;
import net.cf.form.engine.repository.mysql.statement.select.SqlBuildResult;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC查询行映射器
 *
 * @author clouds
 */
public class SelectRowMapper implements RowMapper<Map<String, Object>>, Serializable {

    /**
     * SQL查询结束转化之后输出的符合对象层需要的数据
     * <p>
     * 每条数据的格式：{
     * "f1": "xxx",
     * "f2": "yyy",
     * "detailField": [{}, {}],
     * "one2manyLookupField": [{},{}],
     * "one2oneLookupField": {...},
     * "masterField": {...}
     * }
     * <p>
     * 上面的数据中:
     * f1, f2, ...表示本对象的字段,
     * detailField 表示子表的字段,
     * one2manyLookupField表示一对多的相关字段(多对多的不在此类中处理)
     * one2oneLookupField表示一对一的相关字段，
     * masterField表示主表字段
     */
    private final List<Map<String, Object>> resultMapList = new ArrayList<>();

    /**
     * 本表的记录ID与数据的映射关系
     */
    private final Map<Object, Map<String, Object>> selfRecordDataMaps = new HashMap<>();

    /**
     * 当前表的记录ID与子表的记录ID列表的映射关系
     *
     * KEY为本表的记录ID，Value是多个子表的子表记录列表（KEY为子表的别名，VALUE为对应子表的ID列表）
     */
    private final Map<Object, Map<String, List<Object>>> selfDetailIdsMapMap = new HashMap<>();

    /**
     * SQL构建的结果
     */
    private SqlBuildResult sqlBuildResult;

    public SelectRowMapper(SqlBuildResult sqlBuildResult) {
        this.sqlBuildResult = sqlBuildResult;
    }

    @Override
    public Map<String, Object> mapRow(ResultSet resultSet, int i) throws SQLException {
        Map<String, Object> dataMap = new HashMap<>();
        int columnCount = resultSet.getMetaData().getColumnCount();
        for (int ci = 0; ci < columnCount; ci++) {
            dataMap.put(String.valueOf(ci), resultSet.getObject(ci + 1));
        }

        // 合并数据到结果列表
        this.addToResultMapList(dataMap);

        return dataMap;
    }

    /**
     * 将当前行的数据添加到主数据
     *
     * @param dataMap
     */
    private void addToResultMapList(Map<String, Object> dataMap) {
        SqlBuildResult.SelectObjectInfo selfObjectInfo = sqlBuildResult.getSelfSelectObjectInfo();
        final Map<String, Object> finalSelfDataMap = this.collectSelfDataMap(selfObjectInfo, dataMap);
        Object primaryFieldValue = dataMap.get(String.valueOf(selfObjectInfo.primaryIndex));

        //  收集子表数据
        this.collectDetailDataMapList(dataMap, finalSelfDataMap, primaryFieldValue);

        // 收集主表数据
        this.collectMasterDataMap(dataMap, finalSelfDataMap, primaryFieldValue);

        // 收集相关表数据
        this.collectLookupDataMap(dataMap, finalSelfDataMap, primaryFieldValue);
    }

    /**
     * 收集本表的数据，如果存在则直接返回
     *
     * @param selfObjectInfo
     * @param dataMap
     * @return
     */
    private Map<String, Object> collectSelfDataMap(SqlBuildResult.SelectObjectInfo selfObjectInfo, Map<String, Object> dataMap) {
        Map<String, Object> selfDataMap;
        if (selfObjectInfo.primaryIndex < 0) {
            // 不存在主键（肯定也不会查询子表、主表、相关表)
            selfDataMap = this.collectSubDataMap(dataMap, 0, selfObjectInfo.endIndex);
            resultMapList.add(selfDataMap);
        } else {
            Object primaryFieldValue = dataMap.get(String.valueOf(selfObjectInfo.primaryIndex));
            selfDataMap = selfRecordDataMaps.get(primaryFieldValue);
            if (selfDataMap == null) {
                // 第一次出现本表的记录，生成初始数据
                int s = selfObjectInfo.startIndex;
                int e = selfObjectInfo.endIndex;
                selfDataMap = this.collectSubDataMap(dataMap, s, e);
                resultMapList.add(selfDataMap);
                selfRecordDataMaps.put(primaryFieldValue, selfDataMap);
            }
        }

        return selfDataMap;
    }

    /**
     * 收集子表的数据列表
     *
     * @param dataMap           当前数据库查询行的数据
     * @param selfDataMap       合并后的当前表的记录行
     * @param primaryFieldValue 合并后的当前表记录行的主键值
     */
    private void collectDetailDataMapList(final Map<String, Object> dataMap, final Map<String, Object> selfDataMap, final Object primaryFieldValue) {
        sqlBuildResult.getJoinSelectObjectInfos().forEach((objectAliasName, detailObjectInfo) -> {
            if (detailObjectInfo.joinType != JoinType.DETAIL) {
                return;
            }

            List<Map<String, Object>> detailDataMapList = (List<Map<String, Object>>) selfDataMap.get(objectAliasName);
            if (detailDataMapList == null) {
                detailDataMapList = new ArrayList<>();
                selfDataMap.put(objectAliasName, detailDataMapList);
            }

            Map<String, List<Object>> selfDetailIdsMap = selfDetailIdsMapMap.get(primaryFieldValue);
            if (selfDetailIdsMap == null) {
                selfDetailIdsMap = new HashMap<>();
                selfDetailIdsMapMap.put(primaryFieldValue, selfDetailIdsMap);
            }

            List<Object> detailRecordIds = selfDetailIdsMap.get(objectAliasName);
            if (detailRecordIds == null) {
                detailRecordIds = new ArrayList<>();
                selfDetailIdsMap.put(objectAliasName, detailRecordIds);
            }

            Object detailPrimaryFieldValue = dataMap.get(String.valueOf(detailObjectInfo.primaryIndex));
            if (!detailRecordIds.contains(detailPrimaryFieldValue)) {
                detailRecordIds.add(detailPrimaryFieldValue);
                // 第一次出现主表关联的子表记录的ID，生成初始数据
                int s = detailObjectInfo.startIndex;
                int e = detailObjectInfo.endIndex;
                detailDataMapList.add(this.collectSubDataMap(dataMap, s, e));
            }
        });
    }

    /**
     * 收集主表的数据
     *
     * @param dataMap           当前数据库查询行的数据
     * @param selfDataMap       合并后的当前表的记录行
     * @param primaryFieldValue 合并后的当前表记录行的主键值
     */
    private void collectMasterDataMap(final Map<String, Object> dataMap, final Map<String, Object> selfDataMap, final Object primaryFieldValue) {
        sqlBuildResult.getJoinSelectObjectInfos().forEach((objectAliasName, masterObjectInfo) -> {
            if (masterObjectInfo.joinType != JoinType.MASTER) {
                return;
            }

            Map<String, Object> masterDataMap = (Map<String, Object>) selfDataMap.get(objectAliasName);
            if (masterDataMap == null) {
                masterDataMap = new HashMap<>();
                selfDataMap.put(objectAliasName, masterDataMap);

                // 第一次出现本表关联的主表记录的ID，生成初始数据
                int s = masterObjectInfo.startIndex;
                int e = masterObjectInfo.endIndex;
                masterDataMap.putAll(this.collectSubDataMap(dataMap, s, e));
            }
        });
    }

    /**
     * 收集相关表（仅一对一的相关表）的数据
     *
     * @param dataMap           当前数据库查询行的数据
     * @param selfDataMap       合并后的当前表的记录行
     * @param primaryFieldValue 合并后的当前表记录行的主键值
     */
    private void collectLookupDataMap(final Map<String, Object> dataMap, final Map<String, Object> selfDataMap, final Object primaryFieldValue) {
        sqlBuildResult.getJoinSelectObjectInfos().forEach((objectAliasName, lookupObjectInfo) -> {
            if (lookupObjectInfo.joinType != JoinType.LOOKUP) {
                return;
            }

            Map<String, Object> lookupDataMap = (Map<String, Object>) selfDataMap.get(objectAliasName);
            if (lookupDataMap == null) {
                lookupDataMap = new HashMap<>();
                selfDataMap.put(objectAliasName, lookupDataMap);

                // 第一次出现本表关联的主表记录的ID，生成初始数据
                int s = lookupObjectInfo.startIndex;
                int e = lookupObjectInfo.endIndex;
                lookupDataMap.putAll(this.collectSubDataMap(dataMap, s, e));
            }
        });
    }


    /**
     * 从主数据中截取一段子数据
     *
     * @param sourceMap
     * @param start
     * @param end
     * @return
     */
    private Map<String, Object> collectSubDataMap(Map<String, Object> sourceMap, int start, int end) {
        Map<String, Object> targetMap = new HashMap<>();
        for (int i = start; i < end; i++) {
            SqlBuildResult.SelectItemInfo itemInfo = sqlBuildResult.getSelectItemInfoAt(i);
            if (!itemInfo.isExtra) {
                String name = itemInfo.alias;
                targetMap.put(name, sourceMap.get(String.valueOf(i)));
            }
        }

        return targetMap;
    }

    /**
     * 返回合并后的数据
     *
     * @return
     */
    public List<Map<String, Object>> getResultMapList() {
        return resultMapList;
    }
}
