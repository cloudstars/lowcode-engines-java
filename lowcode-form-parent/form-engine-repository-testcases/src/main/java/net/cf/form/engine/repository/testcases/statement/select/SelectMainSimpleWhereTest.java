package net.cf.form.engine.repository.testcases.statement.select;

/**
 * 测试简单的主表查询接口
 *
 * @author clouds
 */
@Deprecated
public interface SelectMainSimpleWhereTest {

    String SELECT_TRAVEL_TRUE_OQL = "select * from Travel where 1 = 1";

    // IN条件
    String SELECT_TRAVEL_IN_OQL = "select * from Travel where applyId in (1, 2)";

    // LIKE条件
    String SELECT_TRAVEL_LIKE_OQL = "select * from Travel where reason like '%出差%'";

    // 比较条件
    String SELECT_TRAVEL_COMPARE_OQL = "select * from Travel where (startDate >= '2023-06-07' and startDate <= '2023-06-15') or  (endDate >= '2023-06-10' and endDate <= '2023-06-20') ";

    /**
     * 测试带 1 = 1的条件
     */
    void testSelectTravelTrue();

    void testSelectTravelIn();

    void testSelectTravelLike();

    void testTravelCompare();

}