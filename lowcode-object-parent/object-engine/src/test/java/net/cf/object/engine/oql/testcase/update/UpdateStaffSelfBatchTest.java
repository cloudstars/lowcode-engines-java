package net.cf.object.engine.oql.testcase.update;

/**
 * 插入员工本表（批量）记录
 *
 * @author clouds
 */
public interface UpdateStaffSelfBatchTest {

    String OQL_FILE_PATH = "oql/update/UpdateStaffBatch.json";

    String OQL_UPDATE_STAFF_BATCH_VARS = "UpdateStaffBatchVars";

    void testUpdateStaffBatchVars();

}


