package io.github.cloudstars.lowcode.object.view.commons.form;


import io.github.cloudstars.lowcode.ObjectViewCommonsTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.object.view.commons.config.form.FormViewConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectViewCommonsTestApplication.class)
public class FromViewConfigTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form/simple.json");
        FormViewConfig viewConfig = new FormViewConfig(configJson);
        JsonTestUtils.assertDerivedFrom(configJson, viewConfig.toJson());
    }

}
