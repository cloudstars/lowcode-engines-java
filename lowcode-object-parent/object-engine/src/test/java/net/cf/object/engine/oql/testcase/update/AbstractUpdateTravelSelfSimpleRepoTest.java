package net.cf.object.engine.oql.testcase.update;

import net.cf.commons.test.db.dataset.IDataSet;
import net.cf.commons.test.db.dataset.JsonDataSetLoader;
import net.cf.commons.test.db.dataset.MySqlDataSetOperator;
import net.cf.object.engine.OqlEngine;
import net.cf.object.engine.object.ObjectTestResolver;
import net.cf.object.engine.object.ObjectTestUtils;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.testcase.Travel;
import net.cf.object.engine.oql.util.OqlUtils;
import org.junit.After;
import org.junit.Before;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractUpdateTravelSelfSimpleRepoTest extends AbstractOqlRepoTest implements UpdateTravelSelfSimpleTest {

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

    protected AbstractUpdateTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    public void testUpdateTravelById() {
        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_BY_ID);
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(object, oqlInfo.oql);
            ObjectTestUtils.resolveObject(oqlStmt.getObjectSource());
            this.engine.modify(oqlStmt);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            ObjectTestUtils.resolveObject(selectOqlStmt.getSelect().getFrom());
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 1);
            assert ("测试申请单1（变更）".equals(dataList.get(0).get("applyName")));
        }
    }

    @Override
    public void testUpdateTravelByIdVars() {
        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_BY_ID);
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(object, oqlInfo.oql);
            ObjectTestUtils.resolveObject(oqlStmt.getObjectSource());
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("applyName", "测试申请单1（变更）");
            dataMap.put("applyId", Travel.RECORD_ID1);
            this.engine.modify(oqlStmt, dataMap);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            XObject object = ObjectTestResolver.resolveObject(Travel.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            ObjectTestUtils.resolveObject(selectOqlStmt.getSelect().getFrom());
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 1);
            assert ("测试申请单1（变更）".equals(dataList.get(0).get("applyName")));
        }
    }
}
