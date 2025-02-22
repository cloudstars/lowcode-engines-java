package net.cf.object.engine.oql.testcase.select.detail;

/**
 * 查询出差行程本表的案例
 *
 * @author clouds
 */
public interface SelectTravelTripSelfTest {

    String OQL_FILE_PATH = "oql/select/detail/SelectTravelTripSelf.json";

    String OQL_SELECT_TRAVEL_AND_TRIP_BY_ID = "SelectTravelAndTripById";

    String OQL_SELECT_TRAVEL_AND_TRIP_BY_ID_VARS = "SelectTravelAndTripByIdVars";

    String OQL_SELECT_TRAVEL_AND_TRIP_LIST = "SelectTravelAndTripList";

    String OQL_SELECT_TRAVEL_AND_TRIP_LIST_VARS = "SelectTravelAndTripListVars";

    void testSelectTravelAndTripById();

    void testSelectTravelAndTripList();

    void testSelectTravelAndTripByIdVars();

    void testSelectTravelAndTripListVars();

}