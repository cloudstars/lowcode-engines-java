
package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.CommonsValueTypeTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsValueTypeTestApplication.class)
public class NumberValueTypeConfigTest {

    /**
     * 测试一个简单的数字
     */
    @Test
    public void testSimple() {
        JsonObject configJson= JsonUtils.loadJsonObjectFromClasspath("value/type/number/number-simple.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(NumberValueTypeConfig.class, valueType.getClass());
        JsonTestUtils.assertEquals(configJson, valueType.toJson());
    }

}
