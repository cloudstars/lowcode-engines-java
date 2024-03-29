package net.cf.object.engine.oql.testcase.insert.detail;

/**
 * 插入出差记录，并且插入行程子表
 *
 * @author clouds
 */
public interface InsertTravelSelfDetailTest {

    String OQL_FILE_PATH = "oql/insert/InsertTravelAndTrip.json";

    String OQL_INSERT_TRAVEL_AND_TRIPS_VARS = "InsertTravelAndTripVars";

    void testIInsertTravelAndTripVars();

}


