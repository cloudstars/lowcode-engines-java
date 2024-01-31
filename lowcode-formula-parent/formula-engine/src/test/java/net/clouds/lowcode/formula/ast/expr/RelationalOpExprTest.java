package net.clouds.lowcode.formula.ast.expr;


import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOperator;
import net.cf.formula.engine.parser.FxExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 关系操作表达式测试
 *
 * @author clouds
 *
 */
@RunWith(JUnit4.class)
public class RelationalOpExprTest {

    private final static String EXPR1 = "a == 1";

    private final static String EXPR2 = "b != 1.0";

    private final static String EXPR3 = "c < 0.0";

    private final static String EXPR4 = "o.a <= -1";

    private final static String EXPR5 = "o.a.b > -1";

    private final static String EXPR6 = "o.a >= o.b";

    private final static String EXPR7 = "1 == 1";

    @Test
    public void testExpr1() {
        SQLExprParser p = new SQLExprParser(EXPR1);
        SQLExpr e = p.relational();
        // SQL中不支持 ==
        Assert.assertTrue(e instanceof SQLIdentifierExpr);

        FxExprParser exprParser = new FxExprParser(EXPR1);
        FxExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.Equal);
    }

    @Test
    public void testExpr2() {
        SQLExprParser p = new SQLExprParser(EXPR2);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.NotEqual);

        FxExprParser exprParser = new FxExprParser(EXPR2);
        FxExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.NotEqual);
    }

    @Test
    public void testExpr3() {
        SQLExprParser p = new SQLExprParser(EXPR3);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.LessThan);

        FxExprParser exprParser = new FxExprParser(EXPR3);
        FxExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.LessThan);
    }

    @Test
    public void testExpr4() {
        SQLExprParser p = new SQLExprParser(EXPR4);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.LessThanOrEqual);

        FxExprParser exprParser = new FxExprParser(EXPR4);
        FxExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.LessThanOrEqual);
    }

    @Test
    public void testExpr5() {
        SQLExprParser p = new SQLExprParser(EXPR5);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.GreaterThan);

        FxExprParser exprParser = new FxExprParser(EXPR5);
        FxExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.GreaterThan);
    }

    @Test
    public void testExpr6() {
        SQLExprParser p = new SQLExprParser(EXPR6);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.GreaterThanOrEqual);

        FxExprParser exprParser = new FxExprParser(EXPR6);
        FxExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.GreaterThanOrEqual);
    }


    @Test
    public void testExpr7() {
        SQLExprParser p = new SQLExprParser(EXPR7);
        SQLExpr e = p.relational();
        // SQL中不支持 ==
        Assert.assertTrue(e instanceof SQLIntegerExpr);

        FxExprParser exprParser = new FxExprParser(EXPR7);
        FxExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.Equal);
    }

}
