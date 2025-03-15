package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.CommonsEditorTestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsEditorTestApplication.class)
public class JsFunctionDescriptorLoaderTest {

    @Test
    public void test1() {
        String filePath = "descriptors/descriptor1.js";
        XDescriptor descriptor1 = JsFunctionDescriptorLoader.loadFromClassPath(filePath);
        Assert.assertNotNull(descriptor1);
    }

}
