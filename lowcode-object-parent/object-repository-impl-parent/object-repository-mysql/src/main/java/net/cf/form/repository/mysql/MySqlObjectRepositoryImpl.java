package net.cf.form.repository.mysql;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.mysql.jdbc.AdvancedMapSqlParameterSource;
import net.cf.form.repository.mysql.jdbc.BatchInsertJdbcTemplate;
import net.cf.form.repository.mysql.util.SqlUtils;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlObjectRepositoryImpl implements ObjectRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MySqlObjectRepositoryImpl.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private JdbcTemplate template;

    public MySqlObjectRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, JdbcTemplate template) {
        this.jdbcTemplate = jdbcTemplate;
        this.template = template;
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        return this.jdbcTemplate.queryForMap(sql, new HashMap<>());
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        return this.jdbcTemplate.queryForMap(sql, new AdvancedMapSqlParameterSource(paramMap));
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        return this.jdbcTemplate.queryForList(sql, new HashMap<>());
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        return this.jdbcTemplate.queryForList(sql, new AdvancedMapSqlParameterSource(paramMap));
    }

    @Override
    public int insert(SqlInsertStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        int effectedRows = this.jdbcTemplate.update(sql, new HashMap<>());
        LOGGER.info("数据插入成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int insert(SqlInsertStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        int effectedRows;
        String autoGenColumn = statement.getAutoGenColumn();
        if (autoGenColumn != null && autoGenColumn.length() > 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource paramSource = SqlUtils.convertInsertParamMap(statement, paramMap);
            effectedRows = this.jdbcTemplate.update(sql, paramSource, keyHolder);
            if (statement.getValuesList().size() == 1) {
                paramMap.put(autoGenColumn, keyHolder.getKey());
            } else {
                List<Map<String, Object>> keysList = keyHolder.getKeyList();
                List<Object> autoGenColumnValues = new ArrayList<>();
                for (Map<String, Object> keys : keysList) {
                    autoGenColumnValues.add(keys.get(autoGenColumn));
                }
                paramMap.put(autoGenColumn, autoGenColumnValues);
            }
        } else {
            SqlParameterSource paramSource = SqlUtils.convertInsertParamMap(statement, paramMap);
            effectedRows = this.jdbcTemplate.update(sql, paramSource);
        }

        LOGGER.info("数据插入成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int[] batchInsert(SqlInsertStatement statement, List<Map<String, Object>> paramMaps) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        SqlParameterSource[] paramSources = SqlUtils.convertInsertParamMaps(statement, paramMaps);
        int[] effectedRowsArray;
        String autoPrimaryKey = statement.getAutoGenColumn();
        if (autoPrimaryKey != null && autoPrimaryKey.length() > 0) {
            BatchInsertJdbcTemplate batchInsertJdbcTemplate = new BatchInsertJdbcTemplate(template);
            effectedRowsArray = batchInsertJdbcTemplate.batchInsert(statement, paramMaps);
        } else {
            effectedRowsArray = this.jdbcTemplate.batchUpdate(sql, paramSources);
        }

        int effectedRows = 0;
        for (int i = 0, l = effectedRowsArray.length; i < l; i++) {
            effectedRows += effectedRowsArray[i];
        }
        LOGGER.info("数据插入成功，影响行数：{}", effectedRows);

        return effectedRowsArray;
    }

    @Override
    public int update(SqlUpdateStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        int effectedRows = this.jdbcTemplate.update(sql, new HashMap<>());
        LOGGER.info("数据更新成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int update(SqlUpdateStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        SqlParameterSource paramSource = SqlUtils.convertUpdateParamMap(statement, paramMap);
        int effectedRows = this.jdbcTemplate.update(sql, paramSource);
        LOGGER.info("数据更新成功，影响行数：{}", effectedRows);

        return effectedRows;
    }

    @Override
    public int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMaps) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        SqlParameterSource[] paramSources = SqlUtils.convertUpdateParamMaps(statement, paramMaps);
        int[] effectedRowsArray = this.jdbcTemplate.batchUpdate(sql, paramSources);
        int totalEffectedRows = this.sumTotalEffectedRows(effectedRowsArray);
        LOGGER.info("批量数据更新成功，总影响行数：{}", totalEffectedRows);

        return effectedRowsArray;
    }

    @Override
    public int delete(SqlDeleteStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        int effectedRows = this.jdbcTemplate.update(sql, new HashMap<>());
        LOGGER.info("数据删除成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int delete(SqlDeleteStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        int effectedRows = this.jdbcTemplate.update(sql, paramMap);
        LOGGER.info("数据删除成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMaps) {
        String sql = SqlUtils.toSqlText(statement);
        LOGGER.info("目标执行的SQL语句为：{}", sql);

        SqlParameterSource[] paramSources = SqlUtils.convertParamMaps(paramMaps);
        int[] effectedRowsArray = this.jdbcTemplate.batchUpdate(sql, paramSources);
        int totalEffectedRows = this.sumTotalEffectedRows(effectedRowsArray);
        LOGGER.info("批量数据删除成功，总影响行数：{}", totalEffectedRows);

        return effectedRowsArray;
    }

    /**
     * 汇总总影响行数
     *
     * @param effectedRowsArray
     * @return
     */
    private int sumTotalEffectedRows(int[] effectedRowsArray) {
        int effectedRows = 0;
        for (int i = 0, l = effectedRowsArray.length; i < l; i++) {
            effectedRows += effectedRowsArray[i];
        }
        return effectedRows;
    }

}
