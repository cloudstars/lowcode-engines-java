package io.github.cloudstars.lowcode.value.type;

import io.github.cloudstars.lowcode.CommonsValueTypeTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 树形选项选项数据格式测试类
 *
 * @author clouds
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsValueTypeTestApplication.class)
public class TreeOptionValueTypeConfigTest {

    /**
     * 测试一个简单的树形选项
     */
    @Test
    public void testSimple() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/type/tree-option/tree-option-simple.json");
        TreeOptionValueTypeConfig valueType = new TreeOptionValueTypeConfig(configJson);
        JsonTestUtils.assertEquals(configJson, valueType.toJson());
    }

}
