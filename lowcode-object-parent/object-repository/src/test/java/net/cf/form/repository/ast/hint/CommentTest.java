package net.cf.form.repository.ast.hint;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.parser.SQLExprParser;
import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.form.repository.sql.ast.expr.op.SqlBinaryOpExpr;
import net.cf.form.repository.sql.parser.SqlExprParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CommentTest {

    private static final String LINE_COMMENT = "// 这是一行注释；\na + 1 < 2";

    private static final String MULTILINE_COMMENT1 = "/* 这是一段注释；*/ a + 1 < 2";
    private static final String MULTILINE_COMMENT2 = "/* 这是一段注释，换行了；\n*/ a + 1 < 2";
    private static final String MULTILINE_COMMENT3 = "/* 这是一段嵌套注释；\n abc + 1 == 2 /* \r\nxxx */ */ a + 1 < 2";

    @Test
    public void testSingleLine() {
        SQLExprParser p = new SQLExprParser(LINE_COMMENT);
        SQLExpr e = p.expr();
        Assert.assertTrue(e instanceof SQLBinaryOpExpr);

        SqlExprParser parser = new SqlExprParser(LINE_COMMENT);
        SqlExpr expr = parser.expr();
        Assert.assertTrue(expr instanceof SqlBinaryOpExpr);
    }

    @Test
    public void testMultiline() {
        SQLExprParser p1 = new SQLExprParser(MULTILINE_COMMENT1);
        SQLExpr e1 = p1.expr();
        Assert.assertTrue(e1 instanceof SQLBinaryOpExpr);
        SQLExprParser p2 = new SQLExprParser(MULTILINE_COMMENT2);
        SQLExpr e2 = p2.expr();
        Assert.assertTrue(e2 instanceof SQLBinaryOpExpr);
        SQLExprParser p3 = new SQLExprParser(MULTILINE_COMMENT3);
        SQLExpr e3 = p3.expr();
        // Druid对多行解析存在BUG？
        Assert.assertTrue(e3 instanceof SQLAllColumnExpr);

        SqlExprParser parser1 = new SqlExprParser(MULTILINE_COMMENT1);
        SqlExpr expr1 = parser1.expr();
        Assert.assertTrue(expr1 instanceof SqlBinaryOpExpr);
        SqlExprParser parser2 = new SqlExprParser(MULTILINE_COMMENT2);
        SqlExpr expr2 = parser2.expr();
        Assert.assertTrue(expr2 instanceof SqlBinaryOpExpr);
        SqlExprParser parser3 = new SqlExprParser(MULTILINE_COMMENT3);
        SqlExpr expr3 = parser3.expr();
        Assert.assertTrue(expr3 instanceof SqlBinaryOpExpr);
    }
}
