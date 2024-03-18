package net.cf.form.repository.mysql;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.mysql.util.SqlUtils;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlObjectRepositoryImpl implements ObjectRepository {

    private final Logger logger = LoggerFactory.getLogger(MySqlObjectRepositoryImpl.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlObjectRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        String autoPrimaryKey = statement.getAutoGenColumn();
        int effectedRows;
        if (autoPrimaryKey != null && autoPrimaryKey.length() > 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            MapSqlParameterSource parameterSource = new AdvancedMapSqlParameterSource(paramMap);
            effectedRows = this.jdbcTemplate.update(sql, parameterSource, keyHolder);
            String autoId = String.valueOf(keyHolder.getKey());
            paramMap.put(autoPrimaryKey, autoId);
        } else {
            effectedRows = this.jdbcTemplate.update(sql, new AdvancedMapSqlParameterSource(paramMap));
        }

        logger.info("数据插入成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int[] batchInsert(SqlInsertStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
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
        int effectedRows = this.jdbcTemplate.update(sql, new AdvancedMapSqlParameterSource(paramMap));
        logger.info("数据更新成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
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
    public int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

}
