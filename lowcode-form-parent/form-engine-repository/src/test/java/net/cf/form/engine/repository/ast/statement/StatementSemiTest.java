package net.cf.form.engine.repository.ast.statement;

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
 * 测试行尾的封号
 *
 * @author clouds
 */
@Deprecated
@Ignore
@RunWith(JUnit4.class)
public class StatementSemiTest {

    private static final String SELECT1 = "select * from objectName";
    private static final String SELECT2 = "select * from objectName;";

    @Test
    public void testSelectAlL() {
        OqlStatementParser parser1 = new OqlStatementParser(SELECT1);
        List<OqlStatement> statementList1 = parser1.parseStatementList();
        Assert.assertTrue(statementList1.size() == 1);
        Assert.assertTrue(statementList1.get(0) instanceof OqlSelectStatement);
        OqlSelectStatement stmt1 = (OqlSelectStatement) statementList1.get(0);
        Assert.assertFalse(stmt1.isAfterSemi());


        OqlStatementParser parser2 = new OqlStatementParser(SELECT2);
        List<OqlStatement> statementList2 = parser2.parseStatementList();
        Assert.assertTrue(statementList2.size() == 1);
        Assert.assertTrue(statementList2.get(0) instanceof OqlSelectStatement);
        OqlSelectStatement stmt2 = (OqlSelectStatement) statementList2.get(0);
        Assert.assertTrue(stmt2.isAfterSemi());
    }
}
