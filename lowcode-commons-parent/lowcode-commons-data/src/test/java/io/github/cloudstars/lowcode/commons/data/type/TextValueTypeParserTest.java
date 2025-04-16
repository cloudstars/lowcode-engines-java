package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class TextValueTypeParserTest {

    @Resource
    private DataTypeParser parser;

    /**
     * 测试一个简单的文本
     */
    @Test
    public void testSimple() {
        Map<String, Object> configMap = JsonTestUtils.loadMapFromClasspath("datatype/string/string-simple.json");
        JsonObject configJson = new JsonObject(configMap);
        ValueTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(StringValueTypeConfig.class, valueType.getClass());
        StringValueTypeConfig textValueType = (StringValueTypeConfig) valueType;
        Assert.assertEquals(false, textValueType.isRequired());
        Assert.assertEquals("这是一个文本数据，缺省值为abc", textValueType.getRemark());
        Assert.assertEquals("abc", textValueType.getDefaultValue());
    }

}
