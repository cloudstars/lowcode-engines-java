package net.cf.form.repository.mysql.jdbc;

import net.cf.form.repository.mysql.util.SqlUtils;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class BatchInsertJdbcTemplate {

    private final Logger logger = LoggerFactory.getLogger(BatchInsertJdbcTemplate.class);

    private final JdbcTemplate jdbcTemplate;

    public BatchInsertJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 批量插入
     *
     * @param stmt
     * @param paramMaps
     * @return
     */
    public int[] batchInsert(SqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        // 将变量替换为问号
        String sql = SqlUtils.toSqlText(stmt);
        String targetSql = sql.replaceAll(":[\\w\\.]+", "?");
        logger.info("目标执行的SQL语句为：" + targetSql);

        BatchPreparedStatementSetter pss = new MapListBatchPreparedStatementSetter(stmt, paramMaps);
        BatchInsertPreparedStatementCallback callback = new BatchInsertPreparedStatementCallback(sql, pss);
        int[] effectedRowArray = jdbcTemplate.execute(conn -> conn.prepareStatement(targetSql, Statement.RETURN_GENERATED_KEYS), callback);
        // 将返回的主键添加到入参中
        List<Map<String, Object>> keys = callback.getGeneratedKeys();
        for (int i = 0, l = paramMaps.size(); i < l; i++) {
            paramMaps.get(i).putAll(keys.get(i));
        }
        return effectedRowArray;
    }
}