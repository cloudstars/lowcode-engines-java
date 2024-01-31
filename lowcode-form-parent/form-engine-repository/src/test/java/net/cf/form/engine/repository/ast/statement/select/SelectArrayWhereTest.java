package net.cf.form.engine.repository.ast.statement.select;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlArrayContainsOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlArrayContainsOption;
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

/**
 * 字段是一个数组的查询测试
 *
 * @author clouds
 */
@Ignore
@RunWith(JUnit4.class)
@Deprecated
public class SelectArrayWhereTest {

    // 包括任意
    private static final String SELECT_CONTAINS_ANY = "select * from objectName where f1 contains any ['1', '2']";

    // 包含全部
    private static final String SELECT_CONTAINS_ALL = "select * from objectName where f1 contains all [1, 2] or f2 contains [1];";

    @Test
    public void testSelectContainsAny() {
        OqlStatementParser parser = new OqlStatementParser(SELECT_CONTAINS_ANY);
        List<OqlStatement> statements = parser.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) statements.get(0);
        QqlExpr where = stmt.getSelect().getWhere().getExpr();
        Assert.assertTrue(where instanceof OqlArrayContainsOpExpr);
        OqlArrayContainsOpExpr containsOpExpr = (OqlArrayContainsOpExpr) where;
        Assert.assertTrue(containsOpExpr.getOption() == OqlArrayContainsOption.ANY);
        Assert.assertTrue(containsOpExpr.getRight() instanceof OqlJsonArrayExpr);
        Assert.assertTrue(((OqlJsonArrayExpr) containsOpExpr.getRight()).getItems().size() == 2);
    }


    @Test
    public void testSelectContainsAll() {
        OqlStatementParser parser = new OqlStatementParser(SELECT_CONTAINS_ALL);
        List<OqlStatement> statements = parser.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) statements.get(0);
        QqlExpr where = stmt.getSelect().getWhere().getExpr();
        Assert.assertTrue(where instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) where;

        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlArrayContainsOpExpr);
        OqlArrayContainsOpExpr containsOpExprLeft = (OqlArrayContainsOpExpr) binaryOpExpr.getLeft();
        Assert.assertTrue(containsOpExprLeft.getOption() == OqlArrayContainsOption.ALL);
        Assert.assertTrue(containsOpExprLeft.getRight() instanceof OqlJsonArrayExpr);
        Assert.assertTrue(((OqlJsonArrayExpr) containsOpExprLeft.getRight()).getItems().size() == 2);

        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlArrayContainsOpExpr);
        OqlArrayContainsOpExpr containsOpExprRight = (OqlArrayContainsOpExpr) binaryOpExpr.getRight();
        Assert.assertTrue(containsOpExprRight.getOption() == OqlArrayContainsOption.ALL);
        Assert.assertTrue(containsOpExprRight.getRight() instanceof OqlJsonArrayExpr);
        Assert.assertTrue(((OqlJsonArrayExpr) containsOpExprRight.getRight()).getItems().size() == 1);
    }

}