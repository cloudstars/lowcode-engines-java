package net.cf.object.engine.oql.testcase.select;

/**
 * 分页查询测试接口
 *
 * @author clouds
 */
public interface SelectPageTest {

    String OQL_FILE_PATH = "oql/select/SelectPageTest.json";

    String OQL_SELECT_STAFF_PAGE_LIST = "SelectStaffPageList";

    void testSelectStaffPageList();

}