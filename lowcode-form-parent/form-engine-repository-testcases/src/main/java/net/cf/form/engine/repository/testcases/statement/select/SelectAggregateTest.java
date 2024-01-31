package net.cf.form.engine.repository.testcases.statement.select;

/**
 * 测试聚合查询的OQL语句的翻译
 *
 * 主对象：Travel 子对象：Travel
 *
 * @author clouds
 */
@Deprecated
public interface SelectAggregateTest {

    /**
     * COUNT
     */
    String COUNT_TRAVEL_OQL = "select count(*) as a, count(1) b, count(0) as b from Travel";

    /**
     * COUNT、MAX、MIN
     */
    String AGGR_GROUP_TRAVEL_OQL = "select userId, count(0), max(startDate) as maxStartDate, min(endDate) minEndDate from Travel where startDate > '2017-01-01' group by userId;";

    /**
     * COUNT、MAX、MIN + HAVING
     */
    String AGGR_GROUP_TRAVEL_HAVING_OQL = "select userId, count(0), max(startDate) as maxStartDate, min(endDate) minEndDate from Travel where startDate > '2017-01-01' group by userId having(count(0) > 1);";

    /**
     * 测试Count出差主对象
     */
    void testCountTravel();

}
