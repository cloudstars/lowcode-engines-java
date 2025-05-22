package io.github.cloudstars.lowcode.formula.parser;

import io.github.cloudstars.lowcode.FormulaParserTestApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaParserTestApplication.class)
public class JsonLiteralParserTest {

    private static final String expr1 = "1 + 2";

    private static final String expr2 = "2 * 3";

    private static final String expr3 = "(1 +2) * 3";


}
