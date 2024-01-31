package net.cf.form.engine.repository.testcases.statement.insert;

/**
 * 插入主对象（出差对象）测试接口
 *
 * @author clouds
 */
@Deprecated
public interface InsertMainTest {

    /**
     * 插入（常量的）出差主对象
     */
    String INSERT_TRAVEL_OQL = "insert into Travel(userId, startDate, endDate, reason, attach) values ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', {\"name\": \"这是一个文件名\", \"id\": 123});";

    /**
     * 插入（带变量预编译的）出差主对象
     */
    String INSERT_TRAVEL_VAR_OQL = "insert into Travel(userId, startDate, endDate, reason, attach) values (#{userId}, #{startDate}, #{endDate}, #{reason}, #{attach})";
    
    /**
     * 批量插入（常量的）出差主对象
     */
    String INSERT_MULTI_TRAVELS_OQL = "insert into Travel(userId, startDate, endDate, reason, attach) values ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', {\"name\": \"这是一个文件名\", \"id\": 123}), ('zhangsan', '2023-06-07', '2023-06-10', '出差培训', {\"name\": \"这是另一个文件名\", \"id\": 124});";

    /**
     * 批量插入（带变量预编译的）出差主对象
     */
    String INSERT_MULTI_TRAVELS_VAR_OQL = "insert into Travel(userId, startDate, endDate, reason, attach) values (#{userId0}, #{startDate0}, #{endDate0}, #{reason0}, #{attach0}), (#{userId1}, #{startDate1}, #{endDate1}, #{reason1}, #{attach1})";

    /**
     * 测试插入（常量的）出差表的 OQL 解析
     */
    void testInsertTravel();

    /**
     * 测试插入（带变量的 ）出差表的 OQL 解析
     */
    void testInsertTravelVar();

    /**
     * 测试批量插入（常量的）出差表的 OQL 解析
     */
    void testInsertMultiTravels();

    /**
     * 测试批量插入（带变量的）出差表的 OQL 解析
     */
    void testInsertMultiTravelsVar();
}