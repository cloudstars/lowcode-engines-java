package net.cf.object.engine.oql.testcase;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.object.engine.OqlEngineNew;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;

public abstract class AbstractOqlRepoTest extends AbstractOqlTest {

    @Resource
    protected OqlEngineNew engineNew;

    @Resource
    protected IDataSetOperator dataSetOperator;

    private IDataSet dataSet;

    public AbstractOqlRepoTest(String oqlFilePath) {
        super(oqlFilePath);
    }

    /**
     * 获取数据集文件
     *
     * @return
     */
    protected String[] getDataSetFiles() {
        return new String[]{};
    }

    @Before
    public void setup() {
        String[] dataSetFiles = this.getDataSetFiles();
        if (dataSetFiles != null) {
            this.dataSet = JsonDataSetLoader.loadFromClassPath(dataSetFiles);
        }
        if (this.dataSet != null) {
            this.dataSetOperator.setUp(dataSet);
        }
    }

    @After
    public void tearDown() {
        if (this.dataSet != null) {
            this.dataSetOperator.tearDown(this.dataSet);
        }
    }
}
