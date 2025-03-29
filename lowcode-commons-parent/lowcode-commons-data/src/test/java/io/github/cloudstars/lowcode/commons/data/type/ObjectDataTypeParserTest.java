
package io.github.cloudstars.lowcode.commons.data.type;

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
public class ObjectDataTypeParserTest {

    @Resource
    private DataTypeParser parser;

    /**
     * 测试一个简单的文本对象
     */
    @Test
    public void testTextObjectValueType() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/object/object-simple.json");
        DataTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(ObjectDataTypeConfig.class, valueType.getClass());
        ObjectDataTypeConfig objectValueType = (ObjectDataTypeConfig) valueType;

        Assert.assertEquals(false, objectValueType.isRequired());
        Assert.assertEquals("这是一个对象数据，有缺省值", objectValueType.getRemark());
        JsonTestUtils.assertDerivedFrom("{\"a\": \"x\", \"b\": \"y\"}", JsonUtils.toJsonString(objectValueType.getDefaultValue()));

        List<ObjectProperty> properties = objectValueType.getProperties();
        Assert.assertEquals(2, properties.size());
        Assert.assertEquals("a", properties.get(0).getName());
        Assert.assertEquals("b", properties.get(1).getName());
    }

}
