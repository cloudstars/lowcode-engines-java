package net.cf.form.engine.repository.ast.statement.update;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateSetItem;
import net.cf.form.engine.repository.oql.ast.statement.OqlUpdateStatement;
import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@Deprecated
@Ignore
@RunWith(JUnit4.class)
public class UpdateTest {

    // 字段名称以数字开头
    private static final String UPDATE_NUMBER_START_FIELDS = "update objectName set 1a = 1, d2 = '3', 2d=1.2, 2dd=true where 1a = 1";

    // 字段名称以数字开头
    private static final String UPDATE_WHERE = "update objectName set 1a = 1, d2 = '3', 2d=1.2, 2dd=true where 1 = 1;";


    @Test
    public void testUpdateNumberStartFields() {
        SQLStatementParser p = new SQLStatementParser(UPDATE_NUMBER_START_FIELDS);
        List<SQLStatement> stmts = p.parseStatementList();
        Assert.assertTrue(stmts != null && stmts.size() == 1);
        Assert.assertTrue(stmts.get(0) instanceof SQLUpdateStatement);

        OqlStatementParser parser = new OqlStatementParser(UPDATE_NUMBER_START_FIELDS);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlUpdateStatement);

        OqlUpdateStatement updateStmt = (OqlUpdateStatement) statements.get(0);
        Assert.assertTrue(updateStmt.getSetItems().size() == 4);
        Assert.assertTrue(updateStmt.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);

        // 检查4个字段名是否正确
        List<OqlUpdateSetItem> updateSetItems = updateStmt.getSetItems();
        QqlExpr setItem0Expr = updateSetItems.get(0).getField();
        Assert.assertTrue(setItem0Expr instanceof OqlIdentifierExpr);
        OqlIdentifierExpr column0Expr = (OqlIdentifierExpr) setItem0Expr;
        Assert.assertTrue("1a".equalsIgnoreCase(column0Expr.getName()));
        QqlExpr setItem1Expr = updateSetItems.get(1).getField();
        Assert.assertTrue(setItem1Expr instanceof OqlIdentifierExpr);
        OqlIdentifierExpr column1Expr = (OqlIdentifierExpr) setItem1Expr;
        Assert.assertTrue("d2".equalsIgnoreCase(column1Expr.getName()));
        QqlExpr setItem2Expr = updateSetItems.get(2).getField();
        Assert.assertTrue(setItem2Expr instanceof OqlIdentifierExpr);
        OqlIdentifierExpr column2Expr = (OqlIdentifierExpr) setItem2Expr;
        Assert.assertTrue("2d".equalsIgnoreCase(column2Expr.getName()));
        QqlExpr setItem3Expr = updateSetItems.get(3).getField();
        Assert.assertTrue(setItem3Expr instanceof OqlIdentifierExpr);
        OqlIdentifierExpr column3Expr = (OqlIdentifierExpr) setItem3Expr;
        Assert.assertTrue("2dd".equalsIgnoreCase(column3Expr.getName()));
    }

    @Test
    public void testUpdateWhere() {
        OqlStatementParser parser = new OqlStatementParser(UPDATE_WHERE);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlUpdateStatement);
    }
}
