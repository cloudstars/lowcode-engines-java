package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.CommonsDataTestApplication;
import io.github.cloudstars.lowcode.commons.data.type.custom.FileValueTypeConfig;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataTestApplication.class)
public class FileValueTypeParserTest {

    @Resource
    private ValueTypeParser parser;

    /**
     * 测试一个简单的文件
     */
    @Test
    public void testSimple() {
        Map<String, Object> configMap = JsonTestUtils.loadMapFromClasspath("value/file/file-simple.json");
        JsonObject configJson = new JsonObject(configMap);
        ValueTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(FileValueTypeConfig.class, valueType.getClass());
        FileValueTypeConfig fileValueType = (FileValueTypeConfig) valueType;
        Assert.assertEquals(false, fileValueType.isRequired());
        Assert.assertEquals("这是一个文件数据，有缺省值", fileValueType.getRemark());
        String expectedDefaultValueString = "{\"key\":  \"kkk\", \"name\": \"nnn\"}";
        String actualDefaultValueString = JsonUtils.toJsonString(fileValueType.getDefaultValue());
        JsonTestUtils.assertDerivedFrom(expectedDefaultValueString, actualDefaultValueString);
    }

}
