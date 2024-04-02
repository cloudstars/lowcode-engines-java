package net.cf.object.engine.oql.testcase.select.detail;

/**
 * 查询出差记录本表、行程子表的案例
 *
 * @author clouds
 */
public interface SelectTravelSelfDetailTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSelfDetail.json";

    String OQL_SELECT_TRAVEL_AND_TRIP_BY_ID = "SelectTravelAndTripById";

    String OQL_SELECT_TRAVEL_AND_TRIP_BY_ID_VARS = "SelectTravelAndTripByIdVars";

    String OQL_SELECT_TRAVEL_AND_TRIP_LIST = "SelectTravelAndTripList";

    String OQL_SELECT_TRAVEL_AND_TRIP_LIST_VARS = "SelectTravelAndTripListVars";

    void testSelectTravelAndTripById();

    void testSelectTravelAndTripList();

    void testSelectTravelAndTripByIdVars();

    void testSelectTravelAndTripListVars();

}