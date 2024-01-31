package net.cf.form.engine.repository.ast.hint;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.identifier.OqlIdentifierExpr;
import net.cf.form.engine.repository.oql.ast.expr.operation.OqlBinaryOpExpr;
import net.cf.form.engine.repository.oql.ast.statement.OqlSelect;
import net.cf.form.engine.repository.oql.parser.OqlSelectParser;
import org.junit.Assert;
import org.junit.Test;

public class HintTest {

    private final String SINGLE_LINE1_OQL = "select 1 from  --这是一个单行注释\nobjectName";

    private final String SINGLE_LINE2_OQL = "select 1 from  //这是一个单行注释\nobjectName";

    private final String MULTI_LINE_OQL = "select 1 from  /*这是/*内嵌*/一个\n多行\n注释*/objectName";

    private final String COMPLEX_HIT_OQL = "select 1 from  /*这是/*内嵌*/一个\n多行\n注释*/objectName\n where f1 = 1 --这是一个单 行注释\nand f2 = '3' //这也是一个单行注释\n   ";

    @Test
    public void testHint1() {
        OqlSelectParser parser = new OqlSelectParser(SINGLE_LINE1_OQL);
        OqlSelect select = parser.select();

        Assert.assertTrue(select.getFrom().getFlashback() instanceof OqlIdentifierExpr);
    }

    @Test
    public void testHint2() {
        OqlSelectParser parser = new OqlSelectParser(SINGLE_LINE2_OQL);
        OqlSelect select = parser.select();

        Assert.assertTrue(select.getFrom().getFlashback() instanceof OqlIdentifierExpr);
    }

    @Test
    public void testHint3() {
        OqlSelectParser parser = new OqlSelectParser(MULTI_LINE_OQL);
        OqlSelect select = parser.select();

        Assert.assertTrue(select.getFrom().getFlashback() instanceof OqlIdentifierExpr);
    }

    @Test
    public void testHint4() {
        OqlSelectParser parser = new OqlSelectParser(COMPLEX_HIT_OQL);
        OqlSelect select = parser.select();

        Assert.assertTrue(select.getFrom().getFlashback() instanceof OqlIdentifierExpr);
        QqlExpr where = select.getWhere().getExpr();
        Assert.assertTrue(where instanceof OqlBinaryOpExpr);
        OqlBinaryOpExpr binaryOpExpr = (OqlBinaryOpExpr) where;
        Assert.assertTrue(binaryOpExpr.getLeft() instanceof OqlBinaryOpExpr);
        Assert.assertTrue(binaryOpExpr.getRight() instanceof OqlBinaryOpExpr);
    }
}
