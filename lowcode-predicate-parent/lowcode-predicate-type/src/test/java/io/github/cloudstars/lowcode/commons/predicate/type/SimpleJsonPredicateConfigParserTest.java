
package io.github.cloudstars.lowcode.commons.predicate.type;

import io.github.cloudstars.lowcode.CommonsPredicateTypeTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.predicate.type.json.JsonPredicateConfigParser;
import io.github.cloudstars.lowcode.commons.predicate.type.json.XJsonPredicateConfig;
import io.github.cloudstars.lowcode.commons.predicate.type.json.binary.BinaryJsonPredicateConfig;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsPredicateTypeTestApplication.class)
public class SimpleJsonPredicateConfigParserTest {

    /**
     * 测试一个简单的二元操作JSON表达式
     */
    @Test
    public void testSimpleBinary() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("expression/simple-binary.json");
        XJsonPredicateConfig jsonPredicate = JsonPredicateConfigParser.fromJson(configJson);
        Assert.assertEquals(BinaryJsonPredicateConfig.class, jsonPredicate.getClass());
        JsonTestUtils.assertEquals(configJson, jsonPredicate.toJson());
    }

    /**
     * 测试一个简单的二元操作作逻辑操作的JSON表达式
     */
    @Test
    public void testSimpleLogic() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("expression/simple-logic.json");
        XJsonPredicateConfig jsonPredicate = JsonPredicateConfigParser.fromJson(configJson);
        Assert.assertEquals(BinaryJsonPredicateConfig.class, jsonPredicate.getClass());
        JsonTestUtils.assertEquals(configJson, jsonPredicate.toJson());
    }

}
