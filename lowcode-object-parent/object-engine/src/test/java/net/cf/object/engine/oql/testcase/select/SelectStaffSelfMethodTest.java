package net.cf.object.engine.oql.testcase.select;

/**
 * 查询员工本表，测试查询方法调用
 *
 * @author clouds
 */
public interface SelectStaffSelfMethodTest {

    String OQL_FILE_PATH = "oql/select/SelectStaffSelfMethod.json";

    String OQL_SELECT_STAFF_TEXT_METHODS = "SelectStaffSelfTextMethods";

    String OQL_SELECT_STAFF_DATE_METHODS = "SelectStaffSelfDateMethods";

    void testSelectStaffTextMethods();

    void testSelectStaffDateMethods();

}