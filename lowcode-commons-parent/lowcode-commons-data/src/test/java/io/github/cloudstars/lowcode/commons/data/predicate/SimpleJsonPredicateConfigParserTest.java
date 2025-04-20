
package io.github.cloudstars.lowcode.commons.data.predicate;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.data.predicate.json.BinaryJsonPredicate;
import io.github.cloudstars.lowcode.commons.data.predicate.json.JsonPredicateConfigParser;
import io.github.cloudstars.lowcode.commons.data.predicate.json.XJsonPredicate;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class SimpleJsonPredicateConfigParserTest {

    @Resource
    private JsonPredicateConfigParser parser;

    /**
     * 测试一个简单的二元操作JSON表达式
     */
    @Test
    public void testSimpleBinary() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("expression/simple-binary.json");
        XJsonPredicate jsonPredicate = this.parser.fromJson(configJson);
        Assert.assertEquals(BinaryJsonPredicate.class, jsonPredicate.getClass());
        JsonTestUtils.assertEquals(configJson, jsonPredicate.toJson());
    }

}
