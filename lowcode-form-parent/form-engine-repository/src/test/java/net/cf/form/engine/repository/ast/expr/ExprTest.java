package net.cf.form.engine.repository.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
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

        OqlExprParser parser = new OqlExprParser(EXPR1);
        QqlExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Add);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlBinaryOpExpr);

        OqlBinaryOpExpr binaryOpExprL = (OqlBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getOperator() == OqlBinaryOperator.Multiply);

        OqlBinaryOpExpr binaryOpExprR = (OqlBinaryOpExpr) binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR.getOperator() == OqlBinaryOperator.Multiply);
    }

    @Test
    public void testExpr2() {
        SQLExprParser p = new SQLExprParser(EXPR2);
        SQLExpr e = p.expr();

        OqlExprParser parser = new OqlExprParser(EXPR2);
        QqlExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Subtract);
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlIntegerExpr);

        OqlBinaryOpExpr binaryOpExprL = (OqlBinaryOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(binaryOpExprL.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExprL.getRight() instanceof OqlBinaryOpExpr);

        OqlBinaryOpExpr binaryOpExprLL = (OqlBinaryOpExpr) binaryOpExprL.getLeft();
        Assert.assertTrue(binaryOpExprLL.getOperator() == OqlBinaryOperator.Add);

        OqlBinaryOpExpr binaryOpExprLR = (OqlBinaryOpExpr) binaryOpExprL.getRight();
        Assert.assertTrue(binaryOpExprLR.getOperator() == OqlBinaryOperator.Multiply);
    }

    @Test
    public void testIdentifier1() {
        SQLExprParser sp = new SQLExprParser(IDENTIFIER_1);
        SQLExpr se = sp.expr();

        OqlExprParser fp = new OqlExprParser(IDENTIFIER_1);
        QqlExpr fe = fp.expr();
        Assert.assertTrue(fe instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boe = (OqlBinaryOpExpr) fe;
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.Add);

        Assert.assertTrue(boe.getLeft() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boel = (OqlBinaryOpExpr) boe.getLeft();
        Assert.assertTrue(boel.getLeft() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boell = (OqlBinaryOpExpr) boel.getLeft();
        Assert.assertTrue(boell.getOperator() == OqlBinaryOperator.Add);
        Assert.assertTrue(boell.getLeft() instanceof OqlIdentifierExpr);
        OqlIdentifierExpr boelll = (OqlIdentifierExpr) boell.getLeft();
        Assert.assertTrue("aa1".equals(boelll.getName()));
    }

    @Test
    public void testProperty2() {
        SQLExprParser sp = new SQLExprParser(PROPERTY_1);
        SQLExpr se = sp.expr();

        OqlExprParser fp = new OqlExprParser(PROPERTY_1);
        QqlExpr fe = fp.expr();

        Assert.assertTrue(fe instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boe = (OqlBinaryOpExpr) fe;
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.Add);

        Assert.assertTrue(boe.getRight() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boeR = (OqlBinaryOpExpr) boe.getRight();
        Assert.assertTrue(boeR.getLeft() instanceof OqlPropertyExpr);
        OqlPropertyExpr boeRL = (OqlPropertyExpr) boeR.getLeft();
        Assert.assertTrue("f2".equals(boeRL.getName()));
    }

    @Test
    public void testMethod1() {
        SQLExprParser sp = new SQLExprParser(METHOD_1);
        SQLExpr se = sp.expr();

        OqlExprParser fp = new OqlExprParser(METHOD_1);
        QqlExpr fe = fp.expr();

        Assert.assertTrue(fe instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boe = (OqlBinaryOpExpr) fe;
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.Add);

        Assert.assertTrue(boe.getRight() instanceof OqlMethodInvokeExpr);
        OqlMethodInvokeExpr boeR = (OqlMethodInvokeExpr) boe.getRight();
        Assert.assertTrue("max".equalsIgnoreCase(boeR.getMethodName()));
        Assert.assertTrue(boeR.getArguments().size() == 2);
    }

}
