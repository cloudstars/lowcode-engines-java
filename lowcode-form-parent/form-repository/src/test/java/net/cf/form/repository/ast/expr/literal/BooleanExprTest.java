package net.cf.form.repository.ast.expr.literal;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlBooleanExpr;
import net.cf.form.repository.sql.parser.SqlExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * 日期时间表达式解析测试，注意Durid把单具true | false当成关键字
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class BooleanExprTest {

    public final String TRUE = "true";

    public final String FALSE = "false";


    @Test
    public void testTrue() {
        SqlExprParser parser = new SqlExprParser(TRUE);
        SqlExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof SqlBooleanExpr);
        Assert.assertTrue(((SqlBooleanExpr) expr).getValue());

        // 与 Druid 的 AST 作对比
        SQLExprParser druidSqlParser = new SQLExprParser(TRUE);
        SQLExpr druidExpr = druidSqlParser.expr();
        Assert.assertTrue(druidExpr instanceof SQLIdentifierExpr);
        Assert.assertTrue("true".equalsIgnoreCase(((SQLIdentifierExpr) druidExpr).getName()));
    }

    @Test
    public void testFalse() {
        SqlExprParser parser = new SqlExprParser(FALSE);
        SqlExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof SqlBooleanExpr);
        Assert.assertTrue(!((SqlBooleanExpr) expr).getValue());

        // 与 Druid 的 AST 作对比
        SQLExprParser druidSqlParser = new SQLExprParser(FALSE);
        SQLExpr druidExpr = druidSqlParser.expr();
        Assert.assertTrue(druidExpr instanceof SQLIdentifierExpr);
        Assert.assertTrue("false".equalsIgnoreCase(((SQLIdentifierExpr) druidExpr).getName()));
    }

}
