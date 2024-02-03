package net.cf.form.repository.testcases.statement.delete;

/**
 * 删除语句测试（会同步删除相关的子表数据）
 *
 * @author clouds
 */
@Deprecated
public interface DeleteMainTest {

    /**
     * 删除第一条记录的出差原因
     */
    String DELETE_TRAVEL_OQL = "delete from Travel where applyId = 1";

    /**
     * 删除第一条记录的出差原因
     */
    String DELETE_TRAVEL_VAR_OQL = "delete from Travel where applyId = #{applyId}";

    /**
     * 删除第一条记录的出差原因
     */
    String DELETE_TRAVEL_IN_OQL = "delete from Travel where applyId in (1, 2)";


    /**
     * 删除第一条记录的出差原因
     */
    String DELETE_TRAVEL_IN_VAR_OQL = "delete from Travel where applyId in (#{applyId0}, #{applyId1})";

    /**
     * 测试删除出差记录
     */
    void testDeleteTravel();

    /**
     * 测试删除（带变量的）出差记录
     */
    void testDeleteTravelVar();

    /**
     * 测试删除出差记录，按in过滤
     */
    void testDeleteTravelIn();


    /**
     * 测试删除（带变量的）出差记录，按in过滤
     */
    void testDeleteTravelInVar();
}
