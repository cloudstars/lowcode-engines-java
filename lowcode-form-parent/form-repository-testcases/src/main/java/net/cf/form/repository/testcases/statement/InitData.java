package net.cf.form.repository.testcases.statement;

import java.util.Map;

/**
 * 初始化数据
 *
 * @author clouds
 */
public class InitData {

    private String tableName;

    private Map<String, Object> columnValues;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, Object> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(Map<String, Object> columnValues) {
        this.columnValues = columnValues;
    }
}
