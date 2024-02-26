package net.cf.commons.test.dataset;

import java.util.List;
import java.util.Map;

public interface ITable {

    ITableMetaData getTableMetaData();

    int getRowCount();

    List<Map<String, Object>> getValuesList();

    Object getValue(int rowIndex, String columnName) throws DataSetException;
}
