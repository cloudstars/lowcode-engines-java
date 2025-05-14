package io.github.cloudstars.lowcode.object.commons;

import io.github.cloudstars.lowcode.ObjectCommonsTestApplication;
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
@SpringBootTest(classes = ObjectCommonsTestApplication.class)
public class SimpleObjectConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object-simple.json");
        FormBasedObjectConfig objectConfig = new FormBasedObjectConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectConfig.toJson());
    }

    @Test
    public void test0() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object-simple-0.json");
        FormBasedObjectConfig objectConfig = new FormBasedObjectConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectConfig.toJson());
    }

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object-simple-1.json");
        FormBasedObjectConfig objectConfig = new FormBasedObjectConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectConfig.toJson());
    }

}
