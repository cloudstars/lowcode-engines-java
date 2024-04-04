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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlObjectRepositoryImpl implements ObjectRepository {

    private final Logger logger = LoggerFactory.getLogger(MySqlObjectRepositoryImpl.class);

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
        return jdbcTemplate.queryForMap(sql, new HashMap<>());
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        return jdbcTemplate.queryForMap(sql, new AdvancedMapSqlParameterSource(paramMap));
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        return jdbcTemplate.queryForList(sql, new HashMap<>());
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        return jdbcTemplate.queryForList(sql, new AdvancedMapSqlParameterSource(paramMap));
    }

    @Override
    public int insert(SqlInsertStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        int effectedRows = this.jdbcTemplate.update(sql, new HashMap<>());
        logger.info("数据插入成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int insert(SqlInsertStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        int effectedRows;
        String autoPrimaryKey = statement.getAutoGenColumn();
        if (autoPrimaryKey != null && autoPrimaryKey.length() > 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource paramSource = SqlUtils.convertInsertParamMap(statement, paramMap);
            effectedRows = this.jdbcTemplate.update(sql, paramSource, keyHolder);
            String autoId = String.valueOf(keyHolder.getKey());
            paramMap.put(autoPrimaryKey, autoId);
        } else {
            SqlParameterSource paramSource = SqlUtils.convertInsertParamMap(statement, paramMap);
            effectedRows = this.jdbcTemplate.update(sql, paramSource);
        }

        logger.info("数据插入成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int[] batchInsert(SqlInsertStatement statement, List<Map<String, Object>> paramMaps) {
        String sql = SqlUtils.toSqlText(statement);
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
        logger.info("数据插入成功，影响行数：{}", effectedRows);

        return effectedRowsArray;
    }

    @Override
    public int update(SqlUpdateStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        int effectedRows = this.jdbcTemplate.update(sql, new HashMap<>());
        logger.info("数据更新成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int update(SqlUpdateStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        SqlParameterSource paramSource = SqlUtils.convertUpdateParamMap(statement, paramMap);
        int effectedRows = this.jdbcTemplate.update(sql, paramSource);
        logger.info("数据更新成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMaps) {
        SqlParameterSource[] paramSources = SqlUtils.convertUpdateParamMaps(statement, paramMaps);

        return new int[0];
    }

    @Override
    public int delete(SqlDeleteStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        int effectedRows = this.jdbcTemplate.update(sql, new HashMap<>());
        logger.info("数据删除成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int delete(SqlDeleteStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        int effectedRows = this.jdbcTemplate.update(sql, paramMap);
        logger.info("数据删除成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMaps) {
        return new int[0];
    }

}
