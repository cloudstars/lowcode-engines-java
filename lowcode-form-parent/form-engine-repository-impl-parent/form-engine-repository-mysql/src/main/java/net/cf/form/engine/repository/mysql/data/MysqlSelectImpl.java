package net.cf.form.engine.repository.mysql.data;

import net.cf.form.engine.repository.data.DataField;
import net.cf.form.engine.repository.data.DataObject;
import net.cf.form.engine.repository.data.DataObjectResolver;
import net.cf.form.engine.repository.data.MasterDataField;
import net.cf.form.engine.repository.mysql.statement.OqlExprAstVisitor;
import net.cf.form.engine.repository.mysql.statement.select.SelectSqlBuilder;
import net.cf.form.engine.repository.oql.ast.statement.OqlWhereClause;
import net.cf.form.engine.repository.sql_bak.AbstractQueryableSqlInfo;
import net.cf.form.engine.repository.sql_bak.SelectSqlInfo;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlSelectImpl extends AbstractMysqlCurd {

    public MysqlSelectImpl(NamedParameterJdbcTemplate jdbcTemplate, DataObjectResolver resolver) {
        super(jdbcTemplate, resolver);
    }

    /**
     * 主查询
     *
     * @param sqlInfo
     * @param paramMap
     * @return
     */
    public List<Map<String, Object>> select(SelectSqlInfo sqlInfo, Map<String, Object> paramMap) {
        SelectSqlBuilder sqlBuilder = new SelectSqlBuilder(sqlInfo);
        SelectRowMapper rowMapper = new SelectRowMapper(sqlBuilder.getBuildResult());
        String sql = sqlBuilder.getSql();
        jdbcTemplate.query(sql, paramMap, rowMapper);
        List<Map<String, Object>> resultMapList = rowMapper.getResultMapList();
        return resultMapList;
    }

    /**
     * 查询主表本表的主键id
     *
     * @param masterSqlInfo
     * @param paramMap
     * @return
     */
    public List<Object> queryForMasterIds(AbstractQueryableSqlInfo masterSqlInfo, Map<String, Object> paramMap) {
        DataObject dataObject = masterSqlInfo.getObject();

        // 先查出所有命中的主表ID
        OqlWhereClause masterWhereClause = masterSqlInfo.getWhereClause();
        // TODO 如果where子句中带了主键字段，可减少一次查询
        DataField masterPrimaryField = dataObject.getPrimaryField();
        OqlExprAstVisitor masterExprVisitor = new OqlExprAstVisitor(dataObject);
        String masterWhereSql = masterExprVisitor.getSql(masterWhereClause.getExpr());
        String masterIdsSql = "select " + masterPrimaryField.getColumnName() + " as " + masterPrimaryField.getName() + " from " + dataObject.getTableName() + " where " + masterWhereSql;
        List<Map<String, Object>> dataList = jdbcTemplate.queryForList(masterIdsSql, paramMap);

        List<Object> masterIds = new ArrayList<>();
        for (Map<String, Object> data : dataList) {
            Object mid = data.get(masterPrimaryField.getName());
            masterIds.add(mid);
        }
        return masterIds;
    }

    /**
     * 查询主表和子表主键的映射关系
     *
     * @param detailSqlInfo
     * @param masterIds
     * @param paramMap
     * @return
     */
    public Map<Object, List<Object>> queryForMasterAndDetailIdMap(AbstractQueryableSqlInfo detailSqlInfo, List<Object> masterIds, Map<String, Object> paramMap) {
        DataObject object = detailSqlInfo.getObject();

        // 根据命中的主表ID查询相关的子表ID
        DataField primaryField = object.getPrimaryField();
        String primaryColumnName = primaryField.getColumnName();
        String primaryFieldName = primaryField.getName();
        MasterDataField masterField = object.getMasterField();
        String masterColumnName = masterField.getColumnName();
        String masterFieldName = masterField.getName();
        String masterDetailIdsSql = "select " + primaryColumnName + " as " + primaryFieldName + ", " + masterColumnName + " as " + masterFieldName +
                " from " + object.getTableName() +
                " where " + masterColumnName + " in (";
        for (Object mid : masterIds) {
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

}
