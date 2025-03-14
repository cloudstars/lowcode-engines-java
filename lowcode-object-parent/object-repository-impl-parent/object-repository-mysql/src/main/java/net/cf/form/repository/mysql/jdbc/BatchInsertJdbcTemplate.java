package net.cf.form.repository.mysql.jdbc;

import net.cf.form.repository.mysql.util.SqlUtils;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;

import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * 批量插入专用的JdbcTemplate
 *
 * @author clouds
 */
public class BatchInsertJdbcTemplate {

    private final Logger logger = LoggerFactory.getLogger(BatchInsertJdbcTemplate.class);

    private final JdbcTemplate jdbcTemplate;

    public BatchInsertJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 批量插入
     *
     * @param stmt 插入语句
     * @param paramMaps 插入参数
     * @return 影响行数
     */
    public int[] batchInsert(SqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        assert (!CollectionUtils.isEmpty(paramMaps));

        // 将变量替换为问号
        String sql = SqlUtils.toSqlText(stmt);
        String targetSql = sql.replaceAll(":[\\w\\.]+", "?");
        logger.info("目标执行的批量SQL语句为：" + targetSql);

        BatchPreparedStatementSetter pss = new MapListBatchPreparedStatementSetter(stmt, paramMaps);
        BatchInsertPreparedStatementCallback callback = new BatchInsertPreparedStatementCallback(sql, pss);
        int[] effectedRowArray = jdbcTemplate.execute(conn -> conn.prepareStatement(targetSql, Statement.RETURN_GENERATED_KEYS), callback);
        // 将返回的主键添加到入参中
        List<Map<String, Object>> keys = callback.getGeneratedKeys();
        String autoGenColumn = stmt.getAutoGenColumn();
        for (int i = 0, l = paramMaps.size(); i < l; i++) {
            Map<String, Object> key = keys.get(i);
            if (key.containsKey("GENERATED_KEY")) {// MYSQL特殊，H2是列名
                paramMaps.get(i).put(autoGenColumn, key.get("GENERATED_KEY"));
            } else {
                paramMaps.get(i).put(autoGenColumn, key.get(autoGenColumn));
            }
        }
        return effectedRowArray;
    }

}