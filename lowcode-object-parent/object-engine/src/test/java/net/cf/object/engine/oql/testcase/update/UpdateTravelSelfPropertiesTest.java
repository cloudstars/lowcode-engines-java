package net.cf.object.engine.oql.testcase.update;

/**
 * 更新出差记录本表（带子属性）的测试接口
 *
 * @author clouds
 */
public interface UpdateTravelSelfPropertiesTest {

    String OQL_FILE_PATH = "oql/update/UpdateTravelSelfProperties.json";

    String OQL_UPDATE_TRAVEL_CREATOR_BY_ID = "UpdateTravelCreatorById";

    String OQL_UPDATE_TRAVEL_CREATOR_BY_ID_VARS = "UpdateTravelCreatorByIdVars";

    String OQL_UPDATE_TRAVEL_EXPAND_CREATOR_BY_ID = "UpdateTravelExpandCreatorById";

    String OQL_UPDATE_TRAVEL_EXPAND_CREATOR_BY_ID_VARS = "UpdateTravelExpandCreatorByIdVars";

    String OQL_UPDATE_TRAVEL_SINGLE_CREATOR_BY_ID = "UpdateTravelSingleCreatorById";

    String OQL_UPDATE_TRAVEL_SINGLE_CREATOR_BY_ID_VARS = "UpdateTravelSingleCreatorByIdVars";

    void testUpdateTravelCreatorById();

    void testUpdateTravelCreatorByIdVars();

    void testUpdateTravelExpandCreatorById();

    void testUpdateTravelExpandCreatorByIdVars();

    void testUpdateTravelSingleCreatorById();

    void testUpdateTravelSingleCreatorByIdVars();

}