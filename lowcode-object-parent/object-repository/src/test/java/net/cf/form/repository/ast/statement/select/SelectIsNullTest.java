
package net.cf.form.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.statement.AbstractSqlTest;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.util.SqlStatementUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class SelectIsNullTest extends AbstractSqlTest {

    public SelectIsNullTest() {
        super("sql/select/SelectIsNull.json");
    }

    /**
     * 测试 is null 语法
     */
    @Test
    public void testSelectIsNull() {
        String sql = this.sqlMap.get("SELECT_IS_NULL");
        assert (sql != null);

        // 解析SQL
        SqlSelectStatement stmt0 = SqlStatementUtils.parseSingleSelectStatement(sql);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmt0, druidStmts.get(0)));
    }

}
