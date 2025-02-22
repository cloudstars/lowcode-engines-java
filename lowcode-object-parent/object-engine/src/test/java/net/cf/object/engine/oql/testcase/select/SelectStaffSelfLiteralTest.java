package net.cf.object.engine.oql.testcase.select;

/**
 * 查询员工本表，测试查询常量
 *
 * @author clouds
 */
public interface SelectStaffSelfLiteralTest {

    String OQL_FILE_PATH = "oql/select/SelectStaffSelfLiteral.json";

    String OQL_SELECT_STAFF_SELF_LITERAL = "SelectStaffSelfLiteral";

    void testSelectStaffSelfLiteral();

}