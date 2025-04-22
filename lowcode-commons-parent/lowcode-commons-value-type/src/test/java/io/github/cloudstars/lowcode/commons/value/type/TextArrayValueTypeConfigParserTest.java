package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.CommonsValueTestApplication;
import io.github.cloudstars.lowcode.commons.value.type.config.ArrayValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.config.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsValueTestApplication.class)
public class TextArrayValueTypeConfigParserTest {

    /**
     * 测试一个简单的文本
     */
    @Test
    public void testArray() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/type/array/text-array.json");
        XValueTypeConfig valueType = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(ArrayValueTypeConfig.class, valueType.getClass());
        JsonTestUtils.assertDerivedFrom(configJson, valueType.toJson());
    }

}
