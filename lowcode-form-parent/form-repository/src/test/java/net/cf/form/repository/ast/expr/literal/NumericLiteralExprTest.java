package net.cf.form.repository.ast.expr.literal;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.expr.SQLNumberExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlIntegerExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlNumberExpr;
import net.cf.form.repository.sql.parser.SqlExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * 数字类型字面量解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class NumericLiteralExprTest {


    public final String INTEGER1 = "123";

    public final String INTEGER2 = "-123";


    public final String NUMBER1 = "123.456";

    public final String NUMBER2 = "-123.456";

    public final String NUMBER3 = ".123";

    public final String NUMBER4 = "0.123";

    public final String NUMBER5 = "1.";


    @Test
    public void testInteger1() {
        SQLExprParser p = new SQLExprParser(INTEGER1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIntegerExpr);
        Assert.assertTrue(123 == ((SQLIntegerExpr) e).getNumber().intValue());

        SqlExprParser parser = new SqlExprParser(INTEGER1);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlIntegerExpr);
        Assert.assertTrue(123 == ((SqlIntegerExpr) expr).getValue());
    }

    @Test
    public void testInteger2() {
        SQLExprParser p = new SQLExprParser(INTEGER2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIntegerExpr);
        Assert.assertTrue(-123 == ((SQLIntegerExpr) e).getNumber().intValue());

        SqlExprParser parser = new SqlExprParser(INTEGER2);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlIntegerExpr);
        Assert.assertTrue(-123 == ((SqlIntegerExpr) expr).getValue());
    }
    @Test
    public void testNumber1() {
        SQLExprParser p = new SQLExprParser(NUMBER1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(123.456f == ((SQLNumberExpr) e).getNumber().floatValue());


        SqlExprParser parser = new SqlExprParser(NUMBER1);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlNumberExpr);
        Assert.assertTrue(123.456f == ((SqlNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testNumber2() {
        SQLExprParser p = new SQLExprParser(NUMBER2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(-123.456f == ((SQLNumberExpr) e).getNumber().floatValue());

        SqlExprParser parser = new SqlExprParser(NUMBER2);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlNumberExpr);
        Assert.assertTrue(-123.456f == ((SqlNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testNumber3() {
        SQLExprParser p = new SQLExprParser(NUMBER3);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(0.123f == ((SQLNumberExpr) e).getNumber().floatValue());

        SqlExprParser parser = new SqlExprParser(NUMBER3);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlNumberExpr);
        Assert.assertTrue(0.123f == ((SqlNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testNumber4() {
        SQLExprParser p = new SQLExprParser(NUMBER4);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(0.123d == ((SQLNumberExpr) e).getNumber().doubleValue());

        SqlExprParser parser = new SqlExprParser(NUMBER4);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlNumberExpr);
        Assert.assertTrue(0.123d == ((SqlNumberExpr) expr).getNumber().doubleValue());
    }

    @Test
    public void testNumber5() {
        SQLExprParser p = new SQLExprParser(NUMBER5);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(1.f == ((SQLNumberExpr) e).getNumber().floatValue());


        SqlExprParser parser = new SqlExprParser(NUMBER5);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlNumberExpr);
        Assert.assertTrue(1.f == ((SqlNumberExpr) expr).getValue().floatValue());
    }

}
