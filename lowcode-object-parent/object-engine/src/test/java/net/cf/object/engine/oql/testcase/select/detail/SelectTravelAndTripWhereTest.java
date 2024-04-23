package net.cf.object.engine.oql.testcase.select.detail;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询出差记录本表、行程子表的子表作为查询条件案例
 *
 * @author clouds
 */
public interface SelectTravelAndTripWhereTest {

    String OQL_FILE_PATH = "oql/select/detail/SelectTravelAndTripWhere.json";

    String OQL_SELECT_TRAVEL_AND_TRI_BY_ID_AND_TRIPS_FROM_ADDR = "SelectTravelAndTripByIdAndTripsFromAddr";

    void testSelectTravelAndTripByIdAndTripsFromAddr();

    default Map<String, Object> getByIdAndTripsParamMap() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", "534743DSS-FEL2232-323KLFJFDS-323FDSD");
        paramMap.put("trips.fromAddr", "杭州");
        return paramMap;
    }

}