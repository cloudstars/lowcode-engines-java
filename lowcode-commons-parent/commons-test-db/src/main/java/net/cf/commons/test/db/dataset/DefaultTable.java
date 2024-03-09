package net.cf.commons.test.db.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DefaultTable implements ITable {

    private ITableMetaData tableMetaData;

    private final List<Map<String, Object>> valuesList = new ArrayList<>();

    public DefaultTable(ITableMetaData tableMetaData) {
        this.tableMetaData = tableMetaData;
    }

    @Override
    public ITableMetaData getTableMetaData() {
        return this.tableMetaData;
    }

    @Override
    public int getRowCount() {
        return valuesList.size();
    }

    @Override
    public List<Map<String, Object>> getValuesList() {
        return this.valuesList;
    }

    @Override
    public Object getValue(int rowIndex, String columnName) throws DataSetException {
        int rowCount = this.valuesList.size();
        if (rowIndex >= rowCount) {
            throw new DataSetException(rowIndex + "超出表" + columnName + "的最大行数：" + rowCount);
        }

        return this.valuesList.get(rowIndex).get(columnName);
    }

    public void addValues(Map<String, Object> data) {
        this.valuesList.add(data);
    }


    public void addValuesList(List<Map<String, Object>> dataList) {
        this.valuesList.addAll(dataList);
    }
}
