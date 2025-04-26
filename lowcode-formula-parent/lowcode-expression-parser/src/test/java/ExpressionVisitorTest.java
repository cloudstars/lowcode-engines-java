import io.github.cloudstars.lowcode.expression.exception.ExpressionParseException;
import io.github.cloudstars.lowcode.expression.parser.ExprParser;
import io.github.cloudstars.lowcode.expression.parser.base.JavaScriptLexer;
import io.github.cloudstars.lowcode.expression.visitor.ExpressionVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2025/4/21
 */
public class ExpressionVisitorTest {

    @Test
    public void testPlusExpr0() {
        testVisitorExpr("1", "1");
    }

    @Test
    public void testPlusExpr01() {
        testVisitorExpr("a", "a");
    }

    @Test
    public void testPlusExpr() {
        testVisitorExpr("a+b", "a+b");
    }

    @Test
    public void testArithmeticExpr0() {
        testVisitorExpr("a + b   *c/1+1.01", "a+b*c/1+1.01");
    }

    @Test
    public void testMaxExpr() {
        String expr = "MAX(1, 2)";
        testVisitorExpr(expr, "MAX(1,2)");
    }

    @Test
    public void testMathExpr2() {
        testVisitorExpr("MIN(MAX(1, 2), 3)", "MIN(MAX(1,2),3)");
    }

    @Test
    public void testTernaryExpression01() {
        testVisitorExpr("a?1:2", "a?1:2");
    }

    @Test
    public void testEqualExpression02() {
        testVisitorExpr("a == b", "a==b");
    }

    @Test
    public void testEqualExpression021() {
        testVisitorExpr("a == b == c", "a==b==c");
    }

    @Test
    public void testNotEqualExpression02() {
        testVisitorExpr("a != b", "a!=b");
    }

    @Test
    public void testAllEqualExpression02() {
        testVisitorExpr("a === b", "a==b");
    }

    @Test
    public void testNotAllEqualExpression02() {
        testVisitorExpr("a !== b", "a!=b");
    }

    @Test
    public void testTernaryExpression02() {
        testVisitorExpr("1 == 1 ? 1 : 2", "1==1?1:2");
    }

    @Test
    public void testTernaryExpression03() {
        testVisitorExpr("1 === '1' ? 1 : 2", "1=='1'?1:2");
    }

    @Test
    public void testAndExpression01() {
        testVisitorExpr("1 && 2", "1&&2");
    }

    @Test
    public void testOrExpression01() {
        testVisitorExpr("1 || 2", "1||2");
    }

    @Test
    public void testScriptLet06() {
        testVisitorExpr("{\"a\":1,\"b\":2};", "[\"a\":1,\"b\":2];");
    }

    @Test
    public void testScriptLet07() {
        testVisitorExpr("{\"a\":1,\"c\": {}};", "[\"a\":1,\"c\":[:]];");
    }

    @Test
    public void testScriptLet08() {
        testVisitorExpr("{\n" +
                        "    \"arr\": [\n" +
                        "        {\n" +
                        "            \"arrObj1\": 123\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"arrObj2\": 123\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}",
                "[\"arr\":[[\"arrObj1\":123],[\"arrObj2\":123]]]");
    }

    @Test
    public void testScriptLet09() {
        testVisitorExpr("[\n" +
                        "    {\n" +
                        "        \"a\": 1\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"a\": 2\n" +
                        "    }\n" +
                        "]",
                "[[\"a\":1],[\"a\":2]]");
    }

    @Test
    public void testScriptLet10() {
        testVisitorExpr("{  \r\n\t\"OPORGQRYX\": [\r\n    ]  \r\n}",
                "[\"OPORGQRYX\":[]]");
    }

    @Test
    public void testMemberDatExprFunc() {
        testVisitorExpr("a.b.c", "a.b.c");
    }

        @Test
    public void testOptionalExpression() {
        testVisitorExpr("a?.b?.c", "a?.b?.c");
    }

    @Test
    public void testMemberDatExprFunc1() {
        Assert.assertThrows(ExpressionParseException.class, () -> testVisitorExpr("a.b.includes(d)", "a.b.includes(d)"));
    }

    @Test
    public void testTemplateFunc() {
        Assert.assertThrows(ExpressionParseException.class, () -> testVisitorExpr("`My name is ${name} and I am ${age} years old.`", "a.b.includes(d)"));
    }

    /**
     * 测试遍历返回表达式是否正确
     *
     * @param expr       源表达式
     * @param expectExpr 输出表达式/期望表达式
     */
    private void testVisitorExpr(String expr, String expectExpr) {
        JavaScriptLexer lexer = new JavaScriptLexer(CharStreams.fromString(expr));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.exprProgram();

        List<String> dynamicFunction = new ArrayList<>();
        dynamicFunction.add("MAX");
        dynamicFunction.add("MIN");
        ExpressionVisitor extendVisitor = new ExpressionVisitor(dynamicFunction);
        Assert.assertEquals(expectExpr, extendVisitor.visit(tree).toString());
    }
}
