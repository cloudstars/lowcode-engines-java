package net.cf.object.engine.oql.testcase.insert.detail;

/**
 * 插入出差记录，并且插入行程子表
 *
 * @author clouds
 */
public interface InsertTravelDetailTest {

    String OQL_FILE_PATH = "oql/insert/InsertTravelAndTrip.json";

    String OQL_INSERT_TRAVEL_AND_TRIPS = "InsertTravelAndTrip";

    String OQL_INSERT_MULTI_TRAVEL_AND_TRIPS = "InsertMultiTravelAndTrip";

    String OQL_BATCH_INSERT_TRAVEL_AND_TRIPS_VARS = "BatchInsertTravelAndTripVars";

    String OQL_BATCH_INSERT_MULTI_TRAVEL_AND_TRIPS_VARS = "BatchInsertMultiTravelAndTripVars";

    void testInsertTravelAndTrip();

    void testInsertMultiTravelAndTrip();

    void testBatchInsertTravelAndTripVars();

    void testBatchInsertMultiTravelAndTripVars();

}


