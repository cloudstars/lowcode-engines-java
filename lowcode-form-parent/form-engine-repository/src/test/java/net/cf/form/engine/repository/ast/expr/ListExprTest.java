package net.cf.form.engine.repository.ast.expr;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.OqlListExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlCharExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.OqlUndefinedExpr;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
import net.cf.form.engine.repository.oql.parser.Token;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;


/**
 * 列表解析测试
 *
 * @author clouds
 */
@RunWith(JUnit4.class)
public class ListExprTest {

    public final String EMPTY_LIST = "()";

    public final String EMPTY_ITEM_LIST1 = "(,,,)";

    public final String EMPTY_ITEM_LIST2 = "('1',,'2',)";

    @Test
    public void testEmptyList() {
        OqlExprParser parser = new OqlExprParser(EMPTY_LIST);
        parser.accept(Token.LPAREN);
        OqlListExpr listExpr = new OqlListExpr();
        parser.exprList(listExpr.getItems(), listExpr);
        parser.accept(Token.RPAREN);
        Assert.assertTrue(listExpr.getItems().size() == 0);
    }

    @Test
    public void testEmptyList1() {
        OqlExprParser parser = new OqlExprParser(EMPTY_ITEM_LIST1);
        parser.accept(Token.LPAREN);
        OqlListExpr listExpr = new OqlListExpr();
        parser.exprList(listExpr.getItems(), listExpr);
        parser.accept(Token.RPAREN);
        List<QqlExpr> items = listExpr.getItems();
        Assert.assertTrue(items.size() == 4);
        for (QqlExpr item : items) {
            Assert.assertTrue(item instanceof OqlUndefinedExpr);
        }
    }

    @Test
    public void testEmptyList2() {
        OqlExprParser parser = new OqlExprParser(EMPTY_ITEM_LIST2);
        parser.accept(Token.LPAREN);
        OqlListExpr listExpr = new OqlListExpr();
        parser.exprList(listExpr.getItems(), listExpr);
        parser.accept(Token.RPAREN);
        List<QqlExpr> items = listExpr.getItems();
        Assert.assertTrue(items.size() == 4);
        Assert.assertTrue(items.get(0) instanceof OqlCharExpr);
        Assert.assertTrue(items.get(1) instanceof OqlUndefinedExpr);
        Assert.assertTrue(items.get(2) instanceof OqlCharExpr);
        Assert.assertTrue(items.get(3) instanceof OqlUndefinedExpr);
    }

}
