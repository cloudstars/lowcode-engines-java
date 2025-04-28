package net.clouds.lowcode.formula.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.identifier.FxIdentifierExpr;
import net.cf.formula.engine.ast.expr.identifier.FxPropertyExpr;
import net.cf.formula.engine.parser.FxExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IdentifierExprTest {

    static final String IDENTIFIER_1 = "(a)";

    static final String PROPERTY_1 = "o1.o2.o3.xx";

    @Test
    public void testIdentifier1() {
        SQLExprParser sp = new SQLExprParser(IDENTIFIER_1);
        SQLExpr se = sp.primary();
        Assert.assertTrue(se instanceof SQLIdentifierExpr);

        FxExprParser fp = new FxExprParser(IDENTIFIER_1);
        FxExpr fe = fp.primary();
        Assert.assertTrue(fe instanceof FxIdentifierExpr);
    }

    @Test
    public void testProperty1() {
        SQLExprParser sp = new SQLExprParser(PROPERTY_1);
        SQLExpr se = sp.primary();
        Assert.assertTrue(se instanceof SQLPropertyExpr);


        FxExprParser fp = new FxExprParser(PROPERTY_1);
        FxExpr fe = fp.primary();

        Assert.assertTrue(fe instanceof FxPropertyExpr);
        FxPropertyExpr propExpr = (FxPropertyExpr) fe;
        Assert.assertTrue("xx".equals(propExpr.getName()));
        Assert.assertTrue(propExpr.getOwner() instanceof FxPropertyExpr);
        FxPropertyExpr propOwnerExpr = (FxPropertyExpr) propExpr.getOwner();
        Assert.assertTrue("o3".equals(propOwnerExpr.getName()));
        Assert.assertTrue(propOwnerExpr.getOwner() instanceof FxPropertyExpr);
        FxPropertyExpr propOwner2Expr = (FxPropertyExpr) propOwnerExpr.getOwner();
        Assert.assertTrue("o2".equals(propOwner2Expr.getName()));
        Assert.assertTrue(propOwner2Expr.getOwner() instanceof FxIdentifierExpr);
        FxIdentifierExpr propOwner3Expr = (FxIdentifierExpr) propOwner2Expr.getOwner();
        Assert.assertTrue("o1".equals(propOwner3Expr.getName()));
    }

}
