package net.cf.object.engine.oql.testcase.update;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;

/**
 * 更新出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractUpdateTravelSelfPropertiesRepoTest extends AbstractOqlRepoTest implements UpdateTravelSelfPropertiesTest {

    @Resource
    private OqlEngine engine;

    @Resource
    private MySqlDataSetOperator dataSetOperator;

    private IDataSet dataSet;

    @Before
    public void setup() {
        this.dataSet = JsonDataSetLoader.loadFromClassPath(new String[]{"dataset/Travel.json"});
        this.dataSetOperator.setUp(dataSet);
    }

    @After
    public void tearDown() {
        this.dataSetOperator.tearDown(this.dataSet);
    }

    protected AbstractUpdateTravelSelfPropertiesRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testUpdateTravelCreatorById() {

    }

    @Override
    public void testUpdateTravelCreatorByIdVars() {

    }

    @Override
    public void testUpdateTravelExpandCreatorById() {

    }

    @Override
    public void testUpdateTravelExpandCreatorByIdVars() {

    }

    @Override
    public void testUpdateTravelSingleCreatorById() {

    }

    @Override
    public void testUpdateTravelSingleCreatorByIdVars() {

    }
}
