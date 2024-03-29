package net.cf.object.engine.oql.testcase.select.detail;

import net.cf.object.engine.oql.ast.OqlSelectStatement;
import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;
import net.cf.object.engine.util.OqlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractSelectTravelSelfDetailRepoTest
        extends AbstractOqlRepoTest
        implements SelectTravelSelfDetailTest {

    protected AbstractSelectTravelSelfDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testSelectTravelAndTripById() {
        OqlInfo oqlInfo = this.oqlInfos.get(OQL_SELECT_TRAVEL_AND_TRIP);
        OqlSelectStatement oqlStmt = OqlUtils.parseSingleSelectStatement(this.resolver, oqlInfo.oql);
        Map<String, Object> data = this.engine.queryOne(oqlStmt, new HashMap<>());
        assert (data != null && data.containsKey("trips"));

        Object trips = data.get("trips");
        assert (trips instanceof List && ((List<?>) trips).size() == 2);
    }

}
