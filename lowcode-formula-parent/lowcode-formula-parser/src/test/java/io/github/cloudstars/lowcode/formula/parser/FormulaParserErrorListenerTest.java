package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.FormulaParserTestApplication;
import io.github.cloudstars.lowcode.formula.parser.g4.FxExprParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaParserTestApplication.class)
public class FormulaParserErrorListenerTest {

    /**
     * 公式解析错误监听器
     */
    private static FormulaParseErrorListener ERROR_LISTENER = new FormulaParseErrorListener();

    @Test
    public void test() {
        FxExprParser.ProgramContext context = FormulaParser.parse("abc", Arrays.asList(ERROR_LISTENER));
        FormulaAstTestVisitor visitor = new FormulaAstTestVisitor();
        visitor.visitProgram(context);
    }

}
