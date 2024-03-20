package net.cf.object.engine.oql.testcase.update;

import net.cf.object.engine.object.TestObjectResolver;
import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.object.XObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.oql.util.OqlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 更新出差表本表的测试集
 *
 * @author clouds
 */
public abstract class AbstractUpdateTravelSelfSimpleRepoTest extends AbstractOqlRepoTest implements UpdateTravelSelfSimpleTest {

    protected AbstractUpdateTravelSelfSimpleRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json"};
    }

    @Override
    public void testUpdateTravelById() {
        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_BY_ID);
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(object, oqlInfo.oql);
            this.engine.modify(oqlStmt);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
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
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(object, oqlInfo.oql);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("applyName", "测试申请单1（变更）");
            dataMap.put("applyId", TravelObject.RECORD1);
            this.engine.modify(oqlStmt, dataMap);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            XObject object = TestObjectResolver.resolveObject(TravelObject.NAME);
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(object, selectOql);
            List<Map<String, Object>> dataList = this.engine.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 1);
            assert ("测试申请单1（变更）".equals(dataList.get(0).get("applyName")));
        }
    }
}
