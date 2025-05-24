
package io.github.cloudstars.lowcode.predicate.type;

import io.github.cloudstars.lowcode.PredicateTypeTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PredicateTypeTestApplication.class)
public class SimpleFormulaPredicateConfigParserTest {

    /**
     * 测试一个简单的公式断言
     */
    @Test
    public void testSimple() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("formula/f1.json");
        FormulaPredicateConfig config = new FormulaPredicateConfig(configJson);
        JsonTestUtils.assertEquals(configJson, config.toJson());
    }

}
