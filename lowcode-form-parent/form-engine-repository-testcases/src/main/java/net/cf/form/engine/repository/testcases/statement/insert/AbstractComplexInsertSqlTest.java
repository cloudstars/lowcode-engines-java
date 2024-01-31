package net.cf.form.engine.repository.testcases.statement.insert;

import net.cf.form.engine.repository.testcases.statement.AbstractSqlTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 复杂的SQL插入语句测试
 *
 * @author clouds
 */
public abstract class AbstractComplexInsertSqlTest extends AbstractSqlTest {

    // 本测试类要测试的SQL语句
    private static final String SQL_FILE_PATH = "sql/insert/InsertComplex.json";

    /**
     * 从源表插入目标表的 insert 语句的名称
     */
    protected String INSERT_A_FROM_B = "insert-a-from-b";

    @BeforeClass
    public static void init() {
        AbstractSqlTest.initSqls(SQL_FILE_PATH);
    }

    /**
     * 测试不带参数的Insert语句的解析
     */
    @Test
    public void testInsertAFromB() {
        String sql = this.sqlMap.get(INSERT_A_FROM_B);
        this.testInsertAFromB(sql);
    }

    protected abstract void testInsertAFromB(String sql);

}
