package net.clouds.lowcode.formula.ast.expr;

import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.literal.FxIntegerExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOpExpr;
import net.cf.formula.engine.ast.expr.operation.FxBinaryOperator;
import net.cf.formula.engine.parser.FxExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 加减法解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class AdditiveOpExprTest {

    private final String ADD_INTEGER1 = "1 + 2";

    private final String ADD_INTEGER2 = "1 + 2 + 3";

    private final String ADD_INTEGER3 = "1 + 2 - 3";

    private final String ADD_INTEGER4 = "1 - (2 - 3)";

    private final String ADD_INTEGER5 = "(1)";


    @Test
    public void testAddInteger1() {
        FxExprParser parser = new FxExprParser(ADD_INTEGER1);
        FxExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.ADD);
    }

    @Test
    public void testAddInteger2() {
        FxExprParser parser = new FxExprParser(ADD_INTEGER2);
        FxExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.ADD);
    }

    @Test
    public void testAddInteger3() {
        FxExprParser parser = new FxExprParser(ADD_INTEGER3);
        FxExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.SUBTRACT);
    }

    @Test
    public void testAddInteger4() {
        FxExprParser parser = new FxExprParser(ADD_INTEGER4);
        FxExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.SUBTRACT);
        Assert.assertTrue(((FxBinaryOpExpr) binaryOpExpr.getRight()).isParenthesized());
    }

    @Test
    public void testAddInteger5() {
        FxExprParser parser = new FxExprParser(ADD_INTEGER5);
        FxExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof FxIntegerExpr);
        Assert.assertTrue(((FxIntegerExpr) expr).getValue() == 1);
    }
}
