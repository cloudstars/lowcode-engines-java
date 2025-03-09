package io.github.cloudstars.lowcode.object.editor;

import io.github.cloudstars.lowcode.ObjectEditorTestApplication;
import io.github.cloudstars.lowcode.commons.test.util.FileTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectEditorTestApplication.class)
public class ObjectConfigParserTest {


    @Test
    public void test1() {
        String configJsonString = FileTestUtils.loadTextFromClasspath("config/object1.json");

        ObjectConfigParser.parse(configJsonString);
    }
}
