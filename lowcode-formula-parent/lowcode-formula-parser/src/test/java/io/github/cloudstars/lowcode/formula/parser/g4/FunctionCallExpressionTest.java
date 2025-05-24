package io.github.cloudstars.lowcode.formula.parser.g4;

import io.github.cloudstars.lowcode.FormulaParserTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import io.github.cloudstars.lowcode.formula.parser.FormulaParser;
import io.github.cloudstars.lowcode.formula.parser.SyntaxException;
import io.github.cloudstars.lowcode.formula.parser.function.FunctionConfig;
import io.github.cloudstars.lowcode.formula.parser.function.FunctionConfigFactory;
import io.github.cloudstars.lowcode.formula.parser.function.FunctionConfigParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 函数调用规则测试
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FormulaParserTestApplication.class)
public class FunctionCallExpressionTest {

    @Before
    public void registerFunctions() {
        // 加载函数配置并注册
        FunctionConfigParser parser = new FunctionConfigParser();
        JsonArray configJsonArray = JsonUtils.loadJsonArrayFromClasspath("functions.json");
        for (Object object : configJsonArray) {
            FunctionConfig functionConfig = parser.parse((JsonObject) object);
            FunctionConfigFactory.register(functionConfig);
        }
    }

    @Test
    public void test() {
        List<String> fxs = FileTestUtils.loadFileLinesFromClassPath("function-call.fx", true);
        for (String fx : fxs) {
            FormulaParser.parse(fx);
        }
    }

    @Test
    public void testErrors() {
        List<String> fxs = FileTestUtils.loadFileLinesFromClassPath("function-call-errors.fx", true);
        for (String fx : fxs) {
            Assert.assertThrows(SyntaxException.class, () -> {
                FormulaParser.parse(fx);
            });
        }
    }

}
