package net.cf.form.repository.ast.expr.literal;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.repository.ast.util.SqlTestUtils;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlCharExpr;
import net.cf.form.repository.sql.parser.SqlExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * 文本类型字面量解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class TextLiteralExprTest {

    public final String CHAR1 = "\'abc\'";

    public final String CHAR_DATETIME = "\'2024-01-01 10:10:10\'";

    public final String CHAR_TIME = "\'10:10:10\'";

    @Test
    public void testChar1() {
        SQLExprParser p = new SQLExprParser(CHAR1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLCharExpr);
        Assert.assertTrue("abc".equals(((SQLCharExpr) e).getText()));

        SqlExprParser parser = new SqlExprParser(CHAR1);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlCharExpr);
        Assert.assertTrue("abc".equals(((SqlCharExpr) expr).getText()));
    }

    @Test
    public void testDateTime1() {
        SQLExprParser p = new SQLExprParser(CHAR_DATETIME);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLCharExpr);
        Assert.assertTrue("2024-01-01 10:10:10".equals(((SQLCharExpr) e).getText()));

        SqlExprParser parser = new SqlExprParser(CHAR_DATETIME);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlCharExpr);
        Assert.assertTrue("2024-01-01 10:10:10".equals(((SqlCharExpr) expr).getText()));
    }

    @Test
    public void testTime1() {
        SqlExprParser parser = new SqlExprParser(CHAR_TIME);
        SqlExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof SqlCharExpr);
        Assert.assertTrue("10:10:10".equals(((SqlCharExpr) expr).getText()));

        // 与 Druid 的 AST 作对比
        SQLExprParser druidSqlParser = new SQLExprParser(CHAR_TIME);
        SQLExpr druidExpr = druidSqlParser.expr();
        Assert.assertTrue(SqlTestUtils.equals(expr, druidExpr));
    }

}
