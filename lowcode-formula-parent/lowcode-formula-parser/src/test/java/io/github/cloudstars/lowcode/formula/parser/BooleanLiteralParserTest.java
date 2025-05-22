package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.FormulaParserTestApplication;
import io.github.cloudstars.lowcode.formula.parser.g4.FxParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaParserTestApplication.class)
public class BooleanLiteralParserTest {

    private static final String expr1 = "1 + 2";

    private static final String expr2 = "2 * 3";

    private static final String expr3 = "(1 +2) * 3";

    @Test
    public void test1() {
        FxParser.FxContext context = FormulaParser.parse(expr1);
        Assert.assertNotNull(context);
    }

    @Test
    public void test2() {
        FxParser.FxContext context = FormulaParser.parse(expr2);
        Assert.assertNotNull(context);
    }

    @Test
    public void test3() {
        FxParser.FxContext context = FormulaParser.parse(expr3);
        Assert.assertNotNull(context);
    }

}
