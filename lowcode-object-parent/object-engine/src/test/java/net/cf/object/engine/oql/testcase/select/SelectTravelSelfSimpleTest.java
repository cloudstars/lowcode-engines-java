package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录本表，带有简单条件的测试接口
 *
 * @author clouds
 */
public interface SelectTravelSelfSimpleTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSelfSimple.json";

    String OQL_SELECT_TRAVEL_ONE = "SelectTravelOne";

    String OQL_SELECT_TRAVEL_ONE_VARS = "SelectTravelOneVars";

    String OQL_SELECT_TRAVEL_LIST = "SelectTravelList";

    String OQL_SELECT_TRAVEL_LIST_VARS = "SelectTravelListVars";

    String OQL_SELECT_TRAVEL_IN_LIST = "SelectTravelInList";

    String OQL_SELECT_TRAVEL_IN_LIST_VARS = "SelectTravelInListVars";

    /**
     * 测试查询出差记录
     *
     */
    void testSelectTravelOne();

    /**
     * 测试查询出差记录（带变量）
     *
     */
    void testSelectTravelOneVars();

    /**
     * 测试查询出差列表
     *
     */
    void testSelectTravelList();

    /**
     * 测试查询出差列表（带变量）
     *
     */
    void testSelectTravelListVars();

    /**
     * 测试查询出差In列表
     *
     */
    void testSelectTravelInList();

    /**
     * 测试查询出差In列表（带变量）
     *
     */
    void testSelectTravelInListVars();

}