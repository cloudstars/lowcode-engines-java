package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录，并且不带where条件的测试接口
 *
 * @author clouds
 */
public interface SelectTravelSelfNoWhereTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelNoWhere.json";

    String OQL_SELECT_TRAVEL_LIST = "SelectTravelList";

    /**
     * 测试查询出差列表
     *
     */
    void testSelectTravelList();
}