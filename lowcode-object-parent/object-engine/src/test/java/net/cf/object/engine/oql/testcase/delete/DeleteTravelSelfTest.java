package net.cf.object.engine.oql.testcase.delete;

/**
 * 删除出差记录本表的测试接口
 *
 * @author clouds
 */
public interface DeleteTravelSelfTest {

    String OQL_FILE_PATH = "oql/delete/DeleteTravelSelf.json";

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