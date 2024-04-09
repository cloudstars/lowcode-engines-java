package net.cf.object.engine.oql.testcase.select.detail;

import net.cf.object.engine.object.TravelObject;
import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelDetailRepoTest
        extends AbstractOqlRepoTest
        implements SelectTravelDetailTest {

    protected AbstractSelectTravelDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testSelectTravelAndTripIdsById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_IDS_BY_ID);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(oqlStmt, new HashMap<>());
        assert (resultMap != null && resultMap.containsKey("trips"));

        Object trips = resultMap.get("trips");
        assert (trips instanceof List && ((List<?>) trips).size() == 2);
        // 断言trips的内容是String
        assert (((List<?>) trips).get(0) instanceof String);
    }

    @Override
    public void testSelectTravelAndTripById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_BY_ID);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> resultMap = this.engine.queryOne(oqlStmt, new HashMap<>());
        assert (resultMap != null && resultMap.containsKey("trips"));

        Object trips = resultMap.get("trips");
        assert (trips instanceof List && ((List<?>) trips).size() == 2);
    }

    @Override
    public void testSelectTravelAndTripByIdVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_BY_ID_VARS);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", TravelObject.RECORD1);
        Map<String, Object> resultMap = this.engine.queryOne(oqlStmt, paramMap);
        assert (resultMap != null && resultMap.containsKey("trips"));

        Object trips = resultMap.get("trips");
        assert (trips instanceof List && ((List<?>) trips).size() == 2);
    }

    @Override
    public void testSelectTravelAndTripList() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_LIST);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        List<Map<String, Object>> resultList = this.engine.queryList(oqlStmt, new HashMap<>());
        assert (resultList != null && resultList.size() == 2);

        Object trips0 = resultList.get(0).get("trips");
        assert (trips0 instanceof List && ((List<?>) trips0).size() == 2);
        Object trips1 = resultList.get(1).get("trips");
        assert (trips1 instanceof List && ((List<?>) trips1).size() == 1);
    }

    @Override
    public void testSelectTravelAndTripListVars() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP_LIST_VARS);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyIds", Arrays.asList(TravelObject.RECORD1, TravelObject.RECORD2));
        List<Map<String, Object>> resultList = this.engine.queryList(oqlStmt, paramMap);
        assert (resultList != null && resultList.size() == 2);

        Object trips0 = resultList.get(0).get("trips");
        assert (trips0 instanceof List && ((List<?>) trips0).size() == 2);
        Object trips1 = resultList.get(1).get("trips");
        assert (trips1 instanceof List && ((List<?>) trips1).size() == 1);
    }

}
