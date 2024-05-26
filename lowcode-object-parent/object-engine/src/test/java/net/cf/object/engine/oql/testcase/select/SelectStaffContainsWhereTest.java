package net.cf.object.engine.oql.testcase.select;

/**
 * 查询员工本表，测试contains查询条件
 *
 * @author clouds
 */
public interface SelectStaffContainsWhereTest {

    String OQL_FILE_PATH = "oql/select/SelectStaffSelfContains.json";

    String OQL_SELECT_STAFF_BY_HOBBY = "SelectStaffByHobby";

    String OQL_SELECT_STAFF_BY_NOT_HOBBY = "SelectStaffByNotHobby";

    void testSelectStaffByHobby();

    void testSelectStaffByNotHobby();

}