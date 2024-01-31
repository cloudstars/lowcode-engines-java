package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlVariantRefExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
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
public class SelectVariableRefTest {

    private static final String SELECT_VAR1 = "select * from objectName where f1 = ${var1} and f2 = #{var2}";

    @Test
    public void testSelectVar1() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_VAR1);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_VAR1);
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
        Assert.assertTrue(((OqlBinaryOpExpr) where).getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(((OqlBinaryOpExpr) where).getRight() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr leftExpr = (OqlBinaryOpExpr) ((OqlBinaryOpExpr) where).getLeft();
        OqlBinaryOpExpr rightExpr = (OqlBinaryOpExpr) ((OqlBinaryOpExpr) where).getRight();
        Assert.assertTrue(leftExpr.getRight() instanceof OqlVariantRefExpr);
        Assert.assertTrue("${var1}".equals(((OqlVariantRefExpr) leftExpr.getRight()).getName()));
        Assert.assertTrue(rightExpr.getRight() instanceof OqlVariantRefExpr);
        Assert.assertTrue("#{var2}".equals(((OqlVariantRefExpr) rightExpr.getRight()).getName()));

    }
}
