package net.clouds.lowcode.formula.ast.expr;

import net.cf.formula.engine.ast.expr.FxExpr;
import net.cf.formula.engine.ast.expr.literal.*;
import net.cf.formula.engine.parser.FxExprParser;
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
        FxExprParser parser = new FxExprParser(EMPTY_ARRAY);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonArrayExpr);
    }

    @Test
    public void testIntArray2() {
        FxExprParser parser = new FxExprParser(INT_ARRAY1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonArrayExpr);
        List<FxExpr> items = ((FxJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 3);
        Assert.assertTrue(items.get(0) instanceof FxIntegerExpr);
    }

    @Test
    public void testCharArray1() {
        FxExprParser parser = new FxExprParser(CHAR_ARRAY1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonArrayExpr);
        List<FxExpr> items = ((FxJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 3);
        Assert.assertTrue(items.get(0) instanceof FxCharExpr);
    }

    @Test
    public void testCharArray2() {
        FxExprParser parser = new FxExprParser(CHAR_ARRAY2);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonArrayExpr);
        List<FxExpr> items = ((FxJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof FxCharExpr);
    }

    @Test
    public void testNumberArray1() {
        FxExprParser parser = new FxExprParser(NUMBER_ARRAY1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonArrayExpr);
        List<FxExpr> items = ((FxJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof FxNumberExpr);
    }

    @Test
    public void testCharBOOL1() {
        FxExprParser parser = new FxExprParser(BOOLEAN_ARRAY1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonArrayExpr);
        List<FxExpr> items = ((FxJsonArrayExpr) expr).getItems();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof FxBooleanExpr);
    }

    @Test
    public void testEmptyJson() {
        FxExprParser parser = new FxExprParser(EMPTY_JSON);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonObjectExpr);
        List<FxExpr> items = ((FxJsonObjectExpr) expr).getChildren();
        Assert.assertTrue(items.size() == 0);
    }

    @Test
    public void testJson1() {
        FxExprParser parser = new FxExprParser(JSON1);
        FxExpr expr = parser.primary();
        Assert.assertTrue(expr instanceof FxJsonObjectExpr);
        List<FxExpr> items = ((FxJsonObjectExpr) expr).getChildren();
        Assert.assertTrue(items.size() == 2);
    }

    @Test
    public void testJsonArray1() {
        FxExprParser parser = new FxExprParser(JSON_ARRAY1);
        FxExpr expr = parser.primary();
        StringBuilder stringBuilder = new StringBuilder();
        Assert.assertTrue(expr instanceof FxJsonArrayExpr);
        List<FxExpr> items = ((FxJsonArrayExpr) expr).getChildren();
        Assert.assertTrue(items.size() == 2);
        Assert.assertTrue(items.get(0) instanceof FxJsonObjectExpr);
    }

}
