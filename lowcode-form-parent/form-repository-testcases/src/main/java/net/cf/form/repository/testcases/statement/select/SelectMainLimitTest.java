package net.cf.form.repository.testcases.statement.select;

/**
 * 测试主表Limit查询接口
 *
 * @author clouds
 */
@Deprecated
public interface SelectMainLimitTest {

    String SELECT_TRAVEL_LIMIT_OQL = "select applyId from Travel where 1 = 1 limit 10";

    // IN条件
    String SELECT_TRAVEL_LIMIT_OFFSET_OQL = "select applyId from Travel where 1 = 1 limit 100, 10";

    void testSelectTravelLimit();

    void testSelectTravelLimitOffset();
}