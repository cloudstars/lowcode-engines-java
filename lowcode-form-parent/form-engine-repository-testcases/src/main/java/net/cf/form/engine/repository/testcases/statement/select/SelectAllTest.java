package net.cf.form.engine.repository.testcases.statement.select;

/**
 * 测试查询全部的OQL语句的翻译
 *
 * 主对象：Travel 子对象：Travel
 *
 * @author clouds
 */
@Deprecated
public interface SelectAllTest {

    /**
     * 查询全部出差的OQL语句
     */
    String SELECT_TRAVEL_ALL_OQL = "select * from Travel";

    /**
     * 查询全部行程的OQL语句
     */
    String SELECT_SCHEDULE_ALL_OQL = "select * from TravelSchedule";

    /**
     * 查询全部行程的OQL语句
     */
    String SELECT_CLAIM_ALL_OQL = "select * from TravelClaim";

    /**
     * 查询出差 + 行程的全部数据的OQL语句
     */
     String SELECT_TRAVEL_AND_SCHEDULE_ALL_OQL = "select *, schedules.* as schedules from Travel";

    /**
     * 查询行程 + 出差的全部数据的OQL语句
     */
    String SELECT_SCHEDULE_AND_TRAVEL_ALL_OQL = "select *, travel.* from TravelSchedule";

    /**
     * 查询出差报销 + 行程（多选）的全部数据的OQL语句
     */
    String SELECT_CLAIM_AND_SCHEDULE_ALL_OQL = "select *, schedules.* from TravelClaim";

    /**
     * 查询出差报销 + 员工（单选）的全部数据的OQL语句
     */
    String SELECT_CLAIM_AND_STAFF_ALL_OQL = "select *, staff.* from TravelClaim";

    /**
     * 测试查询全部出差数据的OQL解析
     */
    void testSelectTravelAll();

    /**
     * 测试查询全部出差行程数据的OQL解析
     */
    void testSelectScheduleAll();

    /**
     * 测试查询全部出差报销数据的OQL解析
     */
    void testSelectClaimAll();

    /**
     * 测试查询出差 + 行程的全部数据的OQL解析
     */
    void testSelectTravelAndScheduleAll();

    /**
     * 测试查询行程 + 出差的全部数据的OQL解析
     */
    void testSelectScheduleAndTravelAll();

    /**
     * 测试查询报销 + 行程(多选)的全部数据的OQL解析
     */
    void testSelectClaimAndScheduleAll();

    /**
     * 测试查询报销 + 员工(单选)的全部数据的OQL解析
     */
    void testSelectClaimAndStaffAll();

}
