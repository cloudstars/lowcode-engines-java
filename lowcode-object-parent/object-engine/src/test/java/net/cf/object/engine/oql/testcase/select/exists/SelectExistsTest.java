package net.cf.object.engine.oql.testcase.select.exists;

/**
 * 查询Exists语法测试
 *
 * @author clouds
 */
public interface SelectExistsTest {

    String OQL_FILE_PATH = "oql/select/exists/SelectExists.json";

    String OQL_SELECT_EXISTS = "SelectExists";

    void testSelectExists();

}