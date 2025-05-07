package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.CommonsValueTypeTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsValueTypeTestApplication.class)
public class SelectorValueTypeConfigTest {

    /**
     * 测试一个简单的文本选择
     */
    @Test
    public void testSimple() {
        JsonObject configJson= JsonUtils.loadJsonObjectFromClasspath("value/type/selector/selector-text.json");
        SelectorValueTypeConfig<TextValueTypeConfig> valueType = new SelectorValueTypeConfig(configJson);
        JsonTestUtils.assertEquals(configJson, valueType.toJson());
    }

}
