package io.github.cloudstars.lowcode.commons.data.valuetype;

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
public class TextValueTypeConfigParserTest {

    @Resource
    private ValueTypeConfigParser parser;

    /**
     * 测试一个简单的文本
     */
    @Test
    public void testSimple() {
        Map<String, Object> configMap = JsonTestUtils.loadMapFromClasspath("valuetype/text/text-simple.json");
        JsonObject configJson = new JsonObject(configMap);
        XValueTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(TextValueTypeConfig.class, valueType.getClass());
        JsonTestUtils.assertDerivedFrom(configJson, valueType.toJson());
        // TextValueTypeConfig textValueType = (TextValueTypeConfig) valueType;
        //Assert.assertEquals(false, textValueType.isRequired());
        //Assert.assertEquals("这是一个文本数据，缺省值为abc", textValueType.getRemark());
        //Assert.assertEquals("abc", textValueType.getDefaultValue());
    }

}
