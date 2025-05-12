package io.github.cloudstars.lowcode.pdf.commons;

import io.github.cloudstars.lowcode.PdfCommonsTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PdfCommonsTestApplication.class)
public class PdfBuildConfigTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("pdf1.json");
        PdfBuildConfig config = new PdfBuildConfig(configJson);
        JsonTestUtils.assertEquals(configJson, config.toJson());
    }

}
