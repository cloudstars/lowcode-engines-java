package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlAllFieldExpr;
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
public class SelectAllTest {

    // 查询全部数据
    private static final String SELECT_ALL = "select * from objectName";

    // 查询全部数据（含别名）
    private static final String SELECT_ALL_ALIAS = SELECT_ALL + " as aName";

    // 查询全部数据（尾部带分号）
    private static final String SELECT_ALL_SEMI = SELECT_ALL + Token.SEMI.name;

    @Test
    public void testSelectAlL() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_ALL);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_ALL);
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
        Assert.assertTrue(selectItems.size() == 1 && selectItems.get(0).getExpr() instanceof OqlAllFieldExpr);
        Assert.assertFalse(stmt.isAfterSemi());
    }

    @Test
    public void testSelectAlLAliasSemi() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_ALL_ALIAS);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_ALL_ALIAS);
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
        Assert.assertTrue(selectItems.size() == 1 && selectItems.get(0).getExpr() instanceof OqlAllFieldExpr);
        Assert.assertTrue("aName".equals(stmt.getSelect().getFrom().getAlias()));
        Assert.assertFalse(stmt.isAfterSemi());
    }

    @Test
    public void testSelectAlLSemi() {
        SQLStatementParser parser1 = new SQLStatementParser(SELECT_ALL_SEMI);
        OqlStatementParser parser2 = new OqlStatementParser(SELECT_ALL_SEMI);
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
        Assert.assertTrue(selectItems.size() == 1 && selectItems.get(0).getExpr() instanceof OqlAllFieldExpr);
        Assert.assertTrue(stmt.isAfterSemi());
    }
}
