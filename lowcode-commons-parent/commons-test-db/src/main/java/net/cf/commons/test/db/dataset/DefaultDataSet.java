package net.cf.commons.test.db.dataset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class DefaultDataSet implements IDataSet {

    private static final Logger logger = LoggerFactory.getLogger(DefaultDataSet.class);

    protected OrderedTableNameMap orderedTableNameMap;

    private boolean caseSensitiveTableNames = false;

    public DefaultDataSet() {
        this.initialize();
    }

    public DefaultDataSet(boolean caseSensitiveTableNames) {
        this.caseSensitiveTableNames = caseSensitiveTableNames;
        this.initialize();
    }

    public void addTable(DefaultTable defaultTable) {
        String tableName = defaultTable.getTableMetaData().getTableName();
        this.orderedTableNameMap.add(tableName, defaultTable);
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

    public static class OrderedTableNameMap {
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
}
