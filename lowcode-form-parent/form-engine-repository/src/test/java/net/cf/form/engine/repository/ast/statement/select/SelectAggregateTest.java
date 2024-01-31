package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlAggregateExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOperator;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectGroupBy;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectItem;
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
 * 聚合查询测试类
 *
 * @author clouds
 */
@Ignore
@RunWith(JUnit4.class)
@Deprecated
public class SelectAggregateTest {

    /**
     * COUNT全部
     */
    private static final String COUNT_ALL = "select count(*) as a, count(1), count(0) as b from objectName";

    private static final String GROUP_BY = "select f1, count(0), max(f2) as maxF2, min(f3), avg(f4),  stddev(f5), sum(f6) from objectName where t > '2017-01-01' group by f1, f0;";

    private static final String GROUP_BY_HAVING = "select f1, count(0), max(f2) as maxF2, min(f3), avg(f4),  stddev(f5), sum(f6) from objectName where t > '2017-01-01' group by f1 having(max(f1) = 2);";


    @Test
    public void testCountAll() {
        SQLStatementParser parser1 = new SQLStatementParser(COUNT_ALL);
        OqlStatementParser parser2 = new OqlStatementParser(COUNT_ALL);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 3);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof OqlAggregateExpr);
        Assert.assertTrue("a".equals(selectItems.get(0).getAlias()));
        Assert.assertTrue(selectItems.get(1).getExpr() instanceof OqlAggregateExpr);
        Assert.assertTrue(selectItems.get(2).getExpr() instanceof OqlAggregateExpr);
        Assert.assertTrue("b".equals(selectItems.get(2).getAlias()));
    }

    @Test
    public void testGroup() {
        SQLStatementParser parser1 = new SQLStatementParser(GROUP_BY);
        OqlStatementParser parser2 = new OqlStatementParser(GROUP_BY);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 7);

        Assert.assertTrue(selectItems.get(0).getExpr() instanceof OqlIdentifierExpr);

        {
            Assert.assertTrue(selectItems.get(1).getExpr() instanceof OqlAggregateExpr);
            OqlAggregateExpr aggrExpr1 = (OqlAggregateExpr) selectItems.get(1).getExpr();
            Assert.assertTrue("count".equals(aggrExpr1.getMethodName()));
            Assert.assertTrue(aggrExpr1.getArguments().size() == 1);
            Assert.assertTrue(aggrExpr1.getArguments().get(0) instanceof OqlIntegerExpr);
            OqlIntegerExpr aggrExpr1Arg1 = (OqlIntegerExpr) aggrExpr1.getArguments().get(0);
            Assert.assertTrue(0 == aggrExpr1Arg1.getValue());
        }

        {
            Assert.assertTrue(selectItems.get(2).getExpr() instanceof OqlAggregateExpr);
            OqlAggregateExpr aggrExpr2 = (OqlAggregateExpr) selectItems.get(2).getExpr();
            Assert.assertTrue("max".equals(aggrExpr2.getMethodName()));
            Assert.assertTrue(aggrExpr2.getArguments().size() == 1);
            Assert.assertTrue(aggrExpr2.getArguments().get(0) instanceof OqlIdentifierExpr);
            OqlIdentifierExpr aggrExpr2Arg1 = (OqlIdentifierExpr) aggrExpr2.getArguments().get(0);
            Assert.assertTrue("f2".equals(aggrExpr2Arg1.getName()));
            Assert.assertTrue("maxF2".equals(selectItems.get(2).getAlias()));
        }

        {
            Assert.assertTrue(selectItems.get(3).getExpr() instanceof OqlAggregateExpr);
            OqlAggregateExpr aggrExpr3 = (OqlAggregateExpr) selectItems.get(3).getExpr();
            Assert.assertTrue("min".equals(aggrExpr3.getMethodName()));
            Assert.assertTrue(aggrExpr3.getArguments().size() == 1);
            Assert.assertTrue(aggrExpr3.getArguments().get(0) instanceof OqlIdentifierExpr);
            OqlIdentifierExpr aggrExpr3Arg1 = (OqlIdentifierExpr) aggrExpr3.getArguments().get(0);
            Assert.assertTrue("f3".equals(aggrExpr3Arg1.getName()));
            Assert.assertTrue(selectItems.get(3).getAlias() == null);
        }

        {
            Assert.assertTrue(selectItems.get(4).getExpr() instanceof OqlAggregateExpr);
            OqlAggregateExpr aggrExpr4 = (OqlAggregateExpr) selectItems.get(4).getExpr();
            Assert.assertTrue("avg".equals(aggrExpr4.getMethodName()));
            Assert.assertTrue(aggrExpr4.getArguments().size() == 1);
            Assert.assertTrue(aggrExpr4.getArguments().get(0) instanceof OqlIdentifierExpr);
            OqlIdentifierExpr aggrExpr4Arg1 = (OqlIdentifierExpr) aggrExpr4.getArguments().get(0);
            Assert.assertTrue("f4".equals(aggrExpr4Arg1.getName()));
            Assert.assertTrue(selectItems.get(4).getAlias() == null);
        }

        {
            Assert.assertTrue(selectItems.get(5).getExpr() instanceof OqlAggregateExpr);
            OqlAggregateExpr aggrExpr5 = (OqlAggregateExpr) selectItems.get(5).getExpr();
            Assert.assertTrue("stddev".equals(aggrExpr5.getMethodName()));
            Assert.assertTrue(aggrExpr5.getArguments().size() == 1);
            Assert.assertTrue(aggrExpr5.getArguments().get(0) instanceof OqlIdentifierExpr);
            OqlIdentifierExpr aggrExpr5Arg1 = (OqlIdentifierExpr) aggrExpr5.getArguments().get(0);
            Assert.assertTrue("f5".equals(aggrExpr5Arg1.getName()));
            Assert.assertTrue(selectItems.get(5).getAlias() == null);
        }

        {
            Assert.assertTrue(selectItems.get(6).getExpr() instanceof OqlAggregateExpr);
            OqlAggregateExpr aggrExpr6 = (OqlAggregateExpr) selectItems.get(6).getExpr();
            Assert.assertTrue("sum".equals(aggrExpr6.getMethodName()));
            Assert.assertTrue(aggrExpr6.getArguments().size() == 1);
            Assert.assertTrue(aggrExpr6.getArguments().get(0) instanceof OqlIdentifierExpr);
            OqlIdentifierExpr aggrExpr6Arg1 = (OqlIdentifierExpr) aggrExpr6.getArguments().get(0);
            Assert.assertTrue("f6".equals(aggrExpr6Arg1.getName()));
            Assert.assertTrue(selectItems.get(6).getAlias() == null);
        }

        OqlSelectGroupBy groupBy = stmt.getSelect().getGroupBy();
        Assert.assertTrue(groupBy.getItems().size() == 2);
        Assert.assertTrue(groupBy.getItems().get(0) instanceof OqlIdentifierExpr);
        Assert.assertTrue(groupBy.getItems().get(1) instanceof OqlIdentifierExpr);
        Assert.assertNull(groupBy.getHaving());
    }

    @Test
    public void testGroupHaving() {
        SQLStatementParser parser1 = new SQLStatementParser(GROUP_BY_HAVING);
        OqlStatementParser parser2 = new OqlStatementParser(GROUP_BY_HAVING);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 7);

        OqlSelectGroupBy groupBy = stmt.getSelect().getGroupBy();
        Assert.assertNotNull(groupBy.getHaving());
        QqlExpr havingExpr = groupBy.getHaving();
        Assert.assertTrue(havingExpr instanceof OqlBinaryOpExpr);
        Assert.assertTrue(((OqlBinaryOpExpr) havingExpr).getOperator() == OqlBinaryOperator.Equal);
    }

}
