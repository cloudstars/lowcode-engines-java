package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlCharExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
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
public class SelectSimpleWhereTest {

    // 1 = 1条件
    private static final String SELECT_ONE_EQ_ONE = "select * from objectName where 1 = 1;";

    // 简单条件
    private static final String SELECT_SIMPLE = "select * from objectName where 1 = 1 and f1 = 1 or f2 = '3.3';";

    // 复杂条件
    private static final String SELECT_COMPLEX = "select * from objectName where (a>1)or b<1&& c<5*9+4 AND (F1 == F2 + 1) OR F3 = F4 * 2";


    @Test
    public void testSelectOneEqOne() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_ONE_EQ_ONE);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_ONE_EQ_ONE);
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
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.Equal);
        Assert.assertTrue(boe.getLeft() instanceof OqlIntegerExpr);
        Assert.assertTrue(boe.getRight() instanceof OqlIntegerExpr);
        Assert.assertTrue(stmt.isAfterSemi());
    }

    @Test
    public void testSelectSimple() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_SIMPLE);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_SIMPLE);
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
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.Or);
        Assert.assertTrue(boe.getLeft() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boeL = (OqlBinaryOpExpr) boe.getLeft();
        Assert.assertTrue(boeL.getOperator() == OqlBinaryOperator.And);
        Assert.assertTrue(boe.getRight() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boeR = (OqlBinaryOpExpr) boe.getRight();
        Assert.assertTrue(boeR.getLeft() instanceof OqlIdentifierExpr);
        Assert.assertTrue(boeR.getOperator() == OqlBinaryOperator.Equal);
        Assert.assertTrue(boeR.getRight() instanceof OqlCharExpr);
        Assert.assertTrue(stmt.isAfterSemi());
    }

    @Test
    public void testSelectComplex() {
        OqlStatementParser parser = new OqlStatementParser(SELECT_COMPLEX);
        List<OqlStatement> statements = parser.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements.size() == 1);
        OqlStatement firstStatement = statements.get(0);
        Assert.assertTrue(firstStatement instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) firstStatement;
        QqlExpr where = stmt.getSelect().getWhere().getExpr();
        Assert.assertTrue(where instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boe = (OqlBinaryOpExpr) where;
        Assert.assertTrue(boe.getOperator() == OqlBinaryOperator.Or);
        Assert.assertTrue(boe.getLeft() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boeL = (OqlBinaryOpExpr) boe.getLeft();
        Assert.assertTrue(boeL.getOperator() == OqlBinaryOperator.Or);
        Assert.assertTrue(boe.getRight() instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr boeR = (OqlBinaryOpExpr) boe.getRight();
        Assert.assertTrue(boeR.getLeft() instanceof

                OqlIdentifierExpr);
        Assert.assertTrue(boeR.getOperator() == OqlBinaryOperator.Equal);
        Assert.assertTrue(boeR.getRight() instanceof OqlBinaryOpExpr);
        Assert.assertFalse(stmt.isAfterSemi());
    }

}