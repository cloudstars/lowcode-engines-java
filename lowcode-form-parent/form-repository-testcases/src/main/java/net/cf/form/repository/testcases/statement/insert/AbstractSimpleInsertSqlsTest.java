package net.cf.form.repository.testcases.statement.insert;

import net.cf.form.repository.testcases.statement.AbstractSqlTest;
import org.junit.BeforeClass;

/**
 * 简单的SQL插入语句测试
 *
 * @author clouds
 */
public abstract class AbstractSimpleInsertSqlsTest extends AbstractSqlTest {

    // 本测试类要测试的SQL语句
    private static final String SQL_FILE_PATH = "sql/insert/InsertSimple.json";

    /**
     * 带参数的Insert语句的名称
     */
    protected String INSERT_WITH_PARAMS_NAME = "insert-with-params";

    @BeforeClass
    public static void init() {
        AbstractSqlTest.initSqls(SQL_FILE_PATH);
    }

    /**
     * 测试不带参数的Insert语句的解析
     */
    public void testInsertWithoutParams() {
        String sql = this.sqlMap.get("insert-without-params");
        this.testInsertWithoutParams(sql);
    }

    protected abstract void testInsertWithoutParams(String sql);

    /**
     * 测试带参数的Insert语句的解析
     */
    public void testInsertWithParams() {
        String sql = this.sqlMap.get("insert-with-params");
        this.testInsertWithParams(sql);
    }

    protected abstract void testInsertWithParams(String sql);
}
