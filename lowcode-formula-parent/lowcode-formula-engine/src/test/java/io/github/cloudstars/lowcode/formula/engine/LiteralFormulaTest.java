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
public class LiteralFormulaTest {

    @Resource
    private FormulaExecutor executor;

    @Test
    public void test() {
        String fx0 = "123";
        Object result = this.executor.execute(fx0);
        Assert.assertEquals(fx0, result);
    }

}
