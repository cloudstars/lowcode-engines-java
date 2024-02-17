package net.cf.form.repository.ast.statement.insert;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlExprTableSource;
import net.cf.form.repository.sql.ast.statement.SqlInsertStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.form.repository.testcases.statement.insert.AbstractSimpleInsertSqlsTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class SimpleInsertSqlsTest extends AbstractSimpleInsertSqlsTest {

    @Test
    @Override
    public void testInsertWithoutParams() {
        super.testInsertWithoutParams();
    }


    @Override
    protected void testInsertWithoutParams(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlInsertStatement);
        SqlInsertStatement stmt0 = (SqlInsertStatement) stmts.get(0);
        Assert.assertTrue("A".equals((stmt0.getTableSource().getTableName())));
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }

    @Test
    @Override
    public void testInsertWithParams() {
        super.testInsertWithParams();
    }

    @Override
    protected void testInsertWithParams(String sql) {
        SqlStatementParser parser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = parser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlInsertStatement);
        SqlInsertStatement stmt0 = (SqlInsertStatement) stmts.get(0);
        Assert.assertTrue(stmt0.getTableSource() instanceof SqlExprTableSource);
        Assert.assertTrue("tableA".equals(stmt0.getTableName().getSimpleName()));
        Assert.assertTrue(stmt0.getColumns().size() == 3);
        Assert.assertTrue(stmt0.getValues().getValues().size() == 3);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }
}
