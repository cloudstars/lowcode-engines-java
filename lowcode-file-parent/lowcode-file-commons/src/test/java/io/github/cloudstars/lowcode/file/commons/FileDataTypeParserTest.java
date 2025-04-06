package io.github.cloudstars.lowcode.file.commons;

import io.github.cloudstars.lowcode.FileCommonsTestApplication;
import io.github.cloudstars.lowcode.commons.data.type.DataTypeConfig;
import io.github.cloudstars.lowcode.commons.data.type.DataTypeParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileCommonsTestApplication.class)
public class FileDataTypeParserTest {

    @Resource
    private DataTypeParser parser;

    /**
     * 测试一个简单的文件
     */
    @Test
    public void testSimple() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("datatype/file-simple.json");
        DataTypeConfig valueType = this.parser.fromJson(configJson);
        Assert.assertEquals(FileDataTypeConfig.class, valueType.getClass());
        FileDataTypeConfig fileValueType = (FileDataTypeConfig) valueType;
        Assert.assertEquals(false, fileValueType.isRequired());
        Assert.assertEquals("这是一个文件数据，有缺省值", fileValueType.getRemark());
        String expectedDefaultValueString = "{\"key\":  \"kkk\", \"name\": \"nnn\"}";
        String actualDefaultValueString = JsonUtils.toJsonString(fileValueType.getDefaultValue());
        JsonTestUtils.assertDerivedFrom(expectedDefaultValueString, actualDefaultValueString);
    }

}
