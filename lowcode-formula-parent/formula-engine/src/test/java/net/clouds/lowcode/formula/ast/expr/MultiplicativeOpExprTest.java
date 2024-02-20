package net.clouds.lowcode.formula.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.parser.SQLExprParser;
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
 * 乘除法解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class MultiplicativeOpExprTest {

    private final String MUL_INTEGER1 = "1 * 2";

    private final String MUL_INTEGER2 = "1 * 2 / 3";

    private final String MUL_INTEGER3 = "1 / 2 * 3";

    private final String MUL_INTEGER4 = "2 / (2 - 3)";

    private final String MUL_INTEGER5 = "(2 / 4)";

    private final String MUL_INTEGER6 = "(2 / 4 % 3)";

    @Test
    public void testMul1() {
        FxExprParser parser = new FxExprParser(MUL_INTEGER1);
        FxExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.MULTIPLY);
    }

    @Test
    public void testMulInteger2() {
        FxExprParser parser = new FxExprParser(MUL_INTEGER2);
        FxExpr expr = parser.multiplicative();

        // 从左到右结合
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.DIVIDE);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExprL = (FxBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getOperator() == FxBinaryOperator.MULTIPLY);
    }

    @Test
    public void testMulInteger3() {
        FxExprParser parser = new FxExprParser(MUL_INTEGER3);
        FxExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.MULTIPLY);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExprL = (FxBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getOperator() == FxBinaryOperator.DIVIDE);
    }

    @Test
    public void testMulInteger4() {
        FxExprParser parser = new FxExprParser(MUL_INTEGER4);
        FxExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof FxIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof FxBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.DIVIDE);
        FxBinaryOpExpr binaryOpExprR = (FxBinaryOpExpr) binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR.isParenthesized());
        Assert.assertTrue(binaryOpExprR.getOperator() == FxBinaryOperator.SUBTRACT);
    }

    @Test
    public void testMulInteger5() {
        FxExprParser parser = new FxExprParser(MUL_INTEGER5);
        FxExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.isParenthesized());
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.DIVIDE);
    }

    @Test
    public void testMulInteger6() {
        SQLExprParser p = new SQLExprParser(MUL_INTEGER6);
        SQLExpr e = p.multiplicative();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.Modulus);

        FxExprParser parser = new FxExprParser(MUL_INTEGER6);
        FxExpr expr = parser.multiplicative();
        Assert.assertTrue(expr instanceof FxBinaryOpExpr);
        FxBinaryOpExpr binaryOpExpr = (FxBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.isParenthesized());
        Assert.assertTrue(binaryOpExpr.getOperator() == FxBinaryOperator.MODULUS);
    }
}
