package net.clouds.lowcode.formula.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxMethodInvokeExpr;
import net.cf.formula.engine.ast.expr.identifier.FxPropertyExpr;
import net.cf.formula.engine.ast.expr.literal.FxIntegerExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOperator;
import net.cf.formula.engine.parser.FxExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 表达式测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class ExprTest {

    private final static String EXPR1 = "(1 + 2) * 3.1 + 5 * (2 - 1.1)";

    private final static String EXPR2 = "1 + 2 * 3 + 3 * 4 * 5 - 6";

    private final static String IDENTIFIER_1 = "(aa1 + 1.1) * 2 + aa2 / (b - 4)";

    private final static String PROPERTY_1 = "(o1.f1 + 1.1) * 2 + o2.f2 / (o3.o4.f1 - 4)";

    private final static String METHOD_1 = "1 + max(detail.f1, 1)";


    @Test
    public void testExpr1() {
        SQLExprParser p = new SQLExprParser(EXPR1);
        SQLExpr e = p.expr();

        FxExprParser parser = new FxExprParser(EXPR1);
        FxExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.ADD);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxBinaryOpExpr);

        FxBinaryOpExpr binaryOpExprL = (FxBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getOperator() == FxBinaryOperator.MULTIPLY);

        FxBinaryOpExpr binaryOpExprR = (FxBinaryOpExpr) binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR.getOperator() == FxBinaryOperator.MULTIPLY);
    }

    @Test
    public void testExpr2() {
        SQLExprParser p = new SQLExprParser(EXPR2);
        SQLExpr e = p.expr();

        FxExprParser parser = new FxExprParser(EXPR2);
        FxExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.SUBTRACT);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxIntegerExpr);

        FxBinaryOpExpr binaryOpExprL = (FxBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getLeft() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExprL.getRight() instanceof FxBinaryOpExpr);

        FxBinaryOpExpr binaryOpExprLL = (FxBinaryOpExpr) binaryOpExprL.getLeft();
        Assert.assertTrue(binaryOpExprLL.getOperator() == FxBinaryOperator.ADD);

        FxBinaryOpExpr binaryOpExprLR = (FxBinaryOpExpr) binaryOpExprL.getRight();
        Assert.assertTrue(binaryOpExprLR.getOperator() == FxBinaryOperator.MULTIPLY);
    }

    @Test
    public void testIdentifier1() {
        SQLExprParser sp = new SQLExprParser(IDENTIFIER_1);
        SQLExpr se = sp.expr();

        FxExprParser fp = new FxExprParser(IDENTIFIER_1);
        FxExpr fe = fp.expr();
        Assert.assertTrue(fe instanceof FxBinaryOpExpr);
        FxBinaryOpExpr boe = (FxBinaryOpExpr) fe;
        Assert.assertTrue(boe.getOperator() == FxBinaryOperator.ADD);

        Assert.assertTrue(boe.getLeft() instanceof FxBinaryOpExpr);
        FxBinaryOpExpr boel = (FxBinaryOpExpr) boe.getLeft();
        Assert.assertTrue(boel.getLeft() instanceof FxBinaryOpExpr);
        FxBinaryOpExpr boell = (FxBinaryOpExpr) boel.getLeft();
        Assert.assertTrue(boell.getOperator() == FxBinaryOperator.ADD);
        Assert.assertTrue(boell.getLeft() instanceof FxIdentifierExpr);
        FxIdentifierExpr boelll = (FxIdentifierExpr) boell.getLeft();
        Assert.assertTrue("aa1".equals(boelll.getName()));
    }

    @Test
    public void testProperty2() {
        SQLExprParser sp = new SQLExprParser(PROPERTY_1);
        SQLExpr se = sp.expr();

        FxExprParser fp = new FxExprParser(PROPERTY_1);
        FxExpr fe = fp.expr();

        Assert.assertTrue(fe instanceof FxBinaryOpExpr);
        FxBinaryOpExpr boe = (FxBinaryOpExpr) fe;
        Assert.assertTrue(boe.getOperator() == FxBinaryOperator.ADD);

        Assert.assertTrue(boe.getRight() instanceof FxBinaryOpExpr);
        FxBinaryOpExpr boeR = (FxBinaryOpExpr) boe.getRight();
        Assert.assertTrue(boeR.getLeft() instanceof FxPropertyExpr);
        FxPropertyExpr boeRL = (FxPropertyExpr) boeR.getLeft();
        Assert.assertTrue("f2".equals(boeRL.getName()));
    }

    @Test
    public void testMethod1() {
        SQLExprParser sp = new SQLExprParser(METHOD_1);
        SQLExpr se = sp.expr();

        FxExprParser fp = new FxExprParser(METHOD_1);
        FxExpr fe = fp.expr();

        Assert.assertTrue(fe instanceof FxBinaryOpExpr);
        FxBinaryOpExpr boe = (FxBinaryOpExpr) fe;
        Assert.assertTrue(boe.getOperator() == FxBinaryOperator.ADD);

        Assert.assertTrue(boe.getRight() instanceof FxMethodInvokeExpr);
        FxMethodInvokeExpr boeR = (FxMethodInvokeExpr) boe.getRight();
        Assert.assertTrue("max".equalsIgnoreCase(boeR.getMethodName()));
        Assert.assertTrue(boeR.getArguments().size() == 2);
    }

}
