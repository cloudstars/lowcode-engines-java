package net.cf.form.repository.testcases.statement;

import net.cf.form.repository.ObjectRepository;
import net.cf.commons.test.dataset.IDataSetOperator;
import net.cf.commons.test.dataset.IDataSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;

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

    @BeforeClass
    protected void setUp() {
        if (this.dataSetOperator != null) {
            IDataSet dataSet = this.getDataSet();
            if (dataSet != null) {
                this.dataSetOperator.setUp(dataSet);
            }
        }
    }

    @AfterClass
    protected void tearDown() {
        if (this.dataSetOperator != null) {
            IDataSet dataSet = this.getDataSet();
            if (dataSet != null) {
                this.dataSetOperator.tearDown(dataSet);
            }
        }
    }
}
