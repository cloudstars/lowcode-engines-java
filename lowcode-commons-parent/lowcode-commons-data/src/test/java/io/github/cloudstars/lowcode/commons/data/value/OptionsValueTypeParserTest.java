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

/**
 * 选项数据格式测试类
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class OptionsValueTypeParserTest {

    @Resource
    private ValueTypeParser parser;

    /**
     * 测试一个简单的选项
     */
    @Test
    public void testSimple() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/option/option-simple.json");
        ValueTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(OptionValueTypeConfig.class, valueType.getClass());
        OptionValueTypeConfig optionValueType = (OptionValueTypeConfig) valueType;
        Assert.assertEquals(false, optionValueType.isRequired());
        Assert.assertEquals("这是一个选项数据，有缺省值", optionValueType.getRemark());
        String expectedDefaultValueString = "{\"label\":  \"lll\", \"value\": \"vvv\"}";
        String actualDefaultValueString = JsonUtils.toJsonString(optionValueType.getDefaultValue());
        JsonTestUtils.assertDerivedFrom(expectedDefaultValueString, actualDefaultValueString);
    }

}
