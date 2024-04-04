package net.cf.form.repository.mysql.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BatchInsertPreparedStatementCallback implements PreparedStatementCallback<int[]> {

    private static final Logger logger = LoggerFactory.getLogger(BatchInsertPreparedStatementCallback.class);

    private final String sql;

    private final BatchPreparedStatementSetter pss;

    private List<Map<String, Object>> generatedKeys;

    public BatchInsertPreparedStatementCallback(String sql, BatchPreparedStatementSetter pss) {
        this.sql = sql;
        this.pss = pss;
    }

    public List<Map<String, Object>> getGeneratedKeys() {
        return generatedKeys;
    }

    @Override
    public int[] doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
        if (logger.isDebugEnabled())
            logger.debug("Executing batch SQL update and returning " +
                    "generated keys [" + sql + "]");

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        try {
            int batchSize = pss.getBatchSize();
            int totalRowsAffected = 0;
            int[] rowsAffected = new int[batchSize];
            this.generatedKeys = generatedKeyHolder.getKeyList();
            ResultSet keys = null;
            for (int i = 0; i < batchSize; i++) {
                pss.setValues(ps, i);
                rowsAffected[i] = ps.executeUpdate();
                totalRowsAffected += rowsAffected[i];
                try {
                    keys = ps.getGeneratedKeys();
                    if (keys != null) {
                        RowMapper rowMapper = new ColumnMapRowMapper();
                        RowMapperResultSetExtractor rse = new RowMapperResultSetExtractor(rowMapper, 1);
                        generatedKeys.addAll(rse.extractData(keys));
                    }
                } finally {
                    JdbcUtils.closeResultSet(keys);
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("SQL batch update affected "
                        + totalRowsAffected + " rows and returned "
                        + generatedKeys.size() + " keys");
            }
            return rowsAffected;
        } finally {
            if (pss instanceof ParameterDisposer) {
                ((ParameterDisposer) pss).cleanupParameters();
            }
        }
    }
}
