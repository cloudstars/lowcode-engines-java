package net.cf.form.repository.testcases.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class OrderedTableNameMap {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedTableNameMap.class);
    private Map<String, ITable> tableMap = new HashMap();
    private List<String> tableNames = new ArrayList();
    private String lastTableNameOverride;
    private boolean caseSensitiveTableNames;

    public OrderedTableNameMap(boolean caseSensitiveTableNames) {
        this.caseSensitiveTableNames = caseSensitiveTableNames;
    }

    public ITable get(String tableName) {
        String correctedCaseTableName = this.getTableName(tableName);
        return this.tableMap.get(correctedCaseTableName);
    }

    public String[] getTableNames() {
        return (String[]) ((String[]) this.tableNames.toArray(new String[0]));
    }

    public boolean containsTable(String tableName) {
        String correctedCaseTableName = this.getTableName(tableName);
        return this.tableMap.containsKey(correctedCaseTableName);
    }

    public boolean isLastTable(String tableName) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("isLastTable(tableName={}) - start", tableName);
        }

        if (this.tableNames.size() == 0) {
            return false;
        } else {
            String lastTable = this.getLastTableName();
            String lastTableCorrectCase = this.getTableName(lastTable);
            String inputTableCorrectCase = this.getTableName(tableName);
            return lastTableCorrectCase.equals(inputTableCorrectCase);
        }
    }

    public String getLastTableName() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("getLastTableName() - start");
        }

        if (this.lastTableNameOverride != null) {
            return this.lastTableNameOverride;
        } else if (this.tableNames.size() > 0) {
            String lastTable = (String) this.tableNames.get(this.tableNames.size() - 1);
            return lastTable;
        } else {
            return null;
        }
    }

    public void setLastTable(String tableName) throws DataSetException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setLastTable(name{}) - start", tableName);
        }

        if (!this.containsTable(tableName)) {
            throw new DataSetException(tableName);
        } else {
            this.lastTableNameOverride = tableName;
        }
    }

    public void add(String tableName, ITable object) throws DataSetException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("add(tableName={}, object={}) - start", tableName, object);
        }

        String tableNameCorrectedCase = this.getTableName(tableName);
        if (this.containsTable(tableNameCorrectedCase)) {
            throw new DataSetException(tableNameCorrectedCase);
        } else {
            this.tableMap.put(tableNameCorrectedCase, object);
            this.tableNames.add(tableName);
            this.lastTableNameOverride = null;
        }
    }

    public Collection orderedValues() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("orderedValues() - start");
        }

        List orderedValues = new ArrayList(this.tableNames.size());
        Iterator iterator = this.tableNames.iterator();

        while (iterator.hasNext()) {
            String tableName = (String) iterator.next();
            Object object = this.get(tableName);
            orderedValues.add(object);
        }

        return orderedValues;
    }

    public void update(final String tableName, ITable object) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("update(tableName={}, object={}) - start", tableName, object);
        }

        if (!this.containsTable(tableName)) {
            throw new IllegalArgumentException("The table name '" + tableName + "' does not exist in the map");
        } else {
            String tTableName = this.getTableName(tableName);
            this.tableMap.put(tTableName, object);
        }
    }

    public String getTableName(String tableName) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("getTableName(tableName={}) - start", tableName);
        }

        String result = tableName;
        if (!this.caseSensitiveTableNames) {
            result = tableName.toUpperCase(Locale.ENGLISH);
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("getTableName(tableName={}) - end - result={}", tableName, result);
        }

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(this.getClass().getName()).append("[");
        sb.append("_tableNames=").append(this.tableNames);
        sb.append(", _tableMap=").append(this.tableMap);
        sb.append(", _caseSensitiveTableNames=").append(this.caseSensitiveTableNames);
        sb.append("]");
        return sb.toString();
    }
}
