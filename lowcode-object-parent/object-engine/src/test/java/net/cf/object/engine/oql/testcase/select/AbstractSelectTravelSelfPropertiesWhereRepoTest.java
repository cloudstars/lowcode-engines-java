package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.object.ObjectTestUtils;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfPropertiesWhereRepoTest extends AbstractOqlRepoTest implements SelectTravelSelfPropertiesWhereTest {

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

    protected AbstractSelectTravelSelfPropertiesWhereRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testSelectTravelListByCreatorName() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_BY_CREATOR_NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
    }
}
