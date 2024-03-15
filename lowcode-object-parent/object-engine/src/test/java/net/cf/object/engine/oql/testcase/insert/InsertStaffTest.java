package net.cf.object.engine.oql.testcase.insert;

/**
 * 插入员工记录
 *
 * @author clouds
 */
public interface InsertStaffTest {

    String OQL_FILE_PATH = "oql/insert/InsertStaff.json";

    String OQL_INSERT_STAFF = "InsertStaff";

    /**
     * 测试插入员工记录
     *
     */
    void testInsertStaff();

}


