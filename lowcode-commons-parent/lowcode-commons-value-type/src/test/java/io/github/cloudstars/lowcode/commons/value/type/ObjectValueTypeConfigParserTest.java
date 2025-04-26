
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
public class ObjectValueTypeConfigParserTest {

    /**
     * 测试一个简单的文本对象
     */
    @Test
    public void testTextObjectValueType() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/type/object/object-simple.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(ObjectValueTypeConfig.class, valueType.getClass());
        JsonTestUtils.assertEquals(configJson, valueType.toJson());
    }

}
