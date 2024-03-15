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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfSimpleWhereRepoTest
        extends AbstractOqlRepoTest
        implements SelectTravelSelfSimpleWhereTest {

    private static final String RECORD_ID1 = "434743DSS-FEL3232-323KLFJFDS-323FDSD";
    private static final String RECORD_ID2 = "534743DSS-FEL2232-323KLFJFDS-323FDSD";

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

    protected AbstractSelectTravelSelfSimpleWhereRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testSelectTravelOne() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        Map<String, Object> data = this.engine.queryOne(oqlStmt);
        assert (data != null && "434743DSS-FEL3232-323KLFJFDS-323FDSD".equals(data.get("applyId")));
    }

    @Override
    public void testSelectTravelOneVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_ONE_VARS);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", RECORD_ID1);
        Map<String, Object> data = this.engine.queryOne(oqlStmt, paramMap);
        assert (data != null && "434743DSS-FEL3232-323KLFJFDS-323FDSD".equals(data.get("applyId")));
    }

    @Override
    public void testSelectTravelList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_LIST_VARS);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", RECORD_ID1);
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 1);
    }

    @Override
    public void testSelectTravelInList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt);
        assert (dataList != null && dataList.size() == 2);
        for (Map<String, Object> data : dataList) {
            assert (data.containsKey("applyId") && data.containsKey("applyName"));
        }
    }

    @Override
    public void testSelectTravelInListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_IN_LIST_VARS);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(oqlInfo.oql);
        ObjectTestUtils.resolveObject(oqlStmt.getSelect().getFrom());
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyIds", Arrays.asList(RECORD_ID1, RECORD_ID2));
        List<Map<String, Object>> dataList = this.engine.queryList(oqlStmt, paramMap);
        assert (dataList != null && dataList.size() == 2);
    }
}
