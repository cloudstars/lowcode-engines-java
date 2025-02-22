package net.cf.object.engine.oql.testcase.select.lookup;

/**
 * 查询报销本表关联查询出差申请单，出差申请作为where条件
 *
 * @author clouds
 */
public interface SelectExpenseLookupTravelOneRefWhereTest {

    String OQL_FILE_PATH = "oql/select/lookup/SelectExpenseLookupTravelOneRefWhere.json";

    String OQL_SELECT_TRAVEL_LOOKUP_TRAVEL_BY_TRAVEL_NAME = "SelectTravelLookupTravelByTravelName";

    void testSelectTravelLookupTravelByTravelName();

}