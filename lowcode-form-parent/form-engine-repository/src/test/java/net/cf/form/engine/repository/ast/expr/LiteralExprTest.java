package net.cf.form.engine.repository.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
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

    public final String LITERAL_CHAR0 = "'abc'";

    public final String LITERAL_CHAR1 = "'123''abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'";

    public final String LITERAL_CHAR2 = "'abc''ab'''";

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

        OqlExprParser parser = new OqlExprParser(LITERAL_CHAR0);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlCharExpr);
        Assert.assertTrue("abc".equals(((OqlCharExpr) expr).getText()));
    }

    @Test
    public void testChar1() {
        SQLExprParser p = new SQLExprParser(LITERAL_CHAR1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLCharExpr);
        Assert.assertTrue("123'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".equals(((SQLCharExpr) e).getText()));

        OqlExprParser parser = new OqlExprParser(LITERAL_CHAR1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlCharExpr);
        Assert.assertTrue("123'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".equals(((OqlCharExpr) expr).getText()));
    }


    @Test
    public void testChar2() {
        SQLExprParser p = new SQLExprParser(LITERAL_CHAR2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLCharExpr);
        Assert.assertTrue("abc'ab'".equals(((SQLCharExpr) e).getText()));

        OqlExprParser parser = new OqlExprParser(LITERAL_CHAR2);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlCharExpr);
        Assert.assertTrue("abc'ab'".equals(((OqlCharExpr) expr).getValue()));
    }


    @Test
    public void testInteger1() {
        SQLExprParser p = new SQLExprParser(LITERAL_INTEGER1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIntegerExpr);
        Assert.assertTrue(123 == ((SQLIntegerExpr) e).getNumber().intValue());

        OqlExprParser parser = new OqlExprParser(LITERAL_INTEGER1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlIntegerExpr);
        Assert.assertTrue(123 == ((OqlIntegerExpr) expr).getValue());
    }

    @Test
    public void testInteger2() {
        SQLExprParser p = new SQLExprParser(LITERAL_INTEGER2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIntegerExpr);
        Assert.assertTrue(-123 == ((SQLIntegerExpr) e).getNumber().intValue());

        OqlExprParser parser = new OqlExprParser(LITERAL_INTEGER2);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlIntegerExpr);
        Assert.assertTrue(-123 == ((OqlIntegerExpr) expr).getValue());
    }
    @Test
    public void testFloat1() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(123.456f == ((SQLNumberExpr) e).getNumber().floatValue());


        OqlExprParser parser = new OqlExprParser(LITERAL_FLOAT1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlNumberExpr);
        Assert.assertTrue(123.456f == ((OqlNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testFloat5() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT5);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(-123.456f == ((SQLNumberExpr) e).getNumber().floatValue());


        OqlExprParser parser = new OqlExprParser(LITERAL_FLOAT5);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlNumberExpr);
        Assert.assertTrue(-123.456f == ((OqlNumberExpr) expr).getValue().floatValue());
    }


    @Test
    public void testFloat2() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(.123f == ((SQLNumberExpr) e).getNumber().floatValue());

        OqlExprParser parser = new OqlExprParser(LITERAL_FLOAT2);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlNumberExpr);
        Assert.assertTrue(.123f == ((OqlNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testFloat3() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT3);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(0.123f == ((SQLNumberExpr) e).getNumber().floatValue());

        OqlExprParser parser = new OqlExprParser(LITERAL_FLOAT3);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlNumberExpr);
        Assert.assertTrue(0.123f == ((OqlNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testFloat4() {
        SQLExprParser p = new SQLExprParser(LITERAL_FLOAT4);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNumberExpr);
        Assert.assertTrue(1.0 == ((SQLNumberExpr) e).getNumber().doubleValue());

        OqlExprParser parser = new OqlExprParser(LITERAL_FLOAT4);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlNumberExpr);
        Assert.assertTrue(1.0 == ((OqlNumberExpr) expr).getValue().floatValue());
    }

    @Test
    public void testTrue() {
        SQLExprParser p = new SQLExprParser(LITERAL_TRUE);
        SQLExpr e = p.primary();
        // Druid 的 SQL 不支持 true / false
        Assert.assertTrue(e instanceof SQLIdentifierExpr);

        OqlExprParser parser = new OqlExprParser(LITERAL_TRUE);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlBooleanExpr);
        Assert.assertTrue(((OqlBooleanExpr) expr).getValue() == true);
    }

    @Test
    public void testFalse() {
        SQLExprParser p = new SQLExprParser(LITERAL_FALSE);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLIdentifierExpr);

        OqlExprParser parser = new OqlExprParser(LITERAL_FALSE);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlBooleanExpr);
        Assert.assertTrue(((OqlBooleanExpr) expr).getValue() == false);
    }

    @Test
    public void testNull() {
        SQLExprParser p = new SQLExprParser(LITERAL_NULL);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNullExpr);

        OqlExprParser parser = new OqlExprParser(LITERAL_NULL);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlNullExpr);
    }

}
