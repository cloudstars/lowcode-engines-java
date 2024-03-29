package net.cf.object.engine.oql.testcase.select.detail;

/**
 * 查询出差记录本表、行程子表的案例
 *
 * @author clouds
 */
public interface SelectTravelSelfDetailTest {

    String OQL_FILE_PATH = "oql/select/SelectTravelSelfDetail.json";

    String OQL_SELECT_TRAVEL_AND_TRIP = "SelectTravelAndTripById";

    void testSelectTravelAndTripById();

}