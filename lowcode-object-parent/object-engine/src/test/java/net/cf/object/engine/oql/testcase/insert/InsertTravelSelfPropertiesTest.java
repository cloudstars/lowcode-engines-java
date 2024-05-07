package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入出差记录，并且不插入行程子表
 *
 * @author clouds
 */
public interface InsertTravelSelfPropertiesTest {

    String OQL_FILE_PATH = "oql/insert/InsertTravelSelfProperties.json";

    String OQL_INSERT_TRAVEL_WITH_CREATOR = "InsertTravelWithCreator";

    String OQL_INSERT_TRAVEL_WITH_CREATOR_VARS = "InsertTravelWithCreatorVars";

    String OQL_INSERT_TRAVEL_WITH_ATTACHES_VARS = "InsertTravelWithAttachesVars";

    void testInsertTravelWithCreator();

    void testInsertTravelWithCreatorVars();

    void testInsertTravelWithAttachesVars();

}


