package io.github.cloudstars.lowcode.object.method.editor;

import io.github.cloudstars.lowcode.ObjectMethodEditorTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectMethodEditorTestApplication.class)
public class ObjectInsertOneMethodConfigTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("insert/insert-one-1.json");
        XObjectMethodConfig methodConfig = ObjectMethodConfigFactory.newInstance(configJson);
        JsonTestUtils.assertEquals(configJson, methodConfig.toJson());
    }
}
