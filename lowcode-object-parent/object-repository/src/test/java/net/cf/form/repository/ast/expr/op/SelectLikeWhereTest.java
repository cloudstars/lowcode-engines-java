package net.cf.form.repository.ast.expr.op;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlLikeOpExpr;
import net.cf.form.repository.sql.parser.SqlExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SelectLikeWhereTest {

    private static final String LIKE_EQUALS = "f1 like '2'";
    private static final String LIKE_START_WITH = "f2 like '2%'";
    private static final String LIKE_END_WITH = "f3 like '%2'";
    private static final String LIKE_CONTAINS = "f4 like '%2%'";
    private static final String LIKE_ESCAPE = "f1 like '\\_a%' escape '\\'";
    private static final String LIKE_VAR = "f1 like '%${varName}%'";

    @Test
    public void testLikeEquals() {
        SQLExprParser p = new SQLExprParser(LIKE_EQUALS);
        SQLExpr e = p.expr();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);

        SqlExprParser parser = new SqlExprParser(LIKE_EQUALS);
        SqlExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof SqlLikeOpExpr);
    }

}