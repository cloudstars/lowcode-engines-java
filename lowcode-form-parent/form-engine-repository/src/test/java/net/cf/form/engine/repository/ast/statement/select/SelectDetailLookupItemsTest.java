package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.*;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectItem;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import net.cf.form.engine.repository.oql.parser.Token;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@Ignore
@RunWith(JUnit4.class)
@Deprecated
public class SelectDetailLookupItemsTest {

    // 查询子表、相关表的全部字段
    private static final String SELECT_DETAIL_REF_ALL = "select *, detail.*, refExpand.* from objectName as aName;";

    // 查询子表、相关表的指定字段
    private static final String SELECT_DETAIL_LOOKUP_FIELDS = "select f1, f2, refField, detail(f1 as fx, f2, f3) as xx, refExpand(f1, f2, f3) as yy from objectName";

    @Test
    public void testThisAndRefAllOql() {
        SQLStatementParser parser1 = new SQLStatementParser("select *, ref.* from TABLE_NAME obj left join REF_TABLE_NAME ref on obj.id = ref.id");
        List<SQLStatement> statements = parser1.parseStatementList();
        Assert.assertTrue(statements.size() == 1 && statements.get(0) instanceof SQLSelectStatement);

        SQLSelectStatement statement = (SQLSelectStatement) statements.get(0);
        List<SQLSelectItem> selectItems = statement.getSelect().getQueryBlock().getSelectList();
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof SQLAllColumnExpr);
        Assert.assertTrue(selectItems.get(1).getExpr() instanceof SQLPropertyExpr);
        SQLPropertyExpr propertyExpr = (SQLPropertyExpr) selectItems.get(1).getExpr();
        Assert.assertTrue(propertyExpr.getOwner() instanceof SQLIdentifierExpr);
        Assert.assertTrue("ref".equalsIgnoreCase(((SQLIdentifierExpr) propertyExpr.getOwner()).getName()));
        Assert.assertTrue(Token.STAR.name.equalsIgnoreCase(propertyExpr.getName()));
    }

    @Test
    public void testSelectDetailRefAlL() {
        OqlStatementParser parser = new OqlStatementParser(SELECT_DETAIL_REF_ALL);
        List<OqlStatement> statements = parser.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements.size() == 1);
        OqlStatement firstStatement = statements.get(0);
        Assert.assertTrue(firstStatement instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) firstStatement;
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 3);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof OqlAllFieldExpr);

        Assert.assertTrue(selectItems.get(1).getExpr() instanceof OqlPropertyExpr);
        OqlPropertyExpr propertyExpr1 = (OqlPropertyExpr) selectItems.get(1).getExpr();
        Assert.assertTrue("detail".equals(propertyExpr1.getOwner().getName()));
        Assert.assertTrue(Token.STAR.getName().equals(propertyExpr1.getName()));

        Assert.assertTrue(selectItems.get(2).getExpr() instanceof OqlPropertyExpr);
        OqlPropertyExpr propertyExpr2 = (OqlPropertyExpr) selectItems.get(2).getExpr();
        Assert.assertTrue("refExpand".equals(propertyExpr2.getOwner().getName()));
        Assert.assertTrue(Token.STAR.getName().equals(propertyExpr2.getName()));

        Assert.assertTrue(stmt.isAfterSemi());
    }

    @Test
    public void testSelectDetailRefFields() {
        OqlStatementParser parser = new OqlStatementParser(SELECT_DETAIL_LOOKUP_FIELDS);
        List<OqlStatement> statements = parser.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(statements.size() == 1);
        OqlStatement firstStatement = statements.get(0);
        Assert.assertTrue(firstStatement instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) firstStatement;
        List<OqlSelectItem> selectItems = stmt.getSelect().getSelectItems();
        Assert.assertTrue(selectItems.size() == 5);
        Assert.assertTrue(selectItems.get(0).getExpr() instanceof OqlIdentifierExpr);
        Assert.assertTrue(selectItems.get(1).getExpr() instanceof OqlIdentifierExpr);
        Assert.assertTrue(selectItems.get(2).getExpr() instanceof OqlIdentifierExpr);

        Assert.assertTrue(selectItems.get(3).getExpr() instanceof OqlMethodInvokeExpr);
        Assert.assertTrue("xx".equals(selectItems.get(3).getAlias()));
        OqlMethodInvokeExpr detailField = (OqlMethodInvokeExpr) selectItems.get(3).getExpr();
        Assert.assertTrue("detail".equals(detailField.getMethodName()));
        List<QqlExpr> detailArgs = detailField.getArguments();
        Assert.assertTrue(detailArgs.size() == 3);
        Assert.assertTrue(detailArgs.get(0) instanceof OqlSelectItem);
        OqlSelectItem selectItem = (OqlSelectItem) detailArgs.get(0);
        Assert.assertTrue("f1".equals(((OqlIdentifierExpr) (selectItem.getExpr())).getName()));
        Assert.assertTrue("fx".equals((selectItem.getAlias())));
        Assert.assertTrue("f2".equals(((OqlIdentifierExpr) detailArgs.get(1)).getName()));
        Assert.assertTrue("f3".equals(((OqlIdentifierExpr) detailArgs.get(2)).getName()));


        Assert.assertTrue(selectItems.get(4).getExpr() instanceof OqlObjectExpandExpr);
        Assert.assertTrue("yy".equals(selectItems.get(4).getAlias()));
        OqlObjectExpandExpr refField = (OqlObjectExpandExpr) selectItems.get(4).getExpr();
        Assert.assertTrue("refExpand".equals(refField.getMethodName()));
        List<QqlExpr> refArgs = refField.getArguments();
        Assert.assertTrue(refArgs.size() == 3);
        Assert.assertTrue("f1".equals(((OqlIdentifierExpr) refArgs.get(0)).getName()));
        Assert.assertTrue("f2".equals(((OqlIdentifierExpr) refArgs.get(1)).getName()));
        Assert.assertTrue("f3".equals(((OqlIdentifierExpr) refArgs.get(2)).getName()));
    }

}
