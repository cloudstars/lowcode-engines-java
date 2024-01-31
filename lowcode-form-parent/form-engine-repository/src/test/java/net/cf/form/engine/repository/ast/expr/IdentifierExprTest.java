package net.cf.form.engine.repository.ast.expr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlPropertyExpr;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IdentifierExprTest {

    final static String IDENTIFIER_1 = "(a)";

    final static String PROPERTY_1 = "o1.o2.o3.xx";

    @Test
    public void testIdentifier1() {
        SQLExprParser sp = new SQLExprParser(IDENTIFIER_1);
        SQLExpr se = sp.primary();
        Assert.assertTrue(se instanceof SQLIdentifierExpr);

        OqlExprParser fp = new OqlExprParser(IDENTIFIER_1);
        QqlExpr fe = fp.primary();
        Assert.assertTrue(fe instanceof OqlIdentifierExpr);
    }

    @Test
    public void testProperty1() {
        SQLExprParser sp = new SQLExprParser(PROPERTY_1);
        SQLExpr se = sp.primary();
        Assert.assertTrue(se instanceof SQLPropertyExpr);


        OqlExprParser fp = new OqlExprParser(PROPERTY_1);
        QqlExpr fe = fp.primary();

        Assert.assertTrue(fe instanceof OqlPropertyExpr);
        OqlPropertyExpr propExpr = (OqlPropertyExpr) fe;
        Assert.assertTrue("xx".equals(propExpr.getName()));
        Assert.assertTrue(propExpr.getOwner() instanceof OqlPropertyExpr);
        OqlPropertyExpr propOwnerExpr = (OqlPropertyExpr) propExpr.getOwner();
        Assert.assertTrue("o3".equals(propOwnerExpr.getName()));
        Assert.assertTrue(propOwnerExpr.getOwner() instanceof OqlPropertyExpr);
        OqlPropertyExpr propOwner2Expr = (OqlPropertyExpr) propOwnerExpr.getOwner();
        Assert.assertTrue("o2".equals(propOwner2Expr.getName()));
        Assert.assertTrue(propOwner2Expr.getOwner() instanceof OqlIdentifierExpr);
        OqlIdentifierExpr propOwner3Expr = (OqlIdentifierExpr) propOwner2Expr.getOwner();
        Assert.assertTrue("o1".equals(propOwner3Expr.getName()));
    }

}
