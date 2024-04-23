package net.cf.object.engine.oql.testcase.select.lookup;

/**
 * 查询报销本表关联查询出差申请单
 *
 * @author clouds
 */
public interface SelectExpenseLookupTravelOneRefTest {

    String OQL_FILE_PATH = "oql/select/lookup/SelectExpenseLookupTravelOneRef.json";

    String OQL_SELECT_TRAVEL_LOOKUP_TRAVEL = "SelectTravelLookupTravel";

    void testSelectTravelLookupTravel();

}