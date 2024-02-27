package net.cf.commons.test.dataset;

public interface ITableMetaData {
    String getTableName();

    Column[] getColumns() throws DataSetException;

    int getColumnIndex(String columnName) throws DataSetException;
}
