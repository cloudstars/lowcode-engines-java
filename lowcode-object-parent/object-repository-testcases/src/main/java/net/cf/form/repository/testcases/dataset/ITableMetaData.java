package net.cf.form.repository.testcases.dataset;

public interface ITableMetaData {
    String getTableName();

    Column[] getColumns() throws DataSetException;

    int getColumnIndex(String columnName) throws DataSetException;
}
