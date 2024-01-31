package net.cf.form.engine.repository.testcases.statement.insert;

/**
 * 插入主对象和子对象（出差+行程）的OQL语句测试
 *
 * @author clouds
 */
@Deprecated
public interface InsertMainAndDetailTest {

    /**
     * 插入出差主对象和行程子对象
     */
    String INSERT_TRAVELS_AND_SCHEDULES_OQL = "insert into Travel(userId, startDate, endDate, reason, attach, schedules(startDate, endDate, destination, todo)) values ('wangwu', '2023-06-07', '2023-06-10', '出差培训', {\"name\": \"这是一个文件名\", \"id\": 123}, [('2023-06-07', '2023-06-08', '北京', '项目会议'), ('2023-06-09', '2023-06-10', '南京', '学术研讨')]);";

    /**
     * 插入(带变量的)出差主对象和行程子对象
     */
    String INSERT_TRAVELS_AND_SCHEDULES_VAR_OQL = "insert into Travel(userId, startDate, endDate, reason, attach, schedules(startDate, endDate, destination, todo)) values (#{userId}, #{startDate}, #{endDate}, #{reason}, #{attach}, schedules(#{startDate}, #{endDate}, #{destination}, #{todo}));";

    /**
     * 批量插入（常量的）出差主对象和行程子对象
     */
    String INSERT_MULTI_TRAVELS_AND_SCHEDULES_OQL = "insert into Travel(userId, startDate, endDate, reason, attach, schedules(startDate, endDate, destination, todo)) values ('wangwu', '2023-06-07', '2023-06-10', '出差培训', {\"name\": \"这是一个文件名\", \"id\": 123}, [('2023-06-07', '2023-06-08', '北京', '项目会议'), ('2023-06-09', '2023-06-10', '南京', '学术研讨')]), ('wangwu', '2023-06-07', '2023-06-10', '出差培训', {\"name\": \"这是另一个文件名\", \"id\": 124}, [('2023-06-07', '2023-06-08', '北京', '项目会议'), ('2023-06-09', '2023-06-10', '南京', '学术研讨'), ('2023-06-09', '2023-06-10', '天津', '公费旅游')]);";

    /**
     * 批量插入(带变量的)出差主对象和行程子对象
     */
    String INSERT_MULTI_TRAVELS_AND_SCHEDULES_VAR_OQL = "insert into Travel(userId, startDate, endDate, reason, attach, schedules(startDate, endDate, destination, todo)) values (#{userId0}, #{startDate0}, #{endDate0}, #{reason0}, #{attach0}, schedules0(#{startDate}, #{endDate}, #{destination}, #{todo})), (#{userId1}, #{startDate1}, #{endDate1}, #{reason1}, #{attach1}, schedules1(#{startDate}, #{endDate}, #{destination}, #{todo}))";

    /**
     * 测试插入（常量的）出差表的 OQL 解析
     */
    void testInsertTravelsAndSchedules();

    /**
     * 测试插入（带变量的）出差表的 OQL 解析
     */
    void testInsertTravelsAndSchedulesVar();

    /**
     * 测试插入（常量的）（多条）出差表的 OQL 解析
     */
    void testInsertMultiTravelsAndSchedules();

    /**
     * 测试插入（带变量的）（多条）出差表的 OQL 解析
     */
    void testInsertMultiTravelsAndSchedulesVar();

}
