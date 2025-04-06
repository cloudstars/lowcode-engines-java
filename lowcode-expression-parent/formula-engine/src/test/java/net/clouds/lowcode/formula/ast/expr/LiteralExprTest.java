package net.clouds.lowcode.formula.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.literal.*;
import net.cf.formula.engine.parser.FxExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * 字面量解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class LiteralExprTest {

    public final String LITERAL_CHAR0 = "\'abc\'";

    public final String LITERAL_CHAR1 = "\'123\\'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\'";

    public final String LITERAL_CHAR2 = "\'abc\\'ab\\'\'";

    public static final String LITERAL_INTEGER1 = "123";

    public static final String LITERAL_INTEGER2 = "-123";


    public final String LITERAL_FLOAT1 = "123.456";

    public final String LITERAL_FLOAT5 = "-123.456";

    public final String LITERAL_FLOAT2 = ".123";

    public final String LITERAL_FLOAT3 = "0.123";

    public final String LITERAL_FLOAT4 = "1.";

    public final String LITERAL_TRUE = "true";

    public final String LITERAL_FALSE = "false";

    public final String LITERAL_NULL = "null";

    @Test
    public void testChar0() {
        SQLExprParser p = new SQLExprParser(LITERAL_CHAR0);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLCharExpr);
        Assert.assertTrue("abc".equals(((SQLCharExpr) e).getText()));

        FxExprParser parser = new FxExprParser(LITERAL_CHAR0);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxCharExpr);
        Assert.assertTrue("abc".equals(((FxCharExpr) expr).getText()));
    }

    @Test
    public void testChar1() {
        SQLExprParser p = new SQLExprParser(LITERAL_CHAR1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLCharExpr);
        Assert.assertTrue("123\\".equals(((SQLCharExpr) e).getText()));

        FxExprParser parser = new FxExprParser(LITERAL_CHAR1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxCharExpr);
        Assert.assertTrue("123'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".equals(((FxCharExpr) expr).getText()));
    }


    @Test
    public void testChar2() {
        SQLExprParser p = new SQLExprParser(LITERAL_CHAR2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLCharExpr);
        Assert.assertTrue("abc\\".equals(((SQLCharExpr) e).getText()));

        FxExprParser parser = new FxExprParser(LITERAL_CHAR2);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxCharExpr);
        Assert.assertTrue("abc'ab'".equals(((FxCharExpr) expr).getValue()));
    }


    @Test
    public void testInteger1() {
        SQLExprParser p = new SQLExprParser(LITERAL_INTEGER1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIntegerExpr);
        Assert.assertTrue(123 == ((SQLIntegerExpr) e).getNumber().intValue());

        FxExprParser parser = new FxExprParser(LITERAL_INTEGER1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxIntegerExpr);
        Assert.assertTrue(123 == ((FxIntegerExpr) expr).getValue());
    }

    @Test
    public void testInteger2() {
        SQLExprParser p = new SQLExprParser(LITERAL_INTEGER2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIntegerExpr);
        Assert.assertTrue(-123 == ((SQLIntegerExpr) e).getNumber().intValue());

        FxExprParser parser = new FxExprParser(LITERAL_INTEGER2);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxIntegerExpr);
        Assert.assertTrue(-123 == ((FxIntegerExpr) expr).getValue());
    }
    @Test
    public void testFloat1() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(123.456f == ((SQLNumberExpr) e).getNumber().floatValue());


        FxExprParser parser = new FxExprParser(LITERAL_FLOAT1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxNumberExpr);
        Assert.assertTrue(123.456f == ((FxNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testFloat5() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT5);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(-123.456f == ((SQLNumberExpr) e).getNumber().floatValue());


        FxExprParser parser = new FxExprParser(LITERAL_FLOAT5);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxNumberExpr);
        Assert.assertTrue(-123.456f == ((FxNumberExpr) expr).getValue().floatValue());
    }


    @Test
    public void testFloat2() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(.123f == ((SQLNumberExpr) e).getNumber().floatValue());

        FxExprParser parser = new FxExprParser(LITERAL_FLOAT2);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxNumberExpr);
        Assert.assertTrue(.123f == ((FxNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testFloat3() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT3);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(0.123f == ((SQLNumberExpr) e).getNumber().floatValue());

        FxExprParser parser = new FxExprParser(LITERAL_FLOAT3);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxNumberExpr);
        Assert.assertTrue(0.123f == ((FxNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testFloat4() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT4);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(1.0 == ((SQLNumberExpr) e).getNumber().doubleValue());

        FxExprParser parser = new FxExprParser(LITERAL_FLOAT4);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxNumberExpr);
        Assert.assertTrue(1.0 == ((FxNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testTrue() {
        SQLExprParser p = new SQLExprParser(LITERAL_TRUE);
        SQLExpr e = p.primary();
        // Druid 的 SQL 不支持 true / false
        Assert.assertTrue(e instanceof SQLIdentifierExpr);

        FxExprParser parser = new FxExprParser(LITERAL_TRUE);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxBooleanExpr);
        Assert.assertTrue(((FxBooleanExpr) expr).getValue() == true);
    }

    @Test
    public void testFalse() {
        SQLExprParser p = new SQLExprParser(LITERAL_FALSE);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIdentifierExpr);

        FxExprParser parser = new FxExprParser(LITERAL_FALSE);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxBooleanExpr);
        Assert.assertTrue(((FxBooleanExpr) expr).getValue() == false);
    }

    @Test
    public void testNull() {
        SQLExprParser p = new SQLExprParser(LITERAL_NULL);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNullExpr);

        FxExprParser parser = new FxExprParser(LITERAL_NULL);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxNullExpr);
    }

}
