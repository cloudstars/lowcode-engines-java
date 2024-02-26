package net.cf.commons.test.dataset;

import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MysqlDataSetOperator implements IDataSetOperator {

    private JdbcTemplate jdbcTemplate;

    public MysqlDataSetOperator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void setUp(IDataSet dataSet) {
        String[] tableNames = dataSet.getTableNames();
        for (String tableName : tableNames) {
            ITableMetaData tableMetaData = dataSet.getTableMetaData(tableName);
            String sql = this.buildInsertSql(tableMetaData);

            ITable table = dataSet.getTable(tableName);
            List<Map<String, Object>> valuesList = table.getValuesList();

            Column[] columns = tableMetaData.getColumns();
            int cl = columns.length;
            this.jdbcTemplate.batchUpdate(sql, valuesList, valuesList.size(), (PreparedStatement ps, Map<String, Object> argument) -> {
                for (int i = 0; i < cl; i++) {
                    Column column = columns[i];
                    String columnName = column.getColumnName();
                    Object value = table.getValue(i, columnName);
                    DataType dataType = column.getDataType();
                    int parameterIndex = i + 1;
                    if (dataType == DataType.STRING) {
                        ps.setString(parameterIndex, (String) value);
                    } else if (dataType == DataType.BOOLEAN) {
                        ps.setBoolean(parameterIndex, (Boolean) value);
                    } else if (dataType == DataType.INTEGER) {
                        ps.setInt(parameterIndex, (Integer) value);
                    } else if (dataType == DataType.DECIMAL) {
                        ps.setBigDecimal(parameterIndex, (BigDecimal) value);
                    } else if (dataType == DataType.TIMESTAMP) {
                        ps.setTimestamp(parameterIndex, (Timestamp) value);
                    } else if (dataType == DataType.DATE) {
                        ps.setDate(parameterIndex, (Date) value);
                    } else if (dataType == DataType.TIME) {
                        ps.setTime(parameterIndex, (Time) value);
                    } else {
                        ps.setString(parameterIndex, (String) value);
                    }
                }
            });
        }
    }

    private String buildInsertSql(ITableMetaData tableMetaData) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into ").append(tableMetaData.getTableName()).append("values (");
        Column[] columns = tableMetaData.getColumns();
        for (int i = 0, l = columns.length; i < l; i++) {
            Column column = columns[i];
            if (i > 0) {
                sqlBuilder.append(", ");
            }
            sqlBuilder.append("#{" + column.getColumnName() + "}");
        }
        sqlBuilder.append(")");
        return sqlBuilder.toString();
    }

    @Override
    public void tearDown(IDataSet dataSet) {
        String[] tableNames = dataSet.getTableNames();
        for (String tableName : tableNames) {
            ITableMetaData tableMetaData = dataSet.getTableMetaData(tableName);
            String sql = this.buildDeleteSql(tableMetaData);
            this.jdbcTemplate.batchUpdate(sql);
        }
    }

    private String buildDeleteSql(ITableMetaData tableMetaData) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("delete from ").append(tableMetaData.getTableName());
        return sqlBuilder.toString();
    }
}
