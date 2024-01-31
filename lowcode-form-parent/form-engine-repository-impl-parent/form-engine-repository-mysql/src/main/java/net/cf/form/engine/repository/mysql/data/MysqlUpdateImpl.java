package net.cf.form.engine.repository.mysql.data;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.data.MasterDataField;
import net.cf.form.engine.repository.mysql.statement.OqlExprAstVisitor;
import net.cf.form.engine.repository.mysql.statement.update.UpdateSqlBuilder;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlWhereClause;
import net.cf.form.engine.repository.sql_bak.UpdateDetailSqlInfo;
import net.cf.form.engine.repository.sql_bak.UpdateSqlInfo;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlUpdateImpl extends AbstractMysqlCurd {

    public MysqlUpdateImpl(NamedParameterJdbcTemplate jdbcTemplate, DataObjectResolver resolver) {
        super(jdbcTemplate, resolver);
    }

    /**
     * 根据where条件查询子表中的ID和MasterId列，并以键值对的形式返回
     *
     * @param sqlInfo
     * @param paramMap
     * @return
     */
    public Map<Object, List<Object>> queryForMasterIdMap(UpdateDetailSqlInfo sqlInfo, Map<String, Object> paramMap) {
        DataObject object = sqlInfo.getObject();
        DataObject masterObject = sqlInfo.getMasterSqlInfo().getObject();
        OqlUpdateStatement masterStatement = sqlInfo.getMasterSqlInfo().getStatement();

        // 先查出所有命中的主表ID
        OqlWhereClause masterWhereClause = masterStatement.getWhereClause();
        // TODO 如果where子句中带了主键字段，可减少一次查询
        DataField masterPrimaryField = masterObject.getPrimaryField();
        OqlExprAstVisitor masterExprVisitor = new OqlExprAstVisitor(masterObject);
        String masterWhereSql = masterExprVisitor.getSql(masterWhereClause.getExpr());
        String masterIdsSql = "select " + masterPrimaryField.getColumnName() + " as " + masterPrimaryField.getName() + " from " + masterObject.getTableName() + " where " + masterWhereSql;
        List<Map<String, Object>> masterIds = jdbcTemplate.queryForList(masterIdsSql, paramMap);

        // 再根据命中的主表ID查询相关的子表ID
        DataField primaryField = object.getPrimaryField();
        String primaryColumnName = primaryField.getColumnName();
        String primaryFieldName = primaryField.getName();
        MasterDataField masterField = object.getMasterField();
        String masterColumnName = masterField.getColumnName();
        String masterFieldName = masterField.getName();
        String masterDetailIdsSql = "select " + primaryColumnName + " as " + primaryFieldName + ", " + masterColumnName + " as " + masterFieldName +
                " from " + object.getTableName() +
                " where " + masterColumnName + " in (";
        for (Map<String, Object> masterId : masterIds) {
            Object mid = masterId.get(masterPrimaryField.getName());
            if (mid instanceof String) {
                masterDetailIdsSql += "\''" + mid + "\'";
            } else {
                masterDetailIdsSql += mid.toString();
            }
        }
        masterDetailIdsSql += ")";
        List<Map<String, Object>> resultMapList = jdbcTemplate.queryForList(masterDetailIdsSql, (Map<String, ?>) null);
        Map<Object, List<Object>> masterIdsMap = new HashMap<>();
        for (Map<String, Object> resultMap : resultMapList) {
            Object masterId = resultMap.get(masterFieldName);
            List<Object> detailIds = masterIdsMap.get(masterId);
            if (detailIds == null) {
                detailIds = new ArrayList<>();
                masterIdsMap.put(masterId, detailIds);
            }
            detailIds.add(resultMap.get(primaryFieldName));
        }

        return masterIdsMap;
    }


    public int updateSelf(UpdateSqlInfo sqlInfo) {
        return this.updateSelf(sqlInfo, null);
    }

    /**
     * 更新本表
     *
     * @param sqlInfo
     * @param paramMap
     * @return
     */
    public int updateSelf(UpdateSqlInfo sqlInfo, Map<String, Object> paramMap) {
        String sql = new UpdateSqlBuilder(sqlInfo).getSql();
        int effectRows = jdbcTemplate.update(sql, paramMap);

        return effectRows;
    }

    /**
     * 批量更新本表
     *
     * @param sqlInfo
     * @param paramMapList
     * @return
     */
    public int[] batchUpdateSelf(UpdateSqlInfo sqlInfo, List<Map<String, Object>> paramMapList) {
        String sql = new UpdateSqlBuilder(sqlInfo).getSql();
        SqlParameterSource[] parameterSources = new SqlParameterSource[paramMapList.size()];
        for (int i = 0, l = parameterSources.length; i < l; i++) {
            parameterSources[i] = new MapSqlParameterSource(paramMapList.get(i));
        }
        return jdbcTemplate.batchUpdate(sql, parameterSources);
    }
}