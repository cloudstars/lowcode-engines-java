package net.cf.form.engine.repository.ast.statement.select;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelectLimit;
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
public class SelectLimitTest {

    private static final String LIMIT1 = "select * from objectName limit 10";

    private static final String LIMIT2 = "select * from objectName limit 10000, 15";


    @Test
    public void testOrderBy1() {
        SQLStatementParser parser1 = new SQLStatementParser(LIMIT1);
        OqlStatementParser parser2 = new OqlStatementParser(LIMIT1);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        OqlSelectLimit limit = stmt.getSelect().getLimit();
        Assert.assertTrue(limit.getRowCount() == 10);
    }

    @Test
    public void testOrderBy2() {
        SQLStatementParser parser1 = new SQLStatementParser(LIMIT2);
        OqlStatementParser parser2 = new OqlStatementParser(LIMIT2);
        List<SQLStatement> stmts1 = parser1.parseStatementList();
        List<OqlStatement> stmts2 = parser2.parseStatementList();

        // 断言解析出一条且仅一条 select 语句
        Assert.assertTrue(stmts1.size() == 1 && stmts1.get(0) instanceof SQLSelectStatement);
        Assert.assertTrue(stmts2.size() == 1 && stmts2.get(0) instanceof OqlSelectStatement);

        OqlSelectStatement stmt = (OqlSelectStatement) stmts2.get(0);
        OqlSelectLimit limit = stmt.getSelect().getLimit();
        Assert.assertTrue(limit.getOffset() == 10000);
        Assert.assertTrue(limit.getRowCount() == 15);
    }
}
