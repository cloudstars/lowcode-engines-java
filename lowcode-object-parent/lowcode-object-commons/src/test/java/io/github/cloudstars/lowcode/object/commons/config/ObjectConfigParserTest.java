package io.github.cloudstars.lowcode.object.commons.config;

import io.github.cloudstars.lowcode.ObjectCommonsTestApplication;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectCommonsTestApplication.class)
public class ObjectConfigParserTest {

    @Test
    public void test1() {
        String configJsonString = FileTestUtils.loadTextFromClasspath("config/object1.json");
        ObjectConfig config = ObjectConfigParser.parse(configJsonString);
        JsonTestUtils.assertDerivedFrom(configJsonString, config.toJson().toJsonString());
    }

}
