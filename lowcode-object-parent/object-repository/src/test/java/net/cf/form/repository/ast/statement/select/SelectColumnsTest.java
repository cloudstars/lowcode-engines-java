package net.cf.form.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.repository.ast.statement.AbstractSqlTest;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.expr.identifier.SqlAllColumnExpr;
import net.cf.form.repository.sql.ast.expr.identifier.SqlIdentifierExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.statement.SqlSelectItem;
import net.cf.form.repository.sql.ast.statement.SqlSelectStatement;
import net.cf.form.repository.sql.util.SqlStatementUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class SelectColumnsTest extends AbstractSqlTest {

    public SelectColumnsTest() {
        super("sql/select/SelectColumns.json");
    }

    /**
     * 测试仅查询一列的SQL语句的解析
     */
    @Test
    public void testSelectAllColumns() {
        String sql = this.sqlMap.get("SELECT_ALL_COLUMNS");
        assert (sql != null);

        SqlSelectStatement stmt0 = SqlStatementUtils.parseSingleSelectStatement(sql);
        SqlSelectItem selectItem = stmt0.getSelect().getSelectItems().get(0);
        Assert.assertTrue(selectItem.getExpr() instanceof SqlAllColumnExpr);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmt0, druidStmts.get(0)));
    }

    /**
     * 测试仅查询一列的SQL语句的解析
     */
    @Test
    public void testSelectOneColumn() {
        String sql = this.sqlMap.get("SELECT_ONE_COLUMN");
        assert (sql != null);

        SqlSelectStatement stmt0 = SqlStatementUtils.parseSingleSelectStatement(sql);
        List<SqlSelectItem> selectItems = stmt0.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 1);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof SqlIdentifierExpr);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmt0, druidStmts.get(0)));
    }


    /**
     * 测试查询多列的SQL语句的解析
     */
    @Test
    public void testSelectMultipleColumns() {
        String sql = this.sqlMap.get("SELECT_MULTIPLE_COLUMNS");
        assert (sql != null);

        SqlSelectStatement stmt0 = SqlStatementUtils.parseSingleSelectStatement(sql);
        List<SqlSelectItem> selectItems = stmt0.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 3);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof SqlIdentifierExpr);
        Assert.assertTrue(selectItems.get(1).getExpr() instanceof SqlIdentifierExpr);
        Assert.assertTrue(selectItems.get(2).getExpr() instanceof SqlIntegerExpr);
        Assert.assertTrue(SqlTestUtils.equalsIgnoreWhiteSpace(stmt0.toString(), sql));

        // 与 Druid 的 AST 作对比
        SQLStatementParser druidSqlParser = new SQLStatementParser(sql);
        List<SQLStatement> druidStmts = druidSqlParser.parseStatementList();
        Assert.assertTrue(SqlTestUtils.equals(stmt0, druidStmts.get(0)));
    }
}
