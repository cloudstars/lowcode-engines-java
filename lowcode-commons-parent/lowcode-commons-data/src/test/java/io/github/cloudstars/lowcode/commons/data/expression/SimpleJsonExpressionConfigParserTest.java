
package io.github.cloudstars.lowcode.commons.data.expression;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.data.expression.json.BinaryJsonExpression;
import io.github.cloudstars.lowcode.commons.data.expression.json.JsonExpressParser;
import io.github.cloudstars.lowcode.commons.data.expression.json.JsonExpression;
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
public class SimpleJsonExpressionConfigParserTest {

    @Resource
    private JsonExpressParser parser;

    /**
     * 测试一个简单的二元操作JSON表达式
     */
    @Test
    public void testSimpleBinary() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("expression/simple-binary.json");
        JsonExpression jsonExpression = this.parser.fromJson(configJson);
        Assert.assertEquals(BinaryJsonExpression.class, jsonExpression.getClass());
        JsonTestUtils.assertDerivedFrom(configJson, jsonExpression.toJson());
    }

}
