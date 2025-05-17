package io.github.cloudstars.lowcode.object.table.editor;

import io.github.cloudstars.lowcode.ObjectViewEditorTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectViewEditorTestApplication.class)
public class ObjectCurdTableViewConfigTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("table/ordinary.json");
        XObjectViewConfig objectViewConfig = new ObjectCrudTableViewConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectViewConfig.toJson());
    }
}
