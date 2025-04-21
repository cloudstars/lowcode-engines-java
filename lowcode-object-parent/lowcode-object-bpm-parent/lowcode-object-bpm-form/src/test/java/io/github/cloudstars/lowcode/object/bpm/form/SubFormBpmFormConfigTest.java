package io.github.cloudstars.lowcode.object.bpm.form;

import io.github.cloudstars.lowcode.ObjectBpmFormTestApplication;
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
@SpringBootTest(classes = ObjectBpmFormTestApplication.class)
public class SubFormBpmFormConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-subform.json");
        BpmFormConfig bpmFormConfig = new BpmFormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, bpmFormConfig.toJson());
        System.out.println(configJson.toJsonString());
    }

    @Test
    public void test0() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-subform-0.json");
        BpmFormConfig bpmFormConfig = new BpmFormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, bpmFormConfig.toJson());
        System.out.println(bpmFormConfig.toJson().toJsonString());
    }

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-subform-1.json");
        BpmFormConfig bpmFormConfig = new BpmFormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, bpmFormConfig.toJson());
    }

}
