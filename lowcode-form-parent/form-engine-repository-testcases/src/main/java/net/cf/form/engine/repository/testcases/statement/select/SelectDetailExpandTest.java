package net.cf.form.engine.repository.testcases.statement.select;

/**
 * 测试子表字段展示
 *
 * @author clouds
 */
@Deprecated
public interface SelectDetailExpandTest {

    /**
     * 查询出差、行程部分字段的OQL语句（全局展开）
     */
    String SELECT_TRAVEL_AND_SCHEDULES_EXPAND1_OQL = "select *, schedules(scheduleId, travel, startDate, endDate, destination, todo, status) from Travel";

    /**
     * 查询出差、行程部分字段的OQL语句（局部展开）
     */
    String SELECT_TRAVEL_AND_SCHEDULES_EXPAND2_OQL = "select *, schedules.scheduleId, schedules.travel, schedules(startDate, endDate, destination, todo, status) from Travel";


    /**
     * 测试查询出差表的OQL解析（全量展开模式）
     */
    void testSelectTravelAndScheduleExpand1();

    /**
     * 测试查询出差表的OQL解析（混合展开模式）
     */
    void testSelectTravelAndScheduleExpand2();
}
