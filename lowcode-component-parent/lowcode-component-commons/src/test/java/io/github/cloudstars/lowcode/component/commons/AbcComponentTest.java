package io.github.cloudstars.lowcode.component.commons;

import io.github.cloudstars.lowcode.ComponentCommonsTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ComponentCommonsTestApplication.class)
public class AbcComponentTest {

    @Test
    public void test0() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("abc-0.json");
        XComponentConfig componentConfig = ComponentConfigFactory.newInstance(configJson);
        JsonTestUtils.assertEquals(configJson, componentConfig.toJson());
    }

}
