package net.cf.form.engine.repository.ast.statement.insert;

import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlCharExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlJsonArrayExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertInto;
import net.cf.form.engine.repository.oql.ast.statement.OqlInsertStatement;
import net.cf.form.engine.repository.oql.ast.statement.OqlStatement;
import net.cf.form.engine.repository.oql.parser.OqlStatementParser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * 插入子表测试
 *
 * @author clouds
 */
@Deprecated
@RunWith(JUnit4.class)
@Ignore
public class InsertRefDetailTest {

    // 插入相关模型
    private static final String INSERT_REF_OBJECT = "insert into objectName(f1, f2, f3, refField) values ('a', 1, 1.3, [1,2,3])";

    // 插入子模型（子模型也是模型，语法同普通模型插入s）
    private static final String INSERT_DETAIL_OBJECT = "insert into subObjectName(subF1, subF2, subF3, masterField) values ('a', 1, 1.3, 'xxx')";

    // 插入子模型
    private static final String INSERT_MAIN_REF_DETAIL_OBJECT = "insert into objectName(f1, f2, refField, detailField(subF1, subF2, subF3)) values ('a', 1, [4, 5], [((1 + 2) * 3, 2, 3), (1, 2, 3), (1, 2, 3)])";


    @Test
    public void testInsertRefObject() {
        OqlStatementParser parser = new OqlStatementParser(INSERT_REF_OBJECT);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlInsertStatement);

        OqlInsertStatement insertStmt = (OqlInsertStatement) statements.get(0);
        OqlInsertInto insert = insertStmt.getInsertInto();
        Assert.assertTrue(insert.getFields().size() == 4);
        Assert.assertTrue(insert.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);
        Assert.assertTrue(insert.getValuesList().size() == 1);
        Assert.assertTrue(insert.getValuesList().get(0).getValues().size() == 4);
        List<QqlExpr> values = insert.getValuesList().get(0).getValues();
        Assert.assertTrue(values.get(3) instanceof OqlJsonArrayExpr);
    }

    @Test
    public void testInsertDetailObject() {
        OqlStatementParser parser = new OqlStatementParser(INSERT_DETAIL_OBJECT);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlInsertStatement);

        OqlInsertStatement insertStmt = (OqlInsertStatement) statements.get(0);
        OqlInsertInto insert = insertStmt.getInsertInto();
        Assert.assertTrue(insert.getFields().size() == 4);
        Assert.assertTrue(insert.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);
        Assert.assertTrue(insert.getValuesList().size() == 1);
        Assert.assertTrue(insert.getValuesList().get(0).getValues().size() == 4);
        List<QqlExpr> values = insert.getValuesList().get(0).getValues();
        Assert.assertTrue(values.get(3) instanceof OqlCharExpr);
    }

    @Test
    public void testInsertMainRefDetailObject() {
        OqlStatementParser parser = new OqlStatementParser(INSERT_MAIN_REF_DETAIL_OBJECT);
        List<OqlStatement> statements = parser.parseStatementList();
        Assert.assertTrue(statements != null && statements.size() == 1);
        Assert.assertTrue(statements.get(0) instanceof OqlInsertStatement);

        OqlInsertStatement insertStmt = (OqlInsertStatement) statements.get(0);
        OqlInsertInto insert = insertStmt.getInsertInto();
        Assert.assertTrue(insert.getFields().size() == 4);
        Assert.assertTrue(insert.getObjectSource().getFlashback() instanceof OqlIdentifierExpr);
        Assert.assertTrue(insert.getValuesList().size() == 1);
        Assert.assertTrue(insert.getValuesList().get(0).getValues().size() == 4);
        List<QqlExpr> values = insert.getValuesList().get(0).getValues();
        Assert.assertTrue(values.get(2) instanceof OqlJsonArrayExpr);
        Assert.assertTrue(values.get(3) instanceof OqlJsonArrayExpr);
        List<QqlExpr> subListExpr = ((OqlJsonArrayExpr) values.get(3)).getItems();
        Assert.assertTrue(subListExpr.size() == 3);
        for (int i = 0; i < subListExpr.size(); i++) {
            Assert.assertTrue(subListExpr.get(i) instanceof OqlListExpr);
        }
    }
}
