package net.clouds.lowcode.formula.ast.expr;


import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOperator;
import net.cf.formula.engine.ast.expr.operation.FxUnaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxUnaryOperator;
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
public class LogicOpExprTest {

    private final static String EXPR1 = "a == 1 and b == 2.0";

    private final static String EXPR2 = "b != 1.0 && b != '33'";

    private final static String EXPR3 = "c < 0.0 or b.y == 33.331";

    private final static String EXPR4 = "c < 0.0 || b.y == 33.331";

    private final static String EXPR5 = "!(a < 1)";

    private final static String EXPR6 = "not(o.a.b > -1)";

    private final static String EXPR7 = "(o.a >= o.b) or b == 1 and c == 2";

    // and 与 || 混用
    private final static String EXPR8 = "1 <= 1 and (!(2 > 2) || 3 != 3)";

    // 这是一个很复杂的表达式,含了各种运算,并且省掉了所有的可省的空格
    private final static String EXPR9 = "(a>1)or b<1&& c<5*9+4";


    @Test
    public void testExpr1() {
        SQLExprParser p = new SQLExprParser(EXPR1);
        SQLExpr e = p.and();
        // Druid SQL中不支持 ==
        Assert.assertTrue(e instanceof SQLIdentifierExpr);

        FxExprParser exprParser = new FxExprParser(EXPR1);
        FxExpr expr = exprParser.and();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.AND);
    }

    @Test
    public void testExpr2() {
        SQLExprParser p = new SQLExprParser(EXPR2);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        // Druid SQL 不支持 &&
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.NotEqual);

        FxExprParser exprParser = new FxExprParser(EXPR2);
        FxExpr expr = exprParser.and();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.AND);
    }

    @Test
    public void testExpr3() {
        SQLExprParser p = new SQLExprParser(EXPR3);
        SQLExpr e = p.or();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanOr);

        FxExprParser exprParser = new FxExprParser(EXPR3);
        FxExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.OR);
    }

    @Test
    public void testExpr4() {
        SQLExprParser p = new SQLExprParser(EXPR4);
        SQLExpr e = p.or();
        // Druid SQL 中 || 连接符号,优先级比 < 高
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        SQLExpr er = ((SQLBinaryOpExpr) e).getRight();
        Assert.assertTrue(er instanceof SQLBinaryOpExpr);
        SQLBinaryOpExpr er0 = (SQLBinaryOpExpr) er;
        Assert.assertTrue(er0.getOperator() == SQLBinaryOperator.Concat);

        FxExprParser exprParser = new FxExprParser(EXPR4);
        FxExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.OR);
    }

    @Test
    public void testExpr5() {
        SQLExprParser p = new SQLExprParser(EXPR5);
        SQLExpr e = p.expr();
        Assert.assertTrue(e instanceof SQLUnaryExpr);
        Assert.assertTrue(((SQLUnaryExpr) e).getOperator() == SQLUnaryOperator.Not);

        FxExprParser exprParser = new FxExprParser(EXPR5);
        FxExpr expr = exprParser.expr();
        Assert.assertTrue(expr instanceof FxUnaryOpExpr);
        FxUnaryOpExpr unaryOpExpr = (FxUnaryOpExpr) expr;
        Assert.assertTrue(unaryOpExpr.getOperator() == FxUnaryOperator.NOT);
    }

    @Test
    public void testExpr6() {
        SQLExprParser p = new SQLExprParser(EXPR6);
        SQLExpr e = p.expr();
        Assert.assertTrue(e instanceof SQLNotExpr);

        FxExprParser exprParser = new FxExprParser(EXPR6);
        FxExpr expr = exprParser.expr();
        Assert.assertTrue(expr instanceof FxUnaryOpExpr);
        FxUnaryOpExpr unaryOpExpr = (FxUnaryOpExpr) expr;
        Assert.assertTrue(unaryOpExpr.getOperator() == FxUnaryOperator.NOT);
    }

    @Test
    public void testExpr7() {
        SQLExprParser p = new SQLExprParser(EXPR7);
        SQLExpr e = p.or();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanOr);

        FxExprParser exprParser = new FxExprParser(EXPR7);
        FxExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.OR);
        FxExpr binaryOpExprR = binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExprR0 = (FxBinaryOpExpr) binaryOpExprR;
        Assert.assertTrue(binaryOpExprR0.getOperator() == FxBinaryOperator.AND);
    }

    @Test
    public void testExpr8() {
        SQLExprParser p = new SQLExprParser(EXPR8);
        SQLExpr e = p.and();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanAnd);

        FxExprParser exprParser = new FxExprParser(EXPR8);
        FxExpr expr = exprParser.and();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.AND);
        FxExpr binaryOpExprR = binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExprR0 = (FxBinaryOpExpr) binaryOpExprR;
        Assert.assertTrue(binaryOpExprR0.getOperator() == FxBinaryOperator.OR);
        Assert.assertTrue(binaryOpExprR0.getLeft() instanceof FxUnaryOpExpr);
        FxUnaryOpExpr binaryOpExprRL = (FxUnaryOpExpr) binaryOpExprR0.getLeft();
        Assert.assertTrue(binaryOpExprRL.getOperator() == FxUnaryOperator.NOT);
    }

    @Test
    public void testExpr9() {
        SQLExprParser p = new SQLExprParser(EXPR9);
        SQLExpr e = p.or();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanOr);

        FxExprParser exprParser = new FxExprParser(EXPR9);
        FxExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.OR);
        FxExpr binaryOpExprR = binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExprR0 = (FxBinaryOpExpr) binaryOpExprR;
        Assert.assertTrue(binaryOpExprR0.getOperator() == FxBinaryOperator.AND);
        Assert.assertTrue(binaryOpExprR0.getRight() instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExprRR = (FxBinaryOpExpr) binaryOpExprR0.getRight();
        Assert.assertTrue(binaryOpExprRR.getOperator() == FxBinaryOperator.LESS_THAN);
    }

}
