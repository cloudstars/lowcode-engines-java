package io.github.cloudstars.lowcode.object.form;

import io.github.cloudstars.lowcode.ObjectFormTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.object.form.config.FormConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectFormTestApplication.class)
public class JsonFormConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-json.json");
        FormConfig formConfig = new FormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, formConfig.toJson());
    }

    @Test
    public void test0() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-json-0.json");
        FormConfig formConfig = new FormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, formConfig.toJson());
    }

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-json-1.json");
        FormConfig formConfig = new FormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, formConfig.toJson());
    }

    @Test
    public void test2() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-json-2.json");
        FormConfig formConfig = new FormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, formConfig.toJson());
    }

}
