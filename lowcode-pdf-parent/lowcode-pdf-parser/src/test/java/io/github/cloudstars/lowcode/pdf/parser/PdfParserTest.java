package io.github.cloudstars.lowcode.pdf.parser;

import io.github.cloudstars.lowcode.PdfPaserTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.pdf.commons.config.PdfBuildConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PdfPaserTestApplication.class)
public class PdfParserTest {

    @Resource
    private PdfBuildConfigParser parser;

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("pdf1.json");
        PdfBuildConfig config = this.parser.parse(configJson);
        JsonTestUtils.assertEquals(configJson, config.toJson());
    }

}
