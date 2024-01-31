package net.cf.form.engine.repository.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOperator;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
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

    private final static String EXPR8 = "1 = 1";


    @Test
    public void testExpr1() {
        SQLExprParser p = new SQLExprParser(EXPR1);
        SQLExpr e = p.relational();
        // SQL中不支持 ==
        Assert.assertTrue(e instanceof SQLIdentifierExpr);

        OqlExprParser exprParser = new OqlExprParser(EXPR1);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Equal);
    }

    @Test
    public void testExpr2() {
        SQLExprParser p = new SQLExprParser(EXPR2);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.NotEqual);

        OqlExprParser exprParser = new OqlExprParser(EXPR2);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.NotEqual);
    }

    @Test
    public void testExpr3() {
        SQLExprParser p = new SQLExprParser(EXPR3);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.LessThan);

        OqlExprParser exprParser = new OqlExprParser(EXPR3);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.LessThan);
    }

    @Test
    public void testExpr4() {
        SQLExprParser p = new SQLExprParser(EXPR4);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.LessThanOrEqual);

        OqlExprParser exprParser = new OqlExprParser(EXPR4);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.LessThanOrEqual);
    }

    @Test
    public void testExpr5() {
        SQLExprParser p = new SQLExprParser(EXPR5);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.GreaterThan);

        OqlExprParser exprParser = new OqlExprParser(EXPR5);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.GreaterThan);
    }

    @Test
    public void testExpr6() {
        SQLExprParser p = new SQLExprParser(EXPR6);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.GreaterThanOrEqual);

        OqlExprParser exprParser = new OqlExprParser(EXPR6);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.GreaterThanOrEqual);
    }


    @Test
    public void testExpr7() {
        SQLExprParser p = new SQLExprParser(EXPR7);
        SQLExpr e = p.relational();
        // SQL中不支持 ==
        Assert.assertTrue(e instanceof SQLIntegerExpr);

        OqlExprParser exprParser = new OqlExprParser(EXPR7);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Equal);
    }

    @Test
    public void testExpr8() {
        SQLExprParser p = new SQLExprParser(EXPR8);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);

        OqlExprParser exprParser = new OqlExprParser(EXPR8);
        QqlExpr expr = exprParser.relational();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Equal);
    }

}
