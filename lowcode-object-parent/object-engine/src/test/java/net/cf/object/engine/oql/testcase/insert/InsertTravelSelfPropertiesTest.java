package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入出差记录，并且不插入行程子表
 *
 * @author clouds
 */
public interface InsertTravelSelfPropertiesTest {

    String OQL_FILE_PATH = "oql/insert/InsertTravelSelfProperties.json";

    String OQL_INSERT_TRAVEL_WITH_CREATOR = "InsertTravelWithCreator";

    /**
     * 测试插入出差记录，带创建人
     *
     */
    void testInsertTravelWithCreator();

}


