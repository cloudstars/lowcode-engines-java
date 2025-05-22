package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.FormulaEngineTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaEngineTestApplication.class)
public class NumberLiteralFormulaTest {

    @Resource
    private FormulaExecutor executor;

    @Test
    public void testNumber() {
        String fx = "123";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(123, result);
    }

    @Test
    public void testNumberAdd() {
        String fx = "1 + 1";
        Object result = this.executor.execute(fx);
        Assert.assertEquals(2, result);
    }

}
