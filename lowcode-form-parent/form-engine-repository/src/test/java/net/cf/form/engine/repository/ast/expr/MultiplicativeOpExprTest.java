package net.cf.form.engine.repository.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.parser.SQLExprParser;
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
        OqlExprParser parser = new OqlExprParser(MUL_INTEGER1);
        QqlExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Multiply);
    }

    @Test
    public void testMulInteger2() {
        OqlExprParser parser = new OqlExprParser(MUL_INTEGER2);
        QqlExpr expr = parser.multiplicative();

        // 从左到右结合
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Divide);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExprL = (OqlBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getOperator() == OqlBinaryOperator.Multiply);
    }

    @Test
    public void testMulInteger3() {
        OqlExprParser parser = new OqlExprParser(MUL_INTEGER3);
        QqlExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Multiply);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExprL = (OqlBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getOperator() == OqlBinaryOperator.Divide);
    }

    @Test
    public void testMulInteger4() {
        OqlExprParser parser = new OqlExprParser(MUL_INTEGER4);
        QqlExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlIntegerExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Divide);
        OqlBinaryOpExpr binaryOpExprR = (OqlBinaryOpExpr) binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR.isParenthesized());
        Assert.assertTrue(binaryOpExprR.getOperator() == OqlBinaryOperator.Subtract);
    }

    @Test
    public void testMulInteger5() {
        OqlExprParser parser = new OqlExprParser(MUL_INTEGER5);
        QqlExpr expr = parser.multiplicative();

        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.isParenthesized());
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Divide);
    }

    @Test
    public void testMulInteger6() {
        SQLExprParser p = new SQLExprParser(MUL_INTEGER6);
        SQLExpr e = p.multiplicative();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.Modulus);

        OqlExprParser parser = new OqlExprParser(MUL_INTEGER6);
        QqlExpr expr = parser.multiplicative();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.isParenthesized());
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Modulus);
    }
}
