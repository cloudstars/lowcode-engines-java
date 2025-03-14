package io.github.cloudstars.lowcode.bpm.editor;

import io.github.cloudstars.lowcode.bpm.editor.visit.BpmConfigParser;
import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;

public class BpmConfigParserTest {

    public void test1() {
        String configJson = FileTestUtils.loadTextFromClasspath("simple1.json");
        ProcessConfig processConfig = BpmConfigParser.parse(configJson);
    }
}
