package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.FormulaParserTestApplication;
import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaParserTestApplication.class)
public class FormulaParserTest {

    private static final String expr0 = "a + 1";

    @Test
    public void test0() {
        FxExprParser.ProgramContext context = FormulaParser.parse(expr0);
        System.out.println(context.getChildCount());
    }

}
