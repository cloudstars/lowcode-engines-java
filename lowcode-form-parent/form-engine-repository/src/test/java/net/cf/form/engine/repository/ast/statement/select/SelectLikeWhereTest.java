package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlCharExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlLikeOpExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@Ignore
@RunWith(JUnit4.class)
@Deprecated
public class SelectLikeWhereTest {

    // 多种匹配模式
    private static final String SELECT_LIKE_MATCHES = "select * from objectName where f1 like '2' and f2 like '2%' and f3 like '%2' and f4 like '%2%';";

    // 带转义字符
    private static final String SELECT_LIKE_ESCAPE = "select * from objectName where f1 like '\\_a%' escape '\\';";

    @Test
    public void testSelectLikeMatches() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_LIKE_MATCHES);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_LIKE_MATCHES);
        List<SQLStatement> statements1 = parser1.parseStatementList();
        List<OqlStatement> statements2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements1.size() == 1);
        Assert.assertTrue(statements2.size() == 1);
        SQLStatement firstStatement1 = statements1.get(0);
        OqlStatement firstStatement2 = statements2.get(0);
        Assert.assertTrue(firstStatement1 instanceof SQLSelectStatement);
        Assert.assertTrue(firstStatement2 instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) firstStatement2;
        QqlExpr where = stmt.getSelect().getWhere().getExpr();
        Assert.assertTrue(where instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boe = (OqlBinaryOpExpr) where;
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.And);
        Assert.assertTrue(boe.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(boe.getRight() instanceof OqlLikeOpExpr);
        OqlLikeOpExpr boeR = (OqlLikeOpExpr) boe.getRight();
        Assert.assertTrue(boeR.getRight() instanceof OqlCharExpr);
        Assert.assertTrue("%2%".equals(((OqlCharExpr) boeR.getRight()).getText()));
        Assert.assertTrue(stmt.isAfterSemi());
    }

    @Test
    public void testSelectLikeEscape() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_LIKE_ESCAPE);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_LIKE_ESCAPE);
        List<SQLStatement> statements1 = parser1.parseStatementList();
        List<OqlStatement> statements2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements1.size() == 1);
        Assert.assertTrue(statements2.size() == 1);
        SQLStatement firstStatement1 = statements1.get(0);
        OqlStatement firstStatement2 = statements2.get(0);
        Assert.assertTrue(firstStatement1 instanceof SQLSelectStatement);
        Assert.assertTrue(firstStatement2 instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) firstStatement2;
        QqlExpr where = stmt.getSelect().getWhere().getExpr();
        Assert.assertTrue(where instanceof OqlLikeOpExpr);
        OqlLikeOpExpr boe = (OqlLikeOpExpr) where;
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.Like);
        Assert.assertTrue("\\".equals(boe.getEscape()));
        Assert.assertTrue(stmt.isAfterSemi());
    }

}