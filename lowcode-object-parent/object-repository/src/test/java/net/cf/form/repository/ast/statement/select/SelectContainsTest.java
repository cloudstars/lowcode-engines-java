
package net.cf.form.repository.ast.statement.select;

import net.cf.form.repository.ast.statement.AbstractSqlTest;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.util.SqlStatementUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectContainsTest extends AbstractSqlTest {

    public SelectContainsTest() {
        super("sql/select/SelectContains.json");
    }

    /**
     * 测试 like 语法
     */
    @Test
    public void testSelectContains() {
        String sql = this.sqlMap.get("SELECT_CONTAINS");
        assert (sql != null);

        // 解析SQL
        SqlSelectStatement stmt0 = SqlStatementUtils.parseSingleSelectStatement(sql);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));
    }

}
