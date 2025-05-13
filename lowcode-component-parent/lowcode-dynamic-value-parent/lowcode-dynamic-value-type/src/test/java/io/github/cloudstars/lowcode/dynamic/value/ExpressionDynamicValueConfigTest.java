package io.github.cloudstars.lowcode.dynamic.value;

import io.github.cloudstars.lowcode.DynamicValueTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicValueTestApplication.class)
public class ExpressionDynamicValueConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("simple-1.json");
        XDynamicValueConfig valueConfig = DynamicValueConfigFactory.newInstance(configJson);
        JsonTestUtils.assertEquals(configJson, valueConfig.toJson());
    }

}
