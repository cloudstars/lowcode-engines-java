package net.cf.form.engine.repository.ast.statement.update;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLUpdateStatement;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlMethodInvokeExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;
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
public class UpdateRefDetailTest {

    // 更新（字段名称以数字开头）
    private static final String UPDATE_NUMBER_START_FIELDS = "update objectName set 1a = 1, d2 = '3', 2d=1.2, 2dd=true;";

    // 更新子对象
    private static final String UPDATE_MAIN_REF_DETAIL_OBJECT = "update objectName set f1 = 'a', f2 = 1, refField = [4, 5], detailObjectField(subF1, subF2, subF3) = [((1 + 2) * 3, 2, 3), (1, 2, 3, 123), (1, 2, 3)]";


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
    public void testUpdateMainRefDetailObject() {
        OqlStatementParser parser = new OqlStatementParser(UPDATE_MAIN_REF_DETAIL_OBJECT);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlUpdateStatement);

        List<OqlUpdateSetItem> setItems = ((OqlUpdateStatement) statements.get(0)).getSetItems();
        Assert.assertTrue(setItems.size() == 4);
        Assert.assertTrue(setItems.get(3).getField() instanceof OqlMethodInvokeExpr);
        Assert.assertTrue(setItems.get(3).getValue() instanceof OqlJsonArrayExpr);
        List<QqlExpr> subListExpr = ((OqlJsonArrayExpr) setItems.get(3).getValue()).getItems();
        Assert.assertTrue(subListExpr.size() == 3);
        for (int i = 0; i < subListExpr.size(); i++) {
            Assert.assertTrue(subListExpr.get(i) instanceof OqlListExpr);
        }
    }
}
