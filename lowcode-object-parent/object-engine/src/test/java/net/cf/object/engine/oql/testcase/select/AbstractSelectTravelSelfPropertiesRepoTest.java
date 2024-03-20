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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfPropertiesRepoTest extends AbstractOqlRepoTest implements SelectTravelSelfPropertiesTest {

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

    protected AbstractSelectTravelSelfPropertiesRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testSelectTravelCreatorListById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_CREATOR_LIST_BY_ID);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
        assert (dataList.get(0).containsKey("creator"));
        Object creator = dataList.get(0).get("creator");
        assert (creator != null && creator instanceof Map);
        assert ("张三".equals(((Map<?, ?>) creator).get("name")));
        assert ("zhangsan".equals(((Map<?, ?>) creator).get("key")));
    }

    @Override
    public void testSelectTravelExpandCreatorListById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_EXPAND_CREATOR_LIST_BY_ID);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
        assert (dataList.get(0).containsKey("creator"));
        Object creator = dataList.get(0).get("creator");
        assert (creator != null && creator instanceof Map);
        assert ("张三".equals(((Map<?, ?>) creator).get("name")));
        assert ("zhangsan".equals(((Map<?, ?>) creator).get("key")));
    }

    @Override
    public void testSelectTravelSingleCreatorListById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_SINGLE_CREATOR_LIST_BY_ID);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
        assert (dataList.get(0).containsKey("creator.name") && dataList.get(0).containsKey("creator.key") );
        assert ("张三".equals(((Map<?, ?>) dataList.get(0)).get("creator.name")));
        assert ("zhangsan".equals(((Map<?, ?>) dataList.get(0)).get("creator.key")));
    }

    @Override
    public void testSelectTravelListBySingleCreator() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 1);
    }

    @Override
    public void testSelectTravelListBySingleCreatorVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR_VARS);
        XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(object, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("creator.name", "张三");
        paramMap.put("creator.key", "zhangsan");
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
    }
}
