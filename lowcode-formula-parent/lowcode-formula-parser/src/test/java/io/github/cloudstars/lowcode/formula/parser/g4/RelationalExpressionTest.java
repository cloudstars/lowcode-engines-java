package io.github.cloudstars.lowcode.formula.parser.g4;

import io.github.cloudstars.lowcode.FormulaParserTestApplication;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import io.github.cloudstars.lowcode.formula.parser.FormulaParser;
import io.github.cloudstars.lowcode.formula.parser.SyntaxException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaParserTestApplication.class)
public class RelationalExpressionTest {

    @Test
    public void test() {
        List<String> fxs = FileTestUtils.loadFileLinesFromClassPath("relational.fx", true);
        for (String fx : fxs) {
            FormulaParser.parse(fx);
        }
    }

    @Test
    public void testErrors() {
        List<String> fxs = FileTestUtils.loadFileLinesFromClassPath("relational-errors.fx", true);
        for (String fx : fxs) {
            Assert.assertThrows(SyntaxException.class, () -> {
                FormulaParser.parse(fx);
            });
        }
    }

}
