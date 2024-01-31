package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectOrderBy;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectOrderByItem;
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
public class SelectOrderByTest {

    private static final String ORDER_BY1 = "select * from objectName order by f1";

    private static final String ORDER_BY2 = "select * from objectName order by f1 asc";

    private static final String ORDER_BY3 = "select * from objectName order by f1 asc, f2 desc";

    @Test
    public void testOrderBy1() {
        SQLStatementParser parser1 = new SQLStatementParser(ORDER_BY1);
        OqlStatementParser parser2 = new OqlStatementParser(ORDER_BY1);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        OqlSelectOrderBy orderBy = stmt.getSelect().getOrderBy();
        Assert.assertTrue(orderBy.getItems().size() == 1);
        OqlSelectOrderByItem orderByItem0 = orderBy.getItems().get(0);
        Assert.assertTrue(orderByItem0.getExpr() instanceof OqlIdentifierExpr);
        Assert.assertTrue("f1".equals(((OqlIdentifierExpr) orderByItem0.getExpr()).getName()));
        Assert.assertTrue(orderByItem0.isAscending());
    }

    @Test
    public void testOrderBy2() {
        SQLStatementParser parser1 = new SQLStatementParser(ORDER_BY2);
        OqlStatementParser parser2 = new OqlStatementParser(ORDER_BY2);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        OqlSelectOrderBy orderBy = stmt.getSelect().getOrderBy();
        Assert.assertTrue(orderBy.getItems().size() == 1);
        OqlSelectOrderByItem orderByItem0 = orderBy.getItems().get(0);
        Assert.assertTrue(orderByItem0.getExpr() instanceof OqlIdentifierExpr);
        Assert.assertTrue("f1".equals(((OqlIdentifierExpr
                ) orderByItem0.getExpr()).getName()));
        Assert.assertTrue(orderByItem0.isAscending());
    }

    @Test
    public void testOrderBy3() {
        SQLStatementParser parser1 = new SQLStatementParser(ORDER_BY3);
        OqlStatementParser parser2 = new OqlStatementParser(ORDER_BY3);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        OqlSelectOrderBy orderBy = stmt.getSelect().getOrderBy();
        Assert.assertTrue(orderBy.getItems().size() == 2);

        {
            OqlSelectOrderByItem orderByItem0 = orderBy.getItems().get(0);
            Assert.assertTrue(orderByItem0.getExpr() instanceof OqlIdentifierExpr);
            Assert.assertTrue("f1".equals(((OqlIdentifierExpr) orderByItem0.getExpr()).getName()));
            Assert.assertTrue(orderByItem0.isAscending());
        }

        {
            OqlSelectOrderByItem orderByItem1 = orderBy.getItems().get(1);
            Assert.assertTrue(orderByItem1.getExpr() instanceof OqlIdentifierExpr);
            Assert.assertTrue("f2".equals(((OqlIdentifierExpr) orderByItem1.getExpr()).getName()));
            Assert.assertFalse(orderByItem1.isAscending());
        }
    }


}