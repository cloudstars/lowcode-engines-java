package io.github.cloudstars.lowcode.file.commons;

import io.github.cloudstars.lowcode.FileCommonsTestApplication;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileCommonsTestApplication.class)
public class FileTypeImplConfigTest {

    /**
     * 测试一个简单的文件
     */
    @Test
    public void testSimple() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/file-simple.json");
        XValueTypeConfig valueTypeConfig = ValueTypeConfigFactory.newInstance(configJson);
        Assert.assertEquals(FileValueTypeConfig.class, valueTypeConfig.getClass());
        JsonTestUtils.assertEquals(configJson, valueTypeConfig.toJson());
    }

}
