package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入员工本表（批量）记录
 *
 * @author clouds
 */
public interface InsertStaffSelfBatchTest {

    String OQL_FILE_PATH = "oql/insert/InsertStaff.json";

    String OQL_INSERT_STAFF_BATCH_VARS = "InsertStaffBatchVars";

    /**
     * 测试批量插入员工记录
     *
     */
    void testInsertStaffBatchVars();

}


