package net.cf.form.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlJoinTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.form.repository.testcases.statement.select.AbstractSelectJoinTablesTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * 测试表的JOIN
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class SelectJoinTablesTest extends AbstractSelectJoinTablesTest {

    @Test
    @Override
    public void testSelectImplicitCrossJoin() {
        super.testSelectImplicitCrossJoin();
    }

    @Override
    protected void testSelectImplicitCrossJoin(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        SqlTableSource tableSource = stmt0.getSelect().getFrom();
        Assert.assertTrue(tableSource instanceof SqlJoinTableSource);
        Assert.assertTrue(((SqlJoinTableSource) tableSource).getJoinType() == SqlJoinTableSource.JoinType.COMMA);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }

    @Test
    @Override
    public void testSelectExplicitCrossJoin() {
        super.testSelectExplicitCrossJoin();
    }

    @Override
    protected void testSelectExplicitCrossJoin(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        SqlTableSource tableSource = stmt0.getSelect().getFrom();
        Assert.assertTrue(tableSource instanceof SqlJoinTableSource);
        Assert.assertTrue(((SqlJoinTableSource) tableSource).getJoinType() == SqlJoinTableSource.JoinType.CROSS_JOIN);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }

    @Test
    @Override
    public void testSelectJoin() {
        super.testSelectJoin();
    }

    @Override
    protected void testSelectJoin(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        SqlTableSource tableSource = stmt0.getSelect().getFrom();
        Assert.assertTrue(tableSource instanceof SqlJoinTableSource);
        SqlJoinTableSource joinTableSource = ((SqlJoinTableSource) tableSource);
        Assert.assertTrue(joinTableSource.getJoinType() == SqlJoinTableSource.JoinType.JOIN);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }

    @Test
    @Override
    public void testSelectLeftJoin() {
        super.testSelectLeftJoin();
    }

    @Override
    protected void testSelectLeftJoin(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        SqlTableSource tableSource = stmt0.getSelect().getFrom();
        Assert.assertTrue(tableSource instanceof SqlJoinTableSource);
        Assert.assertTrue(((SqlJoinTableSource) tableSource).getJoinType() == SqlJoinTableSource.JoinType.LEFT_OUTER_JOIN);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }

    @Test
    @Override
    public void testSelectJoinOn() {
        super.testSelectJoinOn();
    }

    @Override
    protected void testSelectJoinOn(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        SqlTableSource tableSource = stmt0.getSelect().getFrom();
        Assert.assertTrue(tableSource instanceof SqlJoinTableSource);
        SqlJoinTableSource joinTableSource = (SqlJoinTableSource) tableSource;
        Assert.assertTrue(joinTableSource.getJoinType() == SqlJoinTableSource.JoinType.JOIN);
        Assert.assertTrue(joinTableSource.getCondition() != null);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }
}
