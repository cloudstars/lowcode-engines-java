package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.FormulaEngineTestApplication;
import io.github.cloudstars.lowcode.commons.lang.util.CalculateUtils;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaEngineTestApplication.class)
public class NumberLiteralFormulaTest {

    @Resource
    private FormulaExecutor executor;

    @Test
    public void test() {
        List<String> lines = FileTestUtils.loadFileLinesFromClassPath("numeric-literal.fx", true);
        for (String line : lines) {
            Object result = this.executor.execute(line);
            Object expectedResult = CalculateUtils.parseNumber(line);
            Assert.assertEquals(expectedResult, result);
        }
    }

    @Test
    public void testNumber() {
        String fx1 = "123";
        Object result1 = this.executor.execute(fx1);
        Assert.assertEquals(123, result1);

        String fx2 = "0.5";
        Object result2 = this.executor.execute(fx2);
        Assert.assertEquals(0.5, result2);
    }

    @Test
    public void testNumberPlus() {
        String fx = "1 + 2";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(3, result);
    }

    @Test
    public void testNumberMinus() {
        String fx = "1 - 2";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testNumberMultiply() {
        String fx = "2 * 3";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(6, result);
    }

    @Test
    public void testNumberDivide() {
        String fx = "2 / 0.5";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(4.0, result);
    }

    @Test
    public void testParentheses1() {
        String fx = "3 * (1 + 2)";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(9, result);
    }

    @Test
    public void testParentheses() {
        String fx = "3 * (1 + 2) + (8 / 2)";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(13, result);
    }

}
