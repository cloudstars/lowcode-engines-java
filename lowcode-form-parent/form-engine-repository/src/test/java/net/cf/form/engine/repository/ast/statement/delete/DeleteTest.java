package net.cf.form.engine.repository.ast.statement.delete;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLDeleteStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlDeleteStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@Deprecated
@RunWith(JUnit4.class)
@Ignore
public class DeleteTest {

    private static final String DELETE_SIMPLE = "delete from objectName where 1 = 1;";

    @Test
    public void testDeleteSimple() {
        SQLStatementParser p = new SQLStatementParser(DELETE_SIMPLE);
        List<SQLStatement> stmts = p.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SQLDeleteStatement);

        OqlStatementParser parser = new OqlStatementParser(DELETE_SIMPLE);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlDeleteStatement);

        OqlDeleteStatement deleteStmt = (OqlDeleteStatement) statements.get(0);
        Assert.assertTrue(deleteStmt.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);
        Assert.assertTrue(deleteStmt.getWhereClause().getExpr() instanceof OqlBinaryOpExpr);
    }


    private static final String BATCH_DELETE_SIMPLE = "delete from objectName where a in (1,2)";
    @Test
    public void testBatchDeleteSimple() {
        SQLStatementParser p = new SQLStatementParser(BATCH_DELETE_SIMPLE);
        List<SQLStatement> stmts = p.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SQLDeleteStatement);

        OqlStatementParser parser = new OqlStatementParser(BATCH_DELETE_SIMPLE);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlDeleteStatement);

        OqlDeleteStatement deleteStmt = (OqlDeleteStatement) statements.get(0);
        Assert.assertTrue(deleteStmt.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);
        Assert.assertTrue(deleteStmt.getWhereClause().getExpr() instanceof OqlBinaryOpExpr);
    }
}
