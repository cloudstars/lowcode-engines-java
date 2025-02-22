package net.cf.object.engine.oql.testcase.update.detail;

/**
 * 更新出差记录本表、行程子表的案例
 *
 * @author clouds
 */
public interface UpdateTravelDetailTest {

    String OQL_FILE_PATH = "oql/update/detail/UpdateTravelAndTrip.json";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID = "UpdateTravelAndTripById";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_VARS = "UpdateTravelAndTripByIdVars";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_NULL = "UpdateTravelAndTripByIdWithNull";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_NULL_VARS = "UpdateTravelAndTripByIdWithNullVars";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_EMPTY = "UpdateTravelAndTripByIdWithEmpty";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_WITH_EMPTY_VARS = "UpdateTravelAndTripByIdWithEmptyVars";

    void testUpdateTravelAndTripById();

    void testUpdateTravelAndTripByIdVars();

    void testUpdateTravelAndTripByIdWithNull();

    void testUpdateTravelAndTripByIdWithNullVars();

    void testUpdateTravelAndTripByIdWithEmpty();

    void testUpdateTravelAndTripByIdWithEmptyVars();

}