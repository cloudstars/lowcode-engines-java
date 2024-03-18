package net.cf.object.engine.oql.testcase.update;

/**
 * 更新出差记录本表的测试接口
 *
 * @author clouds
 */
public interface UpdateTravelSelfSimpleTest {

    String OQL_FILE_PATH = "oql/update/UpdateTravelSelfSimple.json";

    String OQL_UPDATE_TRAVEL_BY_ID = "UpdateTravelById";

    String OQL_UPDATE_TRAVEL_BY_ID_VARS = "UpdateTravelByIdVars";

    /**
     * 测试根据记录ID更新出差本表记录
     *
     */
    void testUpdateTravelById();


    /**
     * 测试根据记录ID更新出差本表记录（带变量）
     *
     */
    void testUpdateTravelByIdVars();

}