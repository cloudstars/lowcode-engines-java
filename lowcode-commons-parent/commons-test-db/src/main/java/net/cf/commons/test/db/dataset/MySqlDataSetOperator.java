package net.cf.commons.test.db.dataset;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;

public class MySqlDataSetOperator implements IDataSetOperator {

    private NamedParameterJdbcTemplate jdbcTemplate;

    public MySqlDataSetOperator(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void setUp(IDataSet dataSet) {
        String[] tableNames = dataSet.getTableNames();
        for (String tableName : tableNames) {
            ITableMetaData tableMetaData = dataSet.getTableMetaData(tableName);
            String namedSql = this.buildInsertSql(tableMetaData);

            ITable table = dataSet.getTable(tableName);
            Map<String, ?>[] batchValues = this.convertToBatchValues(table);
            this.jdbcTemplate.batchUpdate(namedSql, batchValues);
        }
    }

    private String buildInsertSql(ITableMetaData tableMetaData) {
        Column[] columns = tableMetaData.getColumns();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("insert into ").append(tableMetaData.getTableName()).append(" (");
        for (int i = 0, l = columns.length; i < l; i++) {
            Column column = columns[i];
            if (i > 0) {
                sqlBuilder.append(", ");
            }
            sqlBuilder.append(column.getColumnName());
        }
        sqlBuilder.append(") values (");
        for (int i = 0, l = columns.length; i < l; i++) {
            Column column = columns[i];
            if (i > 0) {
                sqlBuilder.append(", ");
            }
            sqlBuilder.append(":" + column.getColumnName());
        }
        sqlBuilder.append(")");
        return sqlBuilder.toString();
    }

    /**
     * 将输入的数据格式转换为template需要的格式
     *
     * @param table
     */
    private Map<String, ?>[] convertToBatchValues(ITable table) {
        Column[] columns = table.getTableMetaData().getColumns();
        int cl = columns.length;
        int rc = table.getRowCount();
        Map<String, ?>[] batchValues = new Map[rc];
        for (int i = 0; i < rc; i++) {
            Map<String, Object> batchValue = new HashMap<>();
            for (int j = 0; j < cl; j++) {
                Column column = columns[j];
                String columnName = column.getColumnName();
                Object value = table.getValue(i, columnName);
                batchValue.put(columnName, value);
            }
            batchValues[i] = batchValue;
            /*DataType dataType = column.getDataType();
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
            }*/
        }

        return batchValues;
    }

    @Override
    public void tearDown(IDataSet dataSet) {
        String[] tableNames = dataSet.getTableNames();
        for (String tableName : tableNames) {
            ITableMetaData tableMetaData = dataSet.getTableMetaData(tableName);
            String sql = this.buildDeleteSql(tableMetaData);
            this.jdbcTemplate.update(sql, new HashMap<>(0));
        }
    }

    private String buildDeleteSql(ITableMetaData tableMetaData) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("delete from ").append(tableMetaData.getTableName());
        return sqlBuilder.toString();
    }
}
