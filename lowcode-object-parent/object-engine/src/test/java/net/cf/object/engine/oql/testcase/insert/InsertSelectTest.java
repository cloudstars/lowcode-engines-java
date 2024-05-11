package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入查询 insert select 语句测试
 *
 * @author clouds
 */
public interface InsertSelectTest {

    String OQL_FILE_PATH = "oql/insert/InsertSelectTest.json";

    String OQL_INSERT_SELECT_FROM_HOBBY = "InsertHobbySelectFromHobby";

    String OQL_INSERT_TRAVEL_TRIP_SELECT_FROM_TRAVEL_TRIP_LITERAL = "InsertTravelTripSelectFromTravelTripLiteral";

    String OQL_INSERT_TRAVEL_TRIP_SELECT_FROM_TRAVEL_TRIP_FIELDS = "InsertTravelTripSelectFromTravelTripFields";

    void InsertHobbySelectFromHobby();

    void InsertTravelTripSelectFromTravelTripLiteral();

    void InsertTravelTripSelectFromTravelTripFields();

}


