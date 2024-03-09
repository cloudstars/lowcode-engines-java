package net.cf.form.repository.testcases.statement;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.form.repository.ObjectRepository;
import org.junit.After;
import org.junit.Before;

/**
 * 抽象的测试类，所有的测试类继承它
 *
 * @author clouds
 */
public abstract class AbstractTestCase {

    /**
     * 模型存储接口
     */
    protected final ObjectRepository repository;

    /**
     * 数据集操作器
     */
    protected final IDataSetOperator dataSetOperator;

    protected AbstractTestCase(ObjectRepository repository) {
        this.repository = repository;
        this.dataSetOperator = null;
    }

    public AbstractTestCase(ObjectRepository repository, IDataSetOperator dataSetOperator) {
        this.repository = repository;
        this.dataSetOperator = dataSetOperator;
    }

    /**
     * 获取初始化数据集
     *
     * @return
     */
    protected IDataSet getDataSet() {
        return null;
    }

    @Before
    public void setUp() {
        if (this.dataSetOperator != null) {
            IDataSet dataSet = this.getDataSet();
            if (dataSet != null) {
                this.dataSetOperator.setUp(dataSet);
            }
        }
    }

    @After
    public void tearDown() {
        if (this.dataSetOperator != null) {
            IDataSet dataSet = this.getDataSet();
            if (dataSet != null) {
                this.dataSetOperator.tearDown(dataSet);
            }
        }
    }
}
