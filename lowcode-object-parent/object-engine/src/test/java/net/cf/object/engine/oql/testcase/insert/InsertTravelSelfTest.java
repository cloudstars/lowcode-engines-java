package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入出差记录，并且不插入行程子表
 *
 * @author clouds
 */
public interface InsertTravelSelfTest {

    String OQL_FILE_PATH = "oql/insert/InsertTravelSelf.json";

    String OQL_INSERT_TRAVEL = "InsertTravel";

    /**
     * 测试插入出差记录
     *
     */
    void testInsertTravel();

}


