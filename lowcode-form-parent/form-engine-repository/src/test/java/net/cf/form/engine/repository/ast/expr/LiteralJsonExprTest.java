package net.cf.form.engine.repository.ast.expr;

import net.cf.form.engine.repository.oql.ast.expr.QqlExpr;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import net.cf.form.engine.repository.oql.parser.OqlExprParser;
import net.cf.form.engine.repository.oql.ast.expr.literal.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
public class LiteralJsonExprTest {

    private final static String EMPTY_ARRAY = "[]";

    private final static String INT_ARRAY1 = "[1, 2 , 3]";

    private final static String CHAR_ARRAY1 = "['1', '2', '3']";

    private final static String CHAR_ARRAY2 = "['', '2']";

    private final static String NUMBER_ARRAY1 = "[1.1, 1.2]";

    private final static String BOOLEAN_ARRAY1 = "[true, false]";

    private final static String EMPTY_JSON = "{}";

    private final static String JSON1 = "{\"a\": 1, \"b\": \"2\"}";

    private final static String JSON_ARRAY1 = "[{\"a\": 1, \"b\": \"2\"}, {\"a\": 1, \"b\": \"2\"}]";



    @Test
    public void testIntArray1() {
        OqlExprParser parser = new OqlExprParser(EMPTY_ARRAY);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonArrayExpr);
    }

    @Test
    public void testIntArray2() {
        OqlExprParser parser = new OqlExprParser(INT_ARRAY1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonArrayExpr);
        List<QqlExpr> items = ((OqlJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 3);
        Assert.assertTrue(items.get(0) instanceof OqlIntegerExpr);
    }

    @Test
    public void testCharArray1() {
        OqlExprParser parser = new OqlExprParser(CHAR_ARRAY1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonArrayExpr);
        List<QqlExpr> items = ((OqlJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 3);
        Assert.assertTrue(items.get(0) instanceof OqlCharExpr);
    }

    @Test
    public void testCharArray2() {
        OqlExprParser parser = new OqlExprParser(CHAR_ARRAY2);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonArrayExpr);
        List<QqlExpr> items = ((OqlJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof OqlCharExpr);
    }

    @Test
    public void testNumberArray1() {
        OqlExprParser parser = new OqlExprParser(NUMBER_ARRAY1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonArrayExpr);
        List<QqlExpr> items = ((OqlJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof OqlNumberExpr);
    }

    @Test
    public void testCharBOOL1() {
        OqlExprParser parser = new OqlExprParser(BOOLEAN_ARRAY1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonArrayExpr);
        List<QqlExpr> items = ((OqlJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof OqlBooleanExpr);
    }

    @Test
    public void testEmptyJson() {
        OqlExprParser parser = new OqlExprParser(EMPTY_JSON);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonObjectExpr);
        List<QqlExpr> items = ((OqlJsonObjectExpr) expr).getChildren();
        Assert.assertTrue(items.size() == 0);
    }

    @Test
    public void testJson1() {
        OqlExprParser parser = new OqlExprParser(JSON1);
        QqlExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof OqlJsonObjectExpr);
        List<QqlExpr> items = ((OqlJsonObjectExpr) expr).getChildren();
        Assert.assertTrue(items.size() == 2);
    }

    @Test
    public void testJsonArray1() {
        OqlExprParser parser = new OqlExprParser(JSON_ARRAY1);
        QqlExpr expr = parser.primary();
        StringBuilder stringBuilder = new StringBuilder();
        Assert.assertTrue(expr instanceof OqlJsonArrayExpr);
        List<QqlExpr> items = ((OqlJsonArrayExpr) expr).getChildren();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof OqlJsonObjectExpr);
    }

}
