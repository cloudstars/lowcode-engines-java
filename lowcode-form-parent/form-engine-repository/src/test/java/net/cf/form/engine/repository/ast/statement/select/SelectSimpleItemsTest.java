package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlCharExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlIntegerExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlNumberExpr;
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

@Ignore
@RunWith(JUnit4.class)
@Deprecated
public class SelectSimpleItemsTest {

    // 查询单列
    private static final String SELECT_SINGLE_FIELD = "select f1 from objectName";

    // 查询多列
    private static final String SELECT_MULTI_ALIAS = "select f1 as a1, f2 as a2 from objectName as aName;";

    // 查询字段名是数字开头的列
    private static final String SELECT_NUMBER_START_FIELDS = "select 1a, d2, 2d, 2dd from objectName";

    // 查询常量
    private static final String SELECT_LITERAL_FIELDS = "select 1, -1.1 as aa, '2' as b from objectName";

    // 查询方法
    private static final String SELECT_METHOD_FIELD = "select now() as t from objectName";


    @Test
    public void testSelectSingleField() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_SINGLE_FIELD);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_SINGLE_FIELD);
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
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 1);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof QqlExpr);
        Assert.assertFalse(stmt.isAfterSemi());
    }

    @Test
    public void testSelectMultiFieldsAlias() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_MULTI_ALIAS);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_MULTI_ALIAS);
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
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 2);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof QqlExpr);
        Assert.assertTrue("a1".equals(selectItems.get(0).getAlias()));
        Assert.assertTrue(selectItems.get(1).getExpr() instanceof QqlExpr);
        Assert.assertTrue("a2".equals(selectItems.get(1).getAlias()));
        Assert.assertTrue("aName".equals(stmt.getSelect().getFrom().getAlias()));
        Assert.assertTrue(stmt.isAfterSemi());
    }

    @Test
    public void testSelectNumberStartFields() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_NUMBER_START_FIELDS);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_NUMBER_START_FIELDS);
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
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 4);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof OqlIdentifierExpr);
        OqlIdentifierExpr item0Expr = (OqlIdentifierExpr) selectItems.get(0).getExpr();
        Assert.assertTrue("1a".equals(item0Expr.getName()));
        Assert.assertTrue(selectItems.get(1).getExpr() instanceof OqlIdentifierExpr);
        OqlIdentifierExpr item1Expr = (OqlIdentifierExpr) selectItems.get(1).getExpr();
        Assert.assertTrue("d2".equals(item1Expr.getName()));
        Assert.assertTrue(selectItems.get(2).getExpr() instanceof OqlIdentifierExpr);
        OqlIdentifierExpr item2Expr = (OqlIdentifierExpr) selectItems.get(2).getExpr();
        Assert.assertTrue("2d".equals(item2Expr.getName()));
        Assert.assertTrue(selectItems.get(3).getExpr() instanceof QqlExpr);
        OqlIdentifierExpr item3Expr = (OqlIdentifierExpr

                ) selectItems.get(3).getExpr();
        Assert.assertTrue("2dd".equals(item3Expr.getName()));
        Assert.assertFalse(stmt.isAfterSemi());
    }


    @Test
    public void testSelectLiteralFields() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_LITERAL_FIELDS);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_LITERAL_FIELDS);
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
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 3);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof OqlIntegerExpr);
        Assert.assertTrue(selectItems.get(1).getExpr() instanceof OqlNumberExpr);
        Assert.assertTrue("aa".equals(selectItems.get(1).getAlias()));
        Assert.assertTrue(selectItems.get(2).getExpr() instanceof OqlCharExpr);
        Assert.assertTrue("b".equals(selectItems.get(2).getAlias()));
    }

    @Test
    public void testSelectMethodField() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_METHOD_FIELD);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_METHOD_FIELD);
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
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 1);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof OqlMethodInvokeExpr);
        Assert.assertTrue("t".equals(selectItems.get(0).getAlias()));
    }


}