package net.cf.object.engine.oql.testcase.select;

/**
 * 分页多选的值查询的测试接口
 *
 * @author clouds
 */
public interface SelectMultiValuesTest {

    String OQL_FILE_PATH = "oql/select/SelectStaffMultiValues.json";

    String OQL_SELECT_MULTI_VALUE_LIST = "SelectStaffHobbiesMultiValueList";

    void testSelectStaffHobbiesMultiValueList();

}