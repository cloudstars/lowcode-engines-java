package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录本表，测试分页查询的案例
 *
 * @author clouds
 */
public interface SelectTravelSelfCountLimitTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSelfCountLimit.json";

    String OQL_SELECT_COUNT_TRAVEL = "SelectCountTravel";
    String OQL_SELECT_TRAVEL_WITH_LIMIT = "SelectTravelWithLimit";
    String OQL_SELECT_TRAVEL_WITH_LIMIT_OFFSET = "SelectTravelWithLimitOffset";

    void testSelectCountTravel();

    void testSelectTravelWithLimit();

    void testSelectTravelWithLimitOffset();

}