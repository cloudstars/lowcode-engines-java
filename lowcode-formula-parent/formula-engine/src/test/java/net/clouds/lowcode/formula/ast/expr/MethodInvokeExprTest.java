package net.clouds.lowcode.formula.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxMethodInvokeExpr;
import net.cf.formula.engine.ast.expr.literal.FxIntegerExpr;
import net.cf.formula.engine.parser.FxExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;


/**
 * 方法调用解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class MethodInvokeExprTest {

    public final String METHOD_INVOKE1 = "now()";

    public final String METHOD_INVOKE2 = "max(1, a)";

    public final String METHOD_INVOKE3 = "max(-1, 3)";

    @Test
    public void testMethodInvoke1() {
        SQLExprParser p = new SQLExprParser(METHOD_INVOKE1);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLMethodInvokeExpr);
        Assert.assertTrue("now".equalsIgnoreCase(((SQLMethodInvokeExpr) e).getMethodName()));

        FxExprParser parser = new FxExprParser(METHOD_INVOKE1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxMethodInvokeExpr);
        Assert.assertTrue("now".equalsIgnoreCase(((FxMethodInvokeExpr) expr).getMethodName()));
    }

    @Test
    public void testMethodInvoke2() {
        SQLExprParser p = new SQLExprParser(METHOD_INVOKE2);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLMethodInvokeExpr);
        SQLMethodInvokeExpr mie = (SQLMethodInvokeExpr) e;
        Assert.assertTrue("max".equalsIgnoreCase(mie.getMethodName()));
        List<SQLExpr> mieArgs = mie.getArguments();
        Assert.assertTrue(mieArgs.size() == 2);
        Assert.assertTrue(mieArgs.get(0) instanceof SQLIntegerExpr);
        Assert.assertTrue(mieArgs.get(1) instanceof SQLIdentifierExpr);

        FxExprParser parser = new FxExprParser(METHOD_INVOKE2);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxMethodInvokeExpr);
        FxMethodInvokeExpr fmie = (FxMethodInvokeExpr) expr;
        Assert.assertTrue("max".equalsIgnoreCase(fmie.getMethodName()));
        List<FxExpr> fmieArgs = fmie.getArguments();
        Assert.assertTrue(fmieArgs.size() == 2);
        Assert.assertTrue(fmieArgs.get(0) instanceof FxIntegerExpr);
        Assert.assertTrue(fmieArgs.get(1) instanceof FxIdentifierExpr);
    }

    @Test
    public void testMethodInvoke3() {
        SQLExprParser p = new SQLExprParser(METHOD_INVOKE3);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLMethodInvokeExpr);
        SQLMethodInvokeExpr mie = (SQLMethodInvokeExpr) e;
        Assert.assertTrue("max".equalsIgnoreCase(mie.getMethodName()));
        List<SQLExpr> mieArgs = mie.getArguments();
        Assert.assertTrue(mieArgs.size() == 2);
        Assert.assertTrue(mieArgs.get(0) instanceof SQLIntegerExpr);
        Assert.assertTrue(mieArgs.get(1) instanceof SQLIntegerExpr);

        FxExprParser parser = new FxExprParser(METHOD_INVOKE3);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxMethodInvokeExpr);
        FxMethodInvokeExpr fmie = (FxMethodInvokeExpr) expr;
        Assert.assertTrue("max".equalsIgnoreCase(fmie.getMethodName()));
        List<FxExpr> fmieArgs = fmie.getArguments();
        Assert.assertTrue(fmieArgs.size() == 2);
        Assert.assertTrue(fmieArgs.get(0) instanceof FxIntegerExpr);
        Assert.assertTrue(fmieArgs.get(1) instanceof FxIntegerExpr);
    }
}
