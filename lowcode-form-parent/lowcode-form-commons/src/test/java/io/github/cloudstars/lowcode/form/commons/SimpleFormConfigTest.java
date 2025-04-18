package io.github.cloudstars.lowcode.form.commons;

import io.github.cloudstars.lowcode.FormCommonsTestApplication;
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
@SpringBootTest(classes = FormCommonsTestApplication.class)
public class SimpleFormConfigTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-simple.json");
        FormConfig formConfig = new FormConfig(configJson);
        JsonTestUtils.assertDerivedFrom(configJson, formConfig.toJson());
    }

}
