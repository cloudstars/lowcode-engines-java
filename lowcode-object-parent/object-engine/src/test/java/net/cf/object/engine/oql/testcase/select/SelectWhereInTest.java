package net.cf.object.engine.oql.testcase.select;

/**
 * 测试 where条件 [not] in
 *
 * @author clouds
 */
public interface SelectWhereInTest {

    String OQL_FILE_PATH = "oql/select/SelectWhereIn.json";

    String OQL_SELECT_IN = "SelectWhereIn";

    String OQL_SELECT_NOT_IN = "SelectWhereNotIn";

    void testSelectWhereIn();

    void testSelectWhereNotIn();

}