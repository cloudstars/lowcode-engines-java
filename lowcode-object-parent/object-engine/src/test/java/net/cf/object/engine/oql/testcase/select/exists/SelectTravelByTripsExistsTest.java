package net.cf.object.engine.oql.testcase.select.exists;

/**
 * 根据行程子表查询出差申请表，测试exists子查询
 *
 * @author clouds
 */
public interface SelectTravelByTripsExistsTest {

    String OQL_FILE_PATH = "oql/select/exists/SelectTravelByTripsExists.json";

    String OQL_SELECT_TRAVEL_BY_TRIPS_LIKE = "SelectTravelByTripsLike";

    void testSelectTravelByTripsLike();

}