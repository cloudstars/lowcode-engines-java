package io.github.cloudstars.lowcode.object.form.editor;

import io.github.cloudstars.lowcode.ObjectFormEditorTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.object.form.editor.view.insert.ObjectInsertFormViewConfig;
import io.github.cloudstars.lowcode.object.view.editor.XObjectViewConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectFormEditorTestApplication.class)
public class ObjectInsertFormViewConfigTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("/form/form-insert-1.json");
        XObjectViewConfig objectViewConfig = new ObjectInsertFormViewConfig(configJson);
        JsonTestUtils.assertEquals(configJson, objectViewConfig.toJson());
    }
}
