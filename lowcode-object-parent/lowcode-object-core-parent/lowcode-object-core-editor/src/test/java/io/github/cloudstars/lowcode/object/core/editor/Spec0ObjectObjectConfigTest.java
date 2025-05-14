package io.github.cloudstars.lowcode.object.core.editor;

import io.github.cloudstars.lowcode.ObjectCoreEditorTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.object.commons.FormBasedObjectConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectCoreEditorTestApplication.class)
public class Spec0ObjectObjectConfigTest {

    @Resource
    private Spec1ObjectConfigParser configParser;

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("object0-spec-1.json");
        FormBasedObjectConfig config = this.configParser.parse(configJson);
        JsonObject expectedConfigJson = JsonUtils.loadJsonObjectFromClasspath("object0-spec-2.json");
        JsonTestUtils.assertEquals(expectedConfigJson, config.toJson());
    }

}
