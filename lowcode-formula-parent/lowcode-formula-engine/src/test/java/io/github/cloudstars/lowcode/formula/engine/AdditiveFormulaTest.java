package io.github.cloudstars.lowcode.formula.engine;

import io.github.cloudstars.lowcode.FormulaEngineTestApplication;
import io.github.cloudstars.lowcode.commons.lang.util.CalculateUtils;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaEngineTestApplication.class)
public class AdditiveFormulaTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdditiveFormulaTest.class);

    @Resource
    private FormulaExecutor executor;

    @Test
    public void test() {
        List<String> lines = FileTestUtils.loadFileLinesFromClassPath("additive.fx", true);
        for (String line : lines) {
            LOGGER.info("当前行：{}", line);
            String[] parts = line.split("=");
            Object result = this.executor.execute(parts[0]);
            Object expectedResult = CalculateUtils.parseNumber(parts[1]);
            Assert.assertEquals(expectedResult, result);
        }
    }

}
