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
public class OrgValueTypeConfigTest {

    /**
     * 测试一个简单的机构
     */
    @Test
    public void testSimple() {
        JsonObject configJson= JsonUtils.loadJsonObjectFromClasspath("value/type/org/org-simple.json");
        OrgValueTypeConfig valueType = new OrgValueTypeConfig(configJson);
        JsonTestUtils.assertEquals(configJson, valueType.toJson());
    }

}
