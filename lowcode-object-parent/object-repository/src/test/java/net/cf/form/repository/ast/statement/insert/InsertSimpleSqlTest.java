package net.cf.form.repository.ast.statement.insert;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.statement.AbstractSqlTest;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.util.SqlStatementUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class InsertSimpleSqlTest extends AbstractSqlTest {

    public InsertSimpleSqlTest() {
        super("sql/insert/InsertSimple.json");
    }

    /**
     * 测试不带参数的Insert语句的解析
     */
    @Test
    public void testInsertWithoutParams() {
        String sql = this.sqlMap.get("insert-without-params");
        assert (sql != null);

        SqlInsertStatement stmt = SqlStatementUtils.parseSingleInsertStatement(sql);
        Assert.assertTrue(stmt.getTableSource() instanceof SqlExprTableSource);
        Assert.assertTrue("A".equals((stmt.getTableSource().getTableName())));
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmt, druidStmts.get(0)));
    }

    /**
     * 测试带参数的Insert语句的解析
     */
    @Test
    public void testInsertWithParams() {
        String sql = this.sqlMap.get("insert-with-params");
        SqlInsertStatement stmt = SqlStatementUtils.parseSingleInsertStatement(sql);
        Assert.assertTrue(stmt.getTableSource() instanceof SqlExprTableSource);
        Assert.assertTrue("tableA".equals(stmt.getTableName().getName()));
        Assert.assertTrue(stmt.getColumns().size() == 3);
        Assert.assertTrue(stmt.getValues().getValues().size() == 3);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmt, druidStmts.get(0)));
    }

}
