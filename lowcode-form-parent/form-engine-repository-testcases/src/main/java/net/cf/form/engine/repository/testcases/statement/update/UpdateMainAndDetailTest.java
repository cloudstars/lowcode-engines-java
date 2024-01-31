package net.cf.form.engine.repository.testcases.statement.update;

/**
 * 更新主对象和子对象（出差+行程）的OQL语句测试
 *
 * @author clouds
 */
@Deprecated
public interface UpdateMainAndDetailTest {

    /**
     * 更新（常量的）出差主对象和行程子对象（第一条插入，省略ID和MASTER字段，第二条更新，省略MASTER字段、enterpriseKey、createdBy和createdAt字段）
     */
    String UPDATE_TRAVEL_AND_SCHEDULES_OQL = "update Travel set userId = 'zhangsan', startDate = '2023-06-07', endDate = '2023-06-10', reason = '出差培训', attach = {\"name\": \"这是一个文件名\", \"id\": 123}, schedules(scheduleId, startDate, endDate, destination, todo) = [(null, '2023-06-07', '2023-06-08', '北京', '项目会议'), (1, '2023-06-07', '2023-06-08', '南京', '学术会议')] where applyId = 1" ;

    /**
     * 更新（带变量的）出差主对象和行程子对象（数据传null会被变更，不传值会忽略）
     */
    String UPDATE_TRAVEL_AND_SCHEDULES_VAR_OQL = "update Travel set userId = #{userId}, startDate = #{startDate}, endDate = #{endDate}, reason = #{reason}, attach = #{attach}, schedules(scheduleId, startDate, endDate, destination, todo) = schedules(#{scheduleId}, #{startDate}, #{endDate}, #{destination}, #{todo}) where applyId = #{applyId}";

    /**
     * 测试更新（常量的）出差表的 OQL 解析
     */
    void testUpdateTravelAndSchedules();

    /**
     * 测试更新（带变量的）出差表的 OQL 解析
     */
    void testUpdateTravelAndSchedulesVar();
}
