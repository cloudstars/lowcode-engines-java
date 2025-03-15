package io.github.cloudstars.lowcode.bpm.editor;

import io.github.cloudstars.lowcode.BpmEditorTestApplication;
import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.editor.parser.ProcessConfigParser;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BpmEditorTestApplication.class)
public class ProcessConfigParserTest {

    @Resource
    private ProcessConfigParser parser;

    @Test
    public void testSimple1() {
        String configJson = FileTestUtils.loadTextFromClasspath("process/simple1.json");
        ProcessConfig processConfig = this.parser.fromJsonString(configJson);
        Assert.assertNotNull(processConfig);
        JsonTestUtils.assertDerivedFrom(configJson, processConfig.toJson().toJsonString());
    }

}
