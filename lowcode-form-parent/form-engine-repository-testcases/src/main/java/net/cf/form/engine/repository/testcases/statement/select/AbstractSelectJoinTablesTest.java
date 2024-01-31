
package net.cf.form.engine.repository.testcases.statement.select;

import net.cf.form.engine.repository.testcases.statement.AbstractSqlTest;
import org.junit.BeforeClass;

/**
 * 测试表的JOIN
 *
 * @author clouds
 */
public abstract class AbstractSelectJoinTablesTest extends AbstractSqlTest {

    // 本测试类要测试的SQL语句
    private static final String SQL_FILE_PATH = "sql/select/SelectJoinTables.json";

    @BeforeClass
    public static void init() {
        AbstractSqlTest.initSqls(SQL_FILE_PATH);
    }

    /**
     * 测试两个表的隐式交叉连接
     */
    public void testSelectImplicitCrossJoin() {
        String sql = this.sqlMap.get("SELECT_IMPLICIT_CROSS_JOIN");
        assert (sql != null);

        this.testSelectImplicitCrossJoin(sql);
    }

    protected abstract void testSelectImplicitCrossJoin(String sql);

    /**
     * 测试两个表的显示交叉连接
     */
    public void testSelectExplicitCrossJoin() {
        String sql = this.sqlMap.get("SELECT_EXPLICIT_CROSS_JOIN");
        assert (sql != null);

        this.testSelectExplicitCrossJoin(sql);
    }

    protected abstract void testSelectExplicitCrossJoin(String sql);


    /**
     * 测试两个表Join
     */
    public void testSelectJoin() {
        String sql = this.sqlMap.get("SELECT_JOIN");
        assert (sql != null);

        this.testSelectJoin(sql);
    }

    protected abstract void testSelectJoin(String sql);

    /**
     * 测试两个表Left Join
     */
    public void testSelectLeftJoin() {
        String sql = this.sqlMap.get("SELECT_LEFT_JOIN");
        assert (sql != null);

        this.testSelectLeftJoin(sql);
    }

    protected abstract void testSelectLeftJoin(String sql);

    /**
     * 测试两个表 Join On Condition
     */
    public void testSelectJoinOn() {
        String sql = this.sqlMap.get("SELECT_JOIN_ON");
        assert (sql != null);

        this.testSelectJoinOn(sql);
    }

    protected abstract void testSelectJoinOn(String sql);


}
