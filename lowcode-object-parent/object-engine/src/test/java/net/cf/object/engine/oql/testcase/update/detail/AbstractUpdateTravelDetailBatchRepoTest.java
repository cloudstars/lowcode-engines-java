package net.cf.object.engine.oql.testcase.update.detail;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlStatementUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractUpdateTravelDetailBatchRepoTest
        extends AbstractOqlRepoTest
        implements UpdateTravelDetailBatchTest {

    protected AbstractUpdateTravelDetailBatchRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testUpdateTravelAndTripByIdVarsBatch() {
        String detailOqlSelect = "select * from TravelTrip where travelApplyId in ('" + TravelObject.RECORD1 + "', '" + TravelObject.RECORD2 + "')";
        OqlSelectStatement detailOqlSelectStmt = OqlStatementUtils.parseSingleSelectStatement(this.resolver, detailOqlSelect);
        List<Map<String, Object>> subRecords = this.engine.queryList(detailOqlSelectStmt);
        assert (subRecords.size() == 3);
        Object tripId0 = subRecords.get(0).get("tripId");
        Object tripId1 = subRecords.get(1).get("tripId");
        Object tripId2 = subRecords.get(2).get("tripId");

        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_VARS);
            OqlUpdateStatement oqlStmt = OqlStatementUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            ((Map) (((List) oqlInfo.paramMaps.get(0).get("trips")).get(0))).put("tripId", tripId0);
            ((Map) (((List) oqlInfo.paramMaps.get(1).get("trips")).get(0))).put("tripId", tripId2);
            int[] effectedRows = this.engine.modifyList(oqlStmt, oqlInfo.paramMaps);
            assert (effectedRows.length == 2);
        }

        {
            // 重新查出来作断言
            subRecords = this.engine.queryList(detailOqlSelectStmt);
            assert (subRecords != null && subRecords.size() == 4); // 新加2条、更新2条、删1条
            boolean tripId0Exist = false; // 第1条子表字段是否存在、
            boolean tripId1Exist = false; // 第2条子表记录是否存在
            boolean tripId2Exist = false; // 第3条子表记录是否存在
            for (Map<String, Object> subRecord : subRecords) {
                Object tripId = subRecord.get("tripId");
                if (tripId.equals(tripId0)) {
                    tripId0Exist = true;
                } else if (tripId.equals(tripId1)) {
                    tripId1Exist = true;
                } else if (tripId.equals(tripId2)) {
                    tripId2Exist = true;
                }
            }
            assert (tripId0Exist && !tripId1Exist && tripId2Exist);
        }
    }
}
