package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录本表，测试分页查询的案例
 *
 * @author clouds
 */
public interface SelectTravelSelfCountLimitTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSelfCountLimit.json";

    String OQL_SELECT_COUNT_ONE_TRAVEL = "SelectCountOneTravel";
    String OQL_SELECT_COUNT_STAR_TRAVEL = "SelectCountStarTravel";
    String OQL_SELECT_COUNT_FIELD_TRAVEL = "SelectCountFieldTravel";
    String OQL_SELECT_TRAVEL_WITH_LIMIT = "SelectTravelWithLimit";
    String OQL_SELECT_TRAVEL_WITH_LIMIT_OFFSET = "SelectTravelWithLimitOffset";

    void testSelectCountOneTravel();

    void testSelectCountStarTravel();

    void testSelectCountFieldTravel();

    void testSelectTravelWithLimit();

    void testSelectTravelWithLimitOffset();

}