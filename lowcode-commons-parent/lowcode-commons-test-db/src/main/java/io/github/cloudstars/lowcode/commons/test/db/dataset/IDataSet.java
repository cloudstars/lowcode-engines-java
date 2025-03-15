package io.github.cloudstars.lowcode.commons.test.db.dataset;

/**
 * 数据集
 *
 * @author clouds
 */
public interface IDataSet {

    /**
     * 获取全部的表名称
     *
     * @return
     * @throws DataSetException
     */
    String[] getTableNames() throws DataSetException;

    ITableMetaData getTableMetaData(String tableName) throws DataSetException;

    ITable getTable(String tableName) throws DataSetException;

    boolean isCaseSensitiveTableNames();
}
