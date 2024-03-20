package net.cf.object.engine.oql.testcase.select;

/**
 * 查询出差记录本表，带*的字段的测试接口
 *
 * @author clouds
 */
public interface SelectTravelSelfStarTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSelfStar.json";

    String OQL_SELECT_STAR_TRAVEL = "SelectStarTravel";
    String OQL_SELECT_FIELD_STAR_TRAVEL = "SelectFieldStarTravel";
    String OQL_SELECT_MIXED_STAR_TRAVEL = "SelectMixedStarTravel";

    void testSelectStarTravel();

    void testSelectFieldStarTravel();

    void testSelectMixedStarTravel();

}