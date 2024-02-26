package net.cf.form.repository.testcases.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultDataSet implements IDataSet {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataSet.class);

    protected OrderedTableNameMap orderedTableNameMap;

    private final List<ITableMetaData> tableMetaDataList = new ArrayList<>();

    private boolean caseSensitiveTableNames = false;

    public DefaultDataSet() {
    }

    public DefaultDataSet(boolean caseSensitiveTableNames) {
        this.caseSensitiveTableNames = caseSensitiveTableNames;
    }

    public void addTableMetaData(ITableMetaData tableMetaData) {
        this.tableMetaDataList.add(tableMetaData);
    }

    public boolean isCaseSensitiveTableNames() {
        return this.caseSensitiveTableNames;
    }

    protected OrderedTableNameMap createTableNameMap() {
        return new OrderedTableNameMap(this.caseSensitiveTableNames);
    }

    protected void initialize() throws DataSetException {
        logger.debug("initialize() - start");
        if (this.orderedTableNameMap != null) {
            logger.debug("The table name map has already been initialized.");
        } else {
            this.orderedTableNameMap = this.createTableNameMap();
        }
    }

    public String[] getTableNames() throws DataSetException {
        logger.debug("getTableNames() - start");
        this.initialize();
        return this.orderedTableNameMap.getTableNames();
    }

    public ITableMetaData getTableMetaData(String tableName) throws DataSetException {
        logger.debug("getTableMetaData(tableName={}) - start", tableName);
        return this.getTable(tableName).getTableMetaData();
    }

    public ITable getTable(String tableName) throws DataSetException {
        logger.debug("getTable(tableName={}) - start", tableName);
        this.initialize();
        ITable found = this.orderedTableNameMap.get(tableName);
        if (found != null) {
            return found;
        } else {
            throw new DataSetException(tableName + "不存在！");
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("AbstractDataSet[");
        sb.append("orderedTableNameMap=").append(this.orderedTableNameMap);
        sb.append("]");
        return sb.toString();
    }
}
