package net.cf.form.engine.repository.ast.expr;


import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.*;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlNotExpr;
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

    private final static String EXPR1 = "a = 1 and b = 2.0";

    private final static String EXPR2 = "b != 1.0 && b != '33'";

    private final static String EXPR3 = "c < 0.0 or b.y = 33.331";

    private final static String EXPR4 = "c < 0.0 || b.y = 33.331";

    private final static String EXPR5 = "!(a < 1)";

    private final static String EXPR6 = "not(o.a.b > -1)";

    private final static String EXPR7 = "(o.a >= o.b) or b = 1 and c = 2";

    // and 与 || 混用
    private final static String EXPR8 = "1 <= 1 and (!(2 > 2) || 3 != 3)";

    // 这是一个很复杂的表达式,含了各种运算,并且省掉了所有的可省的空格
    private final static String EXPR9 = "(a>1)or b<1&& c<5*9+4";


    @Test
    public void testExpr1() {
        SQLExprParser p = new SQLExprParser(EXPR1);
        SQLExpr e = p.and();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        SQLBinaryOpExpr boe = (SQLBinaryOpExpr) e;
        Assert.assertTrue(boe.getOperator() == SQLBinaryOperator.BooleanAnd);

        OqlExprParser exprParser = new OqlExprParser(EXPR1);
        QqlExpr expr = exprParser.and();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.And);
    }

    @Test
    public void testExpr2() {
        SQLExprParser p = new SQLExprParser(EXPR2);
        SQLExpr e = p.relational();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        // Druid SQL 不支持 &&
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.NotEqual);

        OqlExprParser exprParser = new OqlExprParser(EXPR2);
        QqlExpr expr = exprParser.and();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.And);
    }

    @Test
    public void testExpr3() {
        SQLExprParser p = new SQLExprParser(EXPR3);
        SQLExpr e = p.or();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanOr);

        OqlExprParser exprParser = new OqlExprParser(EXPR3);
        QqlExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Or);
    }

    @Test
    public void testExpr4() {
        SQLExprParser p = new SQLExprParser(EXPR4);
        SQLExpr e = p.or();
        // Druid SQL 中 || 连接符号,优先级比 < 高
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        SQLExpr er = ((SQLBinaryOpExpr) e).getLeft();
        Assert.assertTrue(er instanceof SQLBinaryOpExpr);
        SQLBinaryOpExpr er0 = (SQLBinaryOpExpr) er;
        Assert.assertTrue(er0.getOperator() == SQLBinaryOperator.LessThan);

        OqlExprParser exprParser = new OqlExprParser(EXPR4);
        QqlExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Or);
    }

    @Test
    public void testExpr5() {
        SQLExprParser p = new SQLExprParser(EXPR5);
        SQLExpr e = p.expr();
        Assert.assertTrue(e instanceof SQLUnaryExpr);
        Assert.assertTrue(((SQLUnaryExpr) e).getOperator() == SQLUnaryOperator.Not);

        OqlExprParser exprParser = new OqlExprParser(EXPR5);
        QqlExpr expr = exprParser.expr();
        Assert.assertTrue(expr instanceof OqlNotExpr);
    }

    @Test
    public void testExpr6() {
        SQLExprParser p = new SQLExprParser(EXPR6);
        SQLExpr e = p.expr();
        Assert.assertTrue(e instanceof SQLNotExpr);

        OqlExprParser exprParser = new OqlExprParser(EXPR6);
        QqlExpr expr = exprParser.expr();
        Assert.assertTrue(expr instanceof OqlNotExpr);
    }

    @Test
    public void testExpr7() {
        SQLExprParser p = new SQLExprParser(EXPR7);
        SQLExpr e = p.or();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanOr);

        OqlExprParser exprParser = new OqlExprParser(EXPR7);
        QqlExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Or);
        QqlExpr binaryOpExprR = binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExprR0 = (OqlBinaryOpExpr) binaryOpExprR;
        Assert.assertTrue(binaryOpExprR0.getOperator() == OqlBinaryOperator.And);
    }

    @Test
    public void testExpr8() {
        SQLExprParser p = new SQLExprParser(EXPR8);
        SQLExpr e = p.and();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanAnd);

        OqlExprParser exprParser = new OqlExprParser(EXPR8);
        QqlExpr expr = exprParser.and();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.And);
        QqlExpr binaryOpExprR = binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExprR0 = (OqlBinaryOpExpr) binaryOpExprR;
        Assert.assertTrue(binaryOpExprR0.getOperator() == OqlBinaryOperator.Or);
        Assert.assertTrue(binaryOpExprR0.getLeft() instanceof OqlNotExpr);
    }

    @Test
    public void testExpr9() {
        SQLExprParser p = new SQLExprParser(EXPR9);
        SQLExpr e = p.or();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);
        Assert.assertTrue(((SQLBinaryOpExpr) e).getOperator() == SQLBinaryOperator.BooleanOr);

        OqlExprParser exprParser = new OqlExprParser(EXPR9);
        QqlExpr expr = exprParser.or();
        Assert.assertTrue(expr instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) expr;
        Assert.assertTrue(binaryOpExpr.getOperator() == OqlBinaryOperator.Or);
        QqlExpr binaryOpExprR = binaryOpExpr.getRight();
        Assert.assertTrue(binaryOpExprR instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExprR0 = (OqlBinaryOpExpr) binaryOpExprR;
        Assert.assertTrue(binaryOpExprR0.getOperator() == OqlBinaryOperator.And);
        Assert.assertTrue(binaryOpExprR0.getRight() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExprRR = (OqlBinaryOpExpr) binaryOpExprR0.getRight();
        Assert.assertTrue(binaryOpExprRR.getOperator() == OqlBinaryOperator.LessThan);
    }

}
