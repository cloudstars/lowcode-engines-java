package net.cf.form.engine.repository.ast.expr;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
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
        OqlExprParser parser = new OqlExprParser(ADD_INTEGER1);
        QqlExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Add);
    }

    @Test
    public void testAddInteger2() {
        OqlExprParser parser = new OqlExprParser(ADD_INTEGER2);
        QqlExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Add);
    }

    @Test
    public void testAddInteger3() {
        OqlExprParser parser = new OqlExprParser(ADD_INTEGER3);
        QqlExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Subtract);
    }

    @Test
    public void testAddInteger4() {
        OqlExprParser parser = new OqlExprParser(ADD_INTEGER4);
        QqlExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Subtract);
        Assert.assertTrue(((OqlBinaryOpExpr) binaryOpExpr.getRight()).isParenthesized());
    }

    @Test
    public void testAddInteger5() {
        OqlExprParser parser = new OqlExprParser(ADD_INTEGER5);
        QqlExpr expr = parser.additive();

        Assert.assertTrue(expr instanceof OqlIntegerExpr);
        Assert.assertTrue(((OqlIntegerExpr) expr).getValue() == 1);
    }
}
