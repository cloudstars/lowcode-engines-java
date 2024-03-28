package net.cf.object.engine.oql.testcase.update;

/**
 * 更新出差记录本表（带子属性）的测试接口
 *
 * @author clouds
 */
public interface UpdateTravelSelfPropertiesTest {

    String OQL_FILE_PATH = "oql/update/UpdateTravelSelfProperties.json";

    String OQL_UPDATE_TRAVEL_MODIFIER_BY_ID = "UpdateTravelModifierById";

    String OQL_UPDATE_TRAVEL_MODIFIER_BY_ID_VARS = "UpdateTravelModifierByIdVars";

    String OQL_UPDATE_TRAVEL_EXPAND_MODIFIER_BY_ID = "UpdateTravelExpandModifierById";

    String OQL_UPDATE_TRAVEL_EXPAND_MODIFIER_BY_ID_VARS = "UpdateTravelExpandModifierByIdVars";

    String OQL_UPDATE_TRAVEL_SINGLE_MODIFIER_BY_ID = "UpdateTravelSingleModifierById";

    String OQL_UPDATE_TRAVEL_SINGLE_MODIFIER_BY_ID_VARS = "UpdateTravelSingleModifierByIdVars";

    String OQL_UPDATE_TRAVEL_WITH_ATTACHES_BY_ID_VARS = "UpdateTravelWithAttachesByIdVars";

    void testUpdateTravelModifierById();

    void testUpdateTravelModifierByIdVars();

    void testUpdateTravelExpandModifierById();

    void testUpdateTravelExpandModifierByIdVars();

    void testUpdateTravelSingleModifierById();

    void testUpdateTravelSingleModifierByIdVars();

    void testUpdateTravelWithAttachesByIdVars();

}