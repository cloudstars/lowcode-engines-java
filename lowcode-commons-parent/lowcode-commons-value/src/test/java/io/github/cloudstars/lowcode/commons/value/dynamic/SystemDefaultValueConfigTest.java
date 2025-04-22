package io.github.cloudstars.lowcode.commons.value.dynamic;

import io.github.cloudstars.lowcode.CommonsValueDynamicTestApplication;
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
@SpringBootTest(classes = CommonsValueDynamicTestApplication.class)
public class SystemDefaultValueConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("value/dynamic/simple-1.json");
        XValueConfig valueConfig = ValueConfigFactory.newInstance(configJson);
        JsonTestUtils.assertEquals(configJson, valueConfig.toJson());
    }

}
