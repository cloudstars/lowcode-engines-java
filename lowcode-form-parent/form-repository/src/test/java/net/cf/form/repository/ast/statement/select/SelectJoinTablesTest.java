package net.cf.form.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.statement.AbstractSqlTest;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.statement.SqlJoinTableSource;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.ast.statement.SqlTableSource;
import net.cf.form.repository.sql.parser.SqlStatementParser;
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
public class SelectJoinTablesTest extends AbstractSqlTest {

    public SelectJoinTablesTest() {
        super("sql/select/SelectJoinTables.json");
    }

    /**
     * 测试两个表的隐式交叉连接
     */
    @Test
    public void testSelectImplicitCrossJoin() {
        String sql = this.sqlMap.get("SELECT_IMPLICIT_CROSS_JOIN");
        assert (sql != null);

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

    /**
     * 测试两个表的显示交叉连接
     */
    @Test
    public void testSelectExplicitCrossJoin() {
        String sql = this.sqlMap.get("SELECT_EXPLICIT_CROSS_JOIN");
        assert (sql != null);

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

    /**
     * 测试两个表Join
     */
    @Test
    public void testSelectJoin() {
        String sql = this.sqlMap.get("SELECT_JOIN");
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

    /**
     * 测试两个表Left Join
     */
    @Test
    public void testSelectLeftJoin() {
        String sql = this.sqlMap.get("SELECT_LEFT_JOIN");
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

    /**
     * 测试两个表 Join On Condition
     */
    @Test
    public void testSelectJoinOn() {
        String sql = this.sqlMap.get("SELECT_JOIN_ON");
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
