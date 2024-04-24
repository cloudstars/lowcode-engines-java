package net.cf.object.engine.oql.testcase.delete.detail;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlDeleteStatement;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插入兴趣爱好表的测试集
 *
 * @author clouds
 */
public abstract class AbstractDeleteTravelDetailRepoTest extends AbstractOqlRepoTest implements DeleteTravelDetailTest {

    protected AbstractDeleteTravelDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testDeleteTravelAndTripById() {
        {
            // 删除数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_BY_ID);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
            this.engine.remove(oqlStmt);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engineNew.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 0);

            String detailSelectOql = "select tripId from TravelTrip where travelApplyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            OqlSelectStatement detailSelectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, detailSelectOql);
            List<Map<String, Object>> detailDataList = this.engineNew.queryList(detailSelectOqlStmt);
            assert (detailDataList != null && detailDataList.size() == 0);
        }
    }

    @Override
    public void testDeleteTravelAndTripByIdVars() {
        {
            // 删除数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_BY_ID_VARS);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("applyId", TravelObject.RECORD1);
            this.engine.remove(oqlStmt, dataMap);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engineNew.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 0);

            String detailSelectOql = "select tripId from TravelTrip where travelApplyId = '434743DSS-FEL3232-323KLFJFDS-323FDSD'";
            OqlSelectStatement detailSelectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, detailSelectOql);
            List<Map<String, Object>> detailDataList = this.engineNew.queryList(detailSelectOqlStmt);
            assert (detailDataList != null && detailDataList.size() == 0);
        }
    }

    @Override
    public void testDeleteTravelAndTripInIds() {
        {
            // 删除数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_IN_IDS);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
            this.engine.remove(oqlStmt);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId in ('434743DSS-FEL3232-323KLFJFDS-323FDSD','534743DSS-FEL2232-323KLFJFDS-323FDSD')";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engineNew.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 0);

            String detailSelectOql = "select tripId from TravelTrip where travelApplyId in ('434743DSS-FEL3232-323KLFJFDS-323FDSD','534743DSS-FEL2232-323KLFJFDS-323FDSD')";
            OqlSelectStatement detailSelectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, detailSelectOql);
            List<Map<String, Object>> detailDataList = this.engineNew.queryList(detailSelectOqlStmt);
            assert (detailDataList != null && detailDataList.size() == 0);
        }
    }

    @Override
    public void testDeleteTravelAndTripInIdsVars() {
        {
            // 删除数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_DELETE_TRAVEL_AND_TRIP_IN_IDS);
            OqlDeleteStatement oqlStmt = OqlUtils.parseSingleDeleteStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("applyIds", Arrays.asList(TravelObject.RECORD1, TravelObject.RECORD2));
            this.engine.remove(oqlStmt, paramMap);
        }

        {
            // 重新查出来作断言
            String selectOql = "select applyId, applyName from Travel where applyId in ('434743DSS-FEL3232-323KLFJFDS-323FDSD','534743DSS-FEL2232-323KLFJFDS-323FDSD')";
            OqlSelectStatement selectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, selectOql);
            List<Map<String, Object>> dataList = this.engineNew.queryList(selectOqlStmt);
            assert (dataList != null && dataList.size() == 0);

            String detailSelectOql = "select tripId from TravelTrip where travelApplyId in ('434743DSS-FEL3232-323KLFJFDS-323FDSD','534743DSS-FEL2232-323KLFJFDS-323FDSD')";
            OqlSelectStatement detailSelectOqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, detailSelectOql);
            List<Map<String, Object>> detailDataList = this.engineNew.queryList(detailSelectOqlStmt);
            assert (detailDataList != null && detailDataList.size() == 0);
        }
    }
}
