package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入出差记录，并且不插入行程子表
 *
 * @author clouds
 */
public interface InsertTravelSelfSimpleTest {

    String OQL_FILE_PATH = "oql/insert/InsertTravelSelfSimple.json";

    String OQL_INSERT_TRAVEL = "InsertTravel";

    String OQL_INSERT_TRAVEL_VARS = "InsertTravelVars";

    /**
     * 测试插入出差记录
     *
     */
    void testInsertTravel();

    /**
     * 测试插入出差记录（带变量）
     *
     */
    void testInsertTravelVars();

}


