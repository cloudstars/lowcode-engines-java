package net.cf.object.engine.oql.testcase.update.detail;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.ast.OqlUpdateStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractUpdateTravelSelfDetailRepoTest
        extends AbstractOqlRepoTest
        implements UpdateTravelSelfDetailTest {

    protected AbstractUpdateTravelSelfDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testUpdateTravelAndTripByIdVars() {
        String detailOqlSelect = "select * from TravelTrip where applyId = '" + TravelObject.RECORD1 + "'";
        OqlSelectStatement detailOqlSelectStmt = OqlUtils.parseSingleSelectStatement(this.resolver, detailOqlSelect);
        List<Map<String, Object>> subRecords = this.engine.queryList(detailOqlSelectStmt);
        assert (subRecords.size() == 2);
        String tripId0 = subRecords.get(0).get("tripId").toString();
        String tripId1 = subRecords.get(1).get("tripId").toString();

        {
            // 更新数据
            OqlInfo oqlInfo = this.oqlInfos.get(OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_VARS);
            OqlUpdateStatement oqlStmt = OqlUtils.parseSingleUpdateStatement(this.resolver, oqlInfo.oql);
            Map<String, Object> paramMap = this.getEditParamMap(TravelObject.RECORD1, tripId0);
            int effectedRows = this.engine.modify(oqlStmt, paramMap);
            assert (effectedRows == 1);
        }

        {
            // 重新查出来作断言
            subRecords = this.engine.queryList(detailOqlSelectStmt);
            assert (subRecords != null && subRecords.size() == 2); // 新加一条、更新一条、删一条还是2
            boolean tripId0Updated = false; // 第1条子表字段是否更新过、
            boolean tripId1Exist = false; // 第2条子表记录是否存在
            for (Map<String, Object> subRecord : subRecords) {
                String tripId = subRecord.get("tripId").toString();
                if (tripId.equals(tripId0)) {
                    assert (subRecord.get("modifier") != null);
                    tripId0Updated = true;
                } else if (tripId.equals(tripId1)) {
                    tripId1Exist = true;
                }
            }
            assert (tripId0Updated && !tripId1Exist);
        }
    }
}
