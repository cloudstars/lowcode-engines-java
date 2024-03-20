package net.cf.object.engine.oql.testcase.select;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.IDataSetOperator;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfSimpleRepoTest
        extends AbstractOqlRepoTest
        implements SelectTravelSelfSimpleTest {

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

    protected AbstractSelectTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testSelectTravelOne() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(oqlStmt);
        assert (data != null && TravelObject.RECORD1.equals(data.get("applyId")));
    }

    @Override
    public void testSelectTravelOneVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE_VARS);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        Map<String, Object> data = this.engine.queryOne(oqlStmt, paramMap);
        assert (data != null && TravelObject.RECORD1.equals(data.get("applyId")));
    }

    @Override
    public void testSelectTravelList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_VARS);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
    }

    @Override
    public void testSelectTravelInList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelInListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST_VARS);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyIds", Arrays.asList(TravelObject.RECORD1, TravelObject.RECORD2));
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 2);
    }
}
