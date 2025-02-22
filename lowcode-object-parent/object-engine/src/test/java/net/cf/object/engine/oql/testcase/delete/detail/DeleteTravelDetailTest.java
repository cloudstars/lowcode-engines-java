package net.cf.object.engine.oql.testcase.delete.detail;

/**
 * 删除出差记录本表的测试接口
 *
 * @author clouds
 */
public interface DeleteTravelDetailTest {

    String OQL_FILE_PATH = "oql/delete/detail/DeleteTravelDetail.json";

    String OQL_DELETE_TRAVEL_AND_TRIP_BY_ID = "DeleteTravelAndTripById";

    String OQL_DELETE_TRAVEL_AND_TRIP_BY_ID_VARS = "DeleteTravelAndTripByIdVars";

    String OQL_DELETE_TRAVEL_AND_TRIP_IN_IDS = "DeleteTravelAndTripInIds";

    String OQL_DELETE_TRAVEL_AND_TRIP_IN_IDS_VARS = "DeleteTravelAndTripInIdsVars";

    /**
     * 测试根据记录ID删除出差本表记录和出差行程记录
     */
    void testDeleteTravelAndTripById();

    /**
     * 测试根据记录ID删除出差本表记录和出差行程记录（带变量）
     */
    void testDeleteTravelAndTripByIdVars();

    /**
     * 测试根据记录ID集合删除出差本表记录和出差行程记录
     */
    void testDeleteTravelAndTripInIds();

    /**
     * 测试根据记录ID集合删除出差本表记录和出差行程记录（带变量）
     */
    void testDeleteTravelAndTripInIdsVars();

}