package net.cf.object.engine.oql.testcase.select;

/**
 * 查询员工本表，测试查询常量
 *
 * @author clouds
 */
public interface SelectStaffSelfMethodTest {

    String OQL_FILE_PATH = "oql/select/SelectStaffSelfMethod.json";

    String OQL_SELECT_STAFF_SELF_METHOD = "SelectStaffSelfMethod";

    void testSelectStaffSelfMethod();

}