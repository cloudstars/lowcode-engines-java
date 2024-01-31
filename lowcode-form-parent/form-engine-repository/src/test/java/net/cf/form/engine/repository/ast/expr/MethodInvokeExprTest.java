package net.cf.form.engine.repository.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLIntegerExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
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

        OqlExprParser parser = new OqlExprParser(METHOD_INVOKE1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlMethodInvokeExpr);
        Assert.assertTrue("now".equalsIgnoreCase(((OqlMethodInvokeExpr) expr).getMethodName()));
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

        OqlExprParser parser = new OqlExprParser(METHOD_INVOKE2);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlMethodInvokeExpr);
        OqlMethodInvokeExpr fmie = (OqlMethodInvokeExpr) expr;
        Assert.assertTrue("max".equalsIgnoreCase(fmie.getMethodName()));
        List<QqlExpr> fmieArgs = fmie.getArguments();
        Assert.assertTrue(fmieArgs.size() == 2);
        Assert.assertTrue(fmieArgs.get(0) instanceof OqlIntegerExpr);
        Assert.assertTrue(fmieArgs.get(1) instanceof OqlIdentifierExpr);
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

        OqlExprParser parser = new OqlExprParser(METHOD_INVOKE3);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlMethodInvokeExpr);
        OqlMethodInvokeExpr fmie = (OqlMethodInvokeExpr) expr;
        Assert.assertTrue("max".equalsIgnoreCase(fmie.getMethodName()));
        List<QqlExpr> fmieArgs = fmie.getArguments();
        Assert.assertTrue(fmieArgs.size() == 2);
        Assert.assertTrue(fmieArgs.get(0) instanceof OqlIntegerExpr);
        Assert.assertTrue(fmieArgs.get(1) instanceof OqlIntegerExpr);
    }
}
