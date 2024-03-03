package net.cf.form.repository.mysql;

import net.cf.form.repository.ObjectRepository;
import net.cf.form.repository.mysql.util.SqlUtils;
import net.cf.form.repository.sql.ast.statement.SqlDeleteStatement;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlUpdateStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLObjectRepositoryImpl implements ObjectRepository {

    private final Logger logger = LoggerFactory.getLogger(MySQLObjectRepositoryImpl.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MySQLObjectRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        // TODO 判断是否存在自增主键
        int effectedRows = this.jdbcTemplate.update(sql, paramMap);
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
        return 0;
    }

    @Override
    public int[] batchUpdate(SqlUpdateStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public int delete(SqlDeleteStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        int effectedRows = this.jdbcTemplate.update(sql, new HashMap<>());
        logger.info("数据更新成功，影响行数：{}", effectedRows);
        return effectedRows;
    }

    @Override
    public int delete(SqlDeleteStatement statement, Map<String, Object> paramMap) {
        return 0;
    }

    @Override
    public int[] batchDelete(SqlDeleteStatement statement, List<Map<String, Object>> paramMapList) {
        return new int[0];
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        return jdbcTemplate.queryForMap(sql, new HashMap<>());
    }

    @Override
    public Map<String, Object> selectOne(SqlSelectStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        return jdbcTemplate.queryForMap(sql, paramMap);
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement) {
        String sql = SqlUtils.toSqlText(statement);
        return jdbcTemplate.queryForList(sql, new HashMap<>());
    }

    @Override
    public List<Map<String, Object>> selectList(SqlSelectStatement statement, Map<String, Object> paramMap) {
        String sql = SqlUtils.toSqlText(statement);
        return jdbcTemplate.queryForList(sql, paramMap);
    }
}
