package net.cf.form.repository.ast.expr.literal;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLNullExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.literal.SqlNullExpr;
import net.cf.form.repository.sql.parser.SqlExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Null表达式解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class NullExprTest {

    public final String NULL = "null";

    @Test
    public void testNull() {
        SQLExprParser p = new SQLExprParser(NULL);
        SQLExpr e = p.primary();
        Assert.assertTrue(e instanceof SQLNullExpr);
        Assert.assertTrue(NULL.equalsIgnoreCase(e.toString()));

        SqlExprParser parser = new SqlExprParser(NULL);
        SqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof SqlNullExpr);
        Assert.assertTrue(NULL.equalsIgnoreCase(expr.toString()));

    }
}
