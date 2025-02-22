package net.cf.object.engine.oql.testcase.select.detail;

/**
 * 查询出差记录本表、行程子表的案例
 *
 * @author clouds
 */
public interface SelectTravelDetailTest {

    String OQL_FILE_PATH = "oql/select/detail/SelectTravelSelfDetail.json";

    String OQL_SELECT_TRAVEL_AND_TRIP_IDS_BY_ID = "SelectTravelAndTripIdsById";

    String OQL_SELECT_TRAVEL_AND_TRIP_BY_ID = "SelectTravelAndTripById";

    String OQL_SELECT_TRAVEL_AND_TRIP_BY_ID_VARS = "SelectTravelAndTripByIdVars";

    String OQL_SELECT_TRAVEL_AND_TRIP_LIST = "SelectTravelAndTripList";

    String OQL_SELECT_TRAVEL_AND_TRIP_LIST_VARS = "SelectTravelAndTripListVars";

    void testSelectTravelAndTripIdsById();

    void testSelectTravelAndTripById();

    void testSelectTravelAndTripList();

    void testSelectTravelAndTripByIdVars();

    void testSelectTravelAndTripListVars();

}