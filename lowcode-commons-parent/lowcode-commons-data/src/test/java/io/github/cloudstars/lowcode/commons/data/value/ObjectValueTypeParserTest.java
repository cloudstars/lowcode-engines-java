
package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class ObjectValueTypeParserTest {

    @Resource
    private ValueTypeParser parser;

    /**
     * 测试一个简单的文本对象
     */
    @Test
    public void testTextObjectValueType() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/object/object-simple.json");
        ValueTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(ObjectValueTypeConfig.class, valueType.getClass());
        ObjectValueTypeConfig objectValueType = (ObjectValueTypeConfig) valueType;

        Assert.assertEquals(false, objectValueType.isRequired());
        Assert.assertEquals("这是一个对象数据，有缺省值", objectValueType.getRemark());
        JsonTestUtils.assertDerivedFrom("{\"a\": \"x\", \"b\": \"y\"}", JsonUtils.toJsonString(objectValueType.getDefaultValue()));

        List<ObjectValueProperty> properties = objectValueType.getProperties();
        Assert.assertEquals(2, properties.size());
        Assert.assertEquals("a", properties.get(0).getName());
        Assert.assertEquals("b", properties.get(1).getName());
    }

}
