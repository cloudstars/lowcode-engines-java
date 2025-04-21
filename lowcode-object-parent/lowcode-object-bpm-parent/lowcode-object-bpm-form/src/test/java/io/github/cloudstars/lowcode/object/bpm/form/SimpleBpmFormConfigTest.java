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
public class SimpleBpmFormConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-simple.json");
        BpmFormConfig bpmFormConfig = new BpmFormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, bpmFormConfig.toJson());
    }

    @Test
    public void test0() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-simple-0.json");
        BpmFormConfig bpmFormConfig = new BpmFormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, bpmFormConfig.toJson());
    }

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-simple-1.json");
        BpmFormConfig bpmFormConfig = new BpmFormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, bpmFormConfig.toJson());
    }

    /**
     * 测试选项字段类型
     */
    @Test
    public void test2() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("form-simple-2.json");
        BpmFormConfig bpmFormConfig = new BpmFormConfig(configJson);
        JsonTestUtils.assertEquals(configJson, bpmFormConfig.toJson());
    }

}
