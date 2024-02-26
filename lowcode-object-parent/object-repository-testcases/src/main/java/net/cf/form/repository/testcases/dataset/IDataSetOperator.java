package net.cf.form.repository.testcases.dataset;

/**
 * 数据集操作器
 *
 * @author clouds
 */
public interface IDataSetOperator {

    /**
     * 装载数据集
     *
     * @param dataSet
     */
    void setUp(IDataSet dataSet);

    /**
     * 卸载数据集
     *
     * @param dataSet
     */
    void tearDown(IDataSet dataSet);
}
