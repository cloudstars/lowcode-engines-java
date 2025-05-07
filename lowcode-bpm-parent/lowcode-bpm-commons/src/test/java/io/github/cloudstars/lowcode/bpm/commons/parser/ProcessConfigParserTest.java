package io.github.cloudstars.lowcode.bpm.commons.parser;

import io.github.cloudstars.lowcode.BpmCommonsTestApplication;
import io.github.cloudstars.lowcode.bpm.commons.config.ProcessConfig;
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
@SpringBootTest(classes = BpmCommonsTestApplication.class)
public class ProcessConfigParserTest {


    @Test
    public void testSimple1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("process/simple1.json");
        ProcessConfig processConfig = new ProcessConfig(configJson);
        JsonTestUtils.assertEquals(configJson, processConfig.toJson());
    }

}
