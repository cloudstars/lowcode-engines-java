package net.cf.object.engine.oql.testcase.select;

/**
 * 分页查询测试接口
 *
 * @author clouds
 */
public interface SelectDistinctTest {

    String OQL_FILE_PATH = "oql/select/SelectDistinctTest.json";

    String OQL_SELECT_DISTINCT_LIST = "SelectDistinctList";

    void testSelectDistinctList();

}