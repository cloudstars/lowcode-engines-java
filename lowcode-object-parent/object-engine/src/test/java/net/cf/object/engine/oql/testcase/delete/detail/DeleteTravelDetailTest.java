package net.cf.object.engine.oql.testcase.delete.detail;

/**
 * 删除出差记录本表的测试接口
 *
 * @author clouds
 */
public interface DeleteTravelDetailTest {

    String OQL_FILE_PATH = "oql/delete/DeleteTravelDetail.json";

    String OQL_DELETE_TRAVEL_BY_ID = "DeleteTravelById";

    String OQL_DELETE_TRAVEL_BY_ID_VARS = "DeleteTravelByIdVars";

    /**
     * 测试根据记录ID删除出差本表记录
     *
     */
    void testDeleteTravelById();

    /**
     * 测试根据记录ID删除出差本表记录（带变量）
     *
     */
    void testDeleteTravelByIdVars();

}