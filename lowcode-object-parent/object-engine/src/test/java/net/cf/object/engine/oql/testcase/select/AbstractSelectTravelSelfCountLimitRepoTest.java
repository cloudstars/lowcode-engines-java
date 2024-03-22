package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfCountLimitRepoTest
        extends AbstractOqlRepoTest
        implements SelectTravelSelfCountLimitTest {

    @Resource
    private IDataSetOperator dataSetOperator;

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

    protected AbstractSelectTravelSelfCountLimitRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testSelectCountOneTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_COUNT_ONE_TRAVEL);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(oqlStmt);
        Object count = data.get("COUNT(1)");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectCountStarTravel() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_COUNT_STAR_TRAVEL);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(oqlStmt);
        Object count = data.get("COUNT(*)");
        assert (count != null && count instanceof Number && ((Number) count).intValue() == 2);
    }

    @Override
    public void testSelectTravelWithLimit() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_WITH_LIMIT);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 2);
    }

    @Override
    public void testSelectTravelWithLimitOffset() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_WITH_LIMIT_OFFSET);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 2);
    }

}
