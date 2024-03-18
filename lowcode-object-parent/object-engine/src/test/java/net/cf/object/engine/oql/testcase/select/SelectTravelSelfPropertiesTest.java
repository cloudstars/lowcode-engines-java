package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录本表，带有子属性条件的测试接口
 *
 * @author clouds
 */
public interface SelectTravelSelfPropertiesTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSelfProperties.json";

    String OQL_SELECT_TRAVEL_CREATOR_LIST_BY_ID = "SelectTravelCreatorListById";
    String OQL_SELECT_TRAVEL_EXPAND_CREATOR_LIST_BY_ID = "SelectTravelExpandCreatorListById";
    String OQL_SELECT_TRAVEL_SINGLE_CREATOR_LIST_BY_ID = "SelectTravelSingleCreatorListById";
    String OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR = "SelectTravelListBySingleCreator";
    String OQL_SELECT_TRAVEL_LIST_BY_SINGLE_CREATOR_VARS = "SelectTravelListBySingleCreatorVars";

    void testSelectTravelCreatorListById();

    void testSelectTravelExpandCreatorListById();

    void testSelectTravelSingleCreatorListById();

    void testSelectTravelListBySingleCreator();

    void testSelectTravelListBySingleCreatorVars();
}