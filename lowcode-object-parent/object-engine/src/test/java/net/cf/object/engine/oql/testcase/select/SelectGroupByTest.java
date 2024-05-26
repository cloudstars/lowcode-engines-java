package net.cf.object.engine.oql.testcase.select;

/**
 * 测试 group by
 *
 * @author clouds
 */
public interface SelectGroupByTest {

    String OQL_FILE_PATH = "oql/select/SelectGroupBy.json";

    String OQL_SELECT_GROUP_BY = "SelectGroupByStaffCode";

    void testSelectGroupByStaffCode();

}