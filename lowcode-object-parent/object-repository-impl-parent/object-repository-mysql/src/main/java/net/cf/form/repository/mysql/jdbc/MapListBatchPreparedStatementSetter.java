package net.cf.form.repository.mysql.jdbc;

import net.cf.form.repository.sql.ast.SqlDataType;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlVariantRefExpr;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 基于MapList的批量预处理语句设值器
 *
 * @author clouds
 */
public class MapListBatchPreparedStatementSetter implements BatchPreparedStatementSetter {

    private final SqlInsertStatement stmt;

    private final List<Map<String, Object>> paramMaps;

    public MapListBatchPreparedStatementSetter(SqlInsertStatement stmt, List<Map<String, Object>> paramMaps) {
        this.stmt = stmt;
        this.paramMaps = paramMaps;
    }

    @Override
    public void setValues(PreparedStatement ps, int i) throws SQLException {
        Map<String, Object> paramMap = paramMaps.get(i);
        List<SqlExpr> columns = stmt.getColumns();
        int columnSize = columns.size();
        List<SqlInsertStatement.ValuesClause> valuesClauses = stmt.getValuesList();
        int pi = 1;
        for (int j = 0; j < valuesClauses.size(); j++) {
            SqlInsertStatement.ValuesClause valuesClause = valuesClauses.get(j);
            List<SqlExpr> values = valuesClause.getValues();
            for (int k = 0; k < columnSize; k++) {
                SqlExpr value = values.get(k);
                if (!(value instanceof SqlVariantRefExpr)) {
                    continue;
                }

                String varName = ((SqlVariantRefExpr) value).getVarName();
                Object javaValue = paramMap.get(varName);

                if (javaValue == null) {
                    ps.setObject(pi++, null);
                    continue;
                }

                SqlDataType columnDataType = columns.get(k).computeSqlDataType();
                if (columnDataType == SqlDataType.CHAR) {
                    ps.setString(pi, javaValue.toString());
                } else if (columnDataType == SqlDataType.INTEGER) {
                    ps.setInt(pi, (Integer) javaValue);
                } else if (columnDataType == SqlDataType.LONG) {
                    ps.setLong(pi, (Long) javaValue);
                } else if (columnDataType == SqlDataType.DECIMAL) {
                    if (javaValue instanceof BigDecimal) {
                        ps.setBigDecimal(pi, (BigDecimal) javaValue);
                    } else if (javaValue instanceof Float) {
                        ps.setFloat(pi, (Float) javaValue);
                    } else if (javaValue instanceof Double) {
                        ps.setDouble(pi, (Double) javaValue);
                    } else {
                        ps.setObject(pi, javaValue);
                    }
                } else if (columnDataType == SqlDataType.BOOLEAN) {
                    ps.setBoolean(pi, (Boolean) javaValue);
                } else if (columnDataType == SqlDataType.DATE) {
                    if (value instanceof Date) {
                        ps.setDate(pi, (Date) javaValue);
                    } else if (value instanceof java.util.Date) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime((java.util.Date) javaValue);
                        ps.setDate(pi, new Date(calendar.getTimeInMillis()));
                    } else if (javaValue instanceof Number) {
                        ps.setDate(pi, new Date(((Number) javaValue).longValue()));
                    } else {
                        ps.setString(pi, (String) javaValue);
                    }
                } else if (columnDataType == SqlDataType.TIMESTAMP) {
                    if (javaValue instanceof Timestamp) {
                        ps.setTimestamp(pi, (Timestamp) javaValue);
                    } else if (javaValue instanceof Number) {
                        ps.setTimestamp(pi, new Timestamp(((Number) javaValue).longValue()));
                    } else {
                        ps.setString(pi, (String) javaValue);
                    }
                } else {
                    ps.setObject(pi, javaValue);
                }

                pi++;
            }
        }
    }

    @Override
    public int getBatchSize() {
        return paramMaps.size();
    }
}
