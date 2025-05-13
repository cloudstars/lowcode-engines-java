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
public class PageListValueTypeConfigTest {

    /**
     * 测试一个简单的分页列表数据格式
     */
    @Test
    public void testSimple() {
        JsonObject configJson= JsonUtils.loadJsonObjectFromClasspath("value/type/page-list/page-list-simple.json");
        PageListValueTypeConfig valueTypeConfig = new PageListValueTypeConfig(configJson);
        JsonTestUtils.assertEquals(configJson, valueTypeConfig.toJson());
    }

}
