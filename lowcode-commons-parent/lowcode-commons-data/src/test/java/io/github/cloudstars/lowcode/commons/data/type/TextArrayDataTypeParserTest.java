package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class TextArrayDataTypeParserTest {

    @Resource
    private DataTypeParser parser;

    /**
     * 测试一个简单的文本
     */
    @Test
    public void testArray() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("datatype/array/text-array.json");
        DataTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(ArrayDataTypeConfig.class, valueType.getClass());
        ArrayDataTypeConfig arrayValueType = (ArrayDataTypeConfig) valueType;
        Assert.assertEquals(true, arrayValueType.isRequired());

        DataTypeConfig itemDataTypeConfig = arrayValueType.getItemsDataType();
        StringDataTypeConfig textItemValueType = (StringDataTypeConfig) itemDataTypeConfig;
        Assert.assertEquals(StringDataTypeConfig.class, textItemValueType.getClass());
        Assert.assertEquals(false, textItemValueType.isRequired());
        Assert.assertEquals("这是一个文本数据，缺省值为abc", textItemValueType.getRemark());
        Assert.assertNull(textItemValueType.getDefaultValue());
    }

}
