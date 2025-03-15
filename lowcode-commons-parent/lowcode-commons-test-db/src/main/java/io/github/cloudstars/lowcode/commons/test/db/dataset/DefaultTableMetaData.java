package io.github.cloudstars.lowcode.commons.test.db.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DefaultTableMetaData implements ITableMetaData {

    private static final Logger logger = LoggerFactory.getLogger(DefaultTableMetaData.class);

    private Map<String, Integer> columnsToIndexes;

    private final String tableName;

    private final Column[] columns;

    public DefaultTableMetaData(String tableName, Column[] columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public int getColumnIndex(String columnName) throws DataSetException {
        logger.debug("getColumnIndex(columnName={}) - start", columnName);
        if (this.columnsToIndexes == null) {
            this.columnsToIndexes = this.createColumnIndexesMap(this.getColumns());
        }

        String columnNameUpperCase = columnName.toUpperCase();
        Integer colIndex = this.columnsToIndexes.get(columnNameUpperCase);
        if (colIndex != null) {
            return colIndex;
        } else {
            throw new DataSetException(this.getTableName() + "中不存在名称为" + columnNameUpperCase + "的列");
        }
    }

    private Map<String, Integer> createColumnIndexesMap(Column[] columns) {
        Map<String, Integer> colsToIndexes = new HashMap(columns.length);

        for (int i = 0; i < columns.length; ++i) {
            colsToIndexes.put(columns[i].getColumnName().toUpperCase(), i);
        }

        return colsToIndexes;
    }

    public String toString() {
        return "tableName=" + this.tableName + ", columns=" + Arrays.asList(this.columns);
    }

    public String getTableName() {
        return this.tableName;
    }

    public Column[] getColumns() {
        return this.columns;
    }
}
