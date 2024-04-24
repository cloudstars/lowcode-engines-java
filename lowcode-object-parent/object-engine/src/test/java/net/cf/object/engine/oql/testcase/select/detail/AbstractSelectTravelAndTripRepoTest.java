package net.cf.object.engine.oql.testcase.select.detail;

import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelAndTripRepoTest extends AbstractOqlRepoTest implements SelectTravelAndTripWhereTest {

    protected AbstractSelectTravelAndTripRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testSelectTravelAndTripByIdAndTripsFromAddr() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRI_BY_ID_AND_TRIPS_FROM_ADDR);
        OqlSelectStatement stmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> paramMap = this.getByIdAndTripsParamMap();
        List<Map<String, Object>> resultMapList = this.engineNew.queryList(stmt, paramMap);
        assert (resultMapList.size() == 1);
        Object trips = resultMapList.get(0).get("trips");
        assert (trips instanceof List && ((List<?>) trips).size() == 1);
        assert (((List<?>) trips).get(0) instanceof String);
    }

}
