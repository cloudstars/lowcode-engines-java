package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class TextArrayValueTypeParserTest {

    @Resource
    private ValueTypeParser parser;

    /**
     * 测试一个简单的文本
     */
    @Test
    public void testArray() {
        Map<String, Object> configMap = JsonTestUtils.loadMapFromClasspath("value/array/text-array.json");
        JsonObject configJson = new JsonObject(configMap);
        ValueTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(ArrayValueTypeConfig.class, valueType.getClass());
        ArrayValueTypeConfig arrayValueType = (ArrayValueTypeConfig) valueType;
        Assert.assertEquals(true, arrayValueType.isRequired());

        ValueTypeConfig itemValueTypeConfig = arrayValueType.getItemValueType();
        TextValueTypeConfig textItemValueType = (TextValueTypeConfig) itemValueTypeConfig;
        Assert.assertEquals(TextValueTypeConfig.class, textItemValueType.getClass());
        Assert.assertEquals(false, textItemValueType.isRequired());
        Assert.assertEquals("这是一个文本数据，缺省值为abc", textItemValueType.getRemark());
        Assert.assertNull(textItemValueType.getDefaultValue());
    }

}
