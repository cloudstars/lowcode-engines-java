package net.cf.commons.test.db.dataset;

public interface ITableMetaData {
    String getTableName();

    Column[] getColumns() throws DataSetException;

    int getColumnIndex(String columnName) throws DataSetException;
}
