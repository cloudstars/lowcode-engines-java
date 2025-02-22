package net.cf.object.engine.oql.testcase.update.detail;

/**
 * 批量更新出差记录本表、行程子表的案例
 *
 * @author clouds
 */
public interface UpdateTravelDetailBatchTest {

    String OQL_FILE_PATH = "oql/update/detail/UpdateTravelAndTripBatch.json";

    String OQL_UPDATE_TRAVEL_AND_TRIP_BY_ID_VARS = "UpdateTravelAndTripByIdVarsBatch";

    void testUpdateTravelAndTripByIdVarsBatch();
}