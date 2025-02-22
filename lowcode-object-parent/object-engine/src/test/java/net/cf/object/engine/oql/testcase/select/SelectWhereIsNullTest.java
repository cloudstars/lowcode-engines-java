package net.cf.object.engine.oql.testcase.select;

/**
 * 测试 where条件 is [not] null
 *
 * @author clouds
 */
public interface SelectWhereIsNullTest {

    String OQL_FILE_PATH = "oql/select/SelectWhereIsNull.json";

    String OQL_SELECT_IS_NULL = "SelectWhereIsNull";

    String OQL_SELECT_IS_NOT_NULL = "SelectWhereIsNotNull";

    void testSelectWhereIsNull();

    void testSelectWhereIsNotNull();

}