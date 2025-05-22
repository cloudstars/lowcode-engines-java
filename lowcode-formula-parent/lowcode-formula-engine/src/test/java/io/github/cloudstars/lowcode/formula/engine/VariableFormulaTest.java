package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.FormulaEngineTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaEngineTestApplication.class)
public class VariableFormulaTest {

    private static final String expr1 = "a";

    private static final String expr2 = "a + b";

    private static final String expr3 = "(a + 1) * b";

    @Resource
    private FormulaExecutor executor;

    @Test
    public void testNumber() {
        Formula formula = this.executor.compile(expr1);

        Map<String, Object> dataMap1 = new HashMap<>();
        dataMap1.put("a", 123);
        Object result1 = formula.execute(dataMap1);
        Assert.assertEquals(123, result1);

        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap2.put("a", -0.5f);
        Object result2 = formula.execute(dataMap2);
        Assert.assertEquals(-0.5f, result2);

        Map<String, Object> dataMap3 = new HashMap<>();
        dataMap3.put("a", -0.5d);
        Object result3 = formula.execute(dataMap3);
        Assert.assertEquals(-0.5d, result3);
    }


}
