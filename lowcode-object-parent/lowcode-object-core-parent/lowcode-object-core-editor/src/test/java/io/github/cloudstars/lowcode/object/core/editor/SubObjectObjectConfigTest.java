package io.github.cloudstars.lowcode.object.core.editor;

import io.github.cloudstars.lowcode.ObjectCoreEditorTestApplication;
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
@SpringBootTest(classes = ObjectCoreEditorTestApplication.class)
public class SubObjectObjectConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object-subobject.json");
        FormBasedObjectConfig objectConfig = new FormBasedObjectConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectConfig.toJson());
        System.out.println(configJson.toJsonString());
    }

    @Test
    public void test0() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object-subobject-0.json");
        FormBasedObjectConfig objectConfig = new FormBasedObjectConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectConfig.toJson());
        System.out.println(objectConfig.toJson().toJsonString());
    }

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object-subobject-1.json");
        FormBasedObjectConfig objectConfig = new FormBasedObjectConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectConfig.toJson());
    }

}
