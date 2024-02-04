package net.cf.form.repository.testcases.statement.select;

import net.cf.form.repository.testcases.statement.AbstractSqlTest;
import org.junit.BeforeClass;

/**
 * 测试查询字段
 *
 * @author clouds
 */
public abstract class AbstractSelectColumnsTest extends AbstractSqlTest {

    // 本测试类要测试的SQL语句
    private static final String SQL_FILE_PATH = "sql/select/SelectColumns.json";


    @BeforeClass
    public static void init() {
        AbstractSqlTest.initSqls(SQL_FILE_PATH);
    }

    /**
     * 测试仅查询一列的SQL语句的解析
     */
    public void testSelectAllColumns() {
        String sql = this.sqlMap.get("SELECT_ALL_COLUMNS");
        assert (sql != null);

        this.testSelectAllColumns(sql);
    }

    protected abstract void testSelectAllColumns(String sql);


    /**
     * 测试仅查询一列的SQL语句的解析
     */
    public void testSelectOneColumn() {
        String sql = this.sqlMap.get("SELECT_ONE_COLUMN");
        assert (sql != null);

        this.testSelectOneColumn(sql);
    }

    protected abstract void testSelectOneColumn(String sql);


    /**
     * 测试查询多列的SQL语句的解析
     */
    public void testSelectMultipleColumns() {
        String sql = this.sqlMap.get("SELECT_MULTIPLE_COLUMNS");
        assert (sql != null);

        this.testSelectMultipleColumns(sql);
    }

    protected abstract void testSelectMultipleColumns(String sql);

}
