package net.cf.form.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.ast.statement.SqlStatement;
import net.cf.form.repository.sql.parser.SqlStatementParser;
import net.cf.form.repository.testcases.statement.select.AbstractSelectColumnsTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class SelectColumnsTest extends AbstractSelectColumnsTest {

    @Test
    @Override
    public void testSelectAllColumns() {
        super.testSelectAllColumns();
    }

    @Override
    protected void testSelectAllColumns(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        SqlSelectItem selectItem = stmt0.getSelect().getSelectItems().get(0);
        Assert.assertTrue(selectItem.getExpr() instanceof SqlAllColumnExpr);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }

    @Test
    @Override
    public void testSelectOneColumn() {
        super.testSelectOneColumn();
    }

    @Override
    protected void testSelectOneColumn(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        Assert.assertTrue(stmt0.getSelect().getSelectItems().size() == 1);
        SqlSelectItem selectItem = stmt0.getSelect().getSelectItems().get(0);
        Assert.assertTrue(selectItem.getExpr() instanceof SqlIdentifierExpr);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }

    @Test
    @Override
    public void testSelectMultipleColumns() {
        super.testSelectMultipleColumns();
    }

    @Override
    protected void testSelectMultipleColumns(String sql) {
        SqlStatementParser sqlParser = new SqlStatementParser(sql);
        List<SqlStatement> stmts = sqlParser.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SqlSelectStatement);
        SqlSelectStatement stmt0 = (SqlSelectStatement) stmts.get(0);
        Assert.assertTrue(stmt0.getSelect().getSelectItems().size() == 3);
        SqlSelectItem selectItem0 = stmt0.getSelect().getSelectItems().get(0);
        Assert.assertTrue(selectItem0.getExpr() instanceof SqlIdentifierExpr);
        SqlSelectItem selectItem1 = stmt0.getSelect().getSelectItems().get(1);
        Assert.assertTrue(selectItem1.getExpr() instanceof SqlIdentifierExpr);
        SqlSelectItem selectItem2 = stmt0.getSelect().getSelectItems().get(2);
        Assert.assertTrue(selectItem2.getExpr() instanceof SqlIntegerExpr);
        System.out.println(stmt0.toString());
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmts, druidStmts));
    }
}
