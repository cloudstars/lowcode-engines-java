package net.cf.form.engine.repository.testcases.statement.update;

/**
 * 更新主对象（出差对象）测试接口
 *
 * @author clouds
 */
@Deprecated
public interface UpdateMainTest {

    /**
     * 更新（常量的）出差主对象
     */
    String UPDATE_TRAVEL_OQL = "update Travel set userId = 'zhangsan', startDate = '2023-06-08', endDate = '2023-06-11', reason = '出差培训（变更）', attach = {\"name\": \"这是一个文件名\", \"id\": 124} where applyId = 1";

    /**
     * 更新（带变量预编译的）出差主对象
     */
    String UPDATE_TRAVEL_VAR_OQL = "update Travel set userId = #{userId}, startDate = #{startDate}, endDate = #{endDate}, reason = #{reason}, attach = #{attach} where applyId = #{applyId}";

    /**
     * 测试更新（常量的）出差表的 OQL 解析
     */
    void testUpdateTravel();

    /**
     * 测试更新（带变量）的出差表的 OQL 解析
     */
    void testUpdateTravelVar();

}
