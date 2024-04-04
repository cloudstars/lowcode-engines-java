package net.cf.object.engine.oql.testcase.select;

/**
 * 查询报销本表，测试contains查询条件
 *
 * @author clouds
 */
public interface SelectExpenseLookupTravelOneRefTest {

    String OQL_FILE_PATH = "oql/select/SelectExpenseLookupTravelOneRef.json";

    String OQL_SELECT_TRAVEL_LOOKUP_TRAVEL = "SelectTravelLookupTravel";

    void testSelectTravelLookupTravel();

}