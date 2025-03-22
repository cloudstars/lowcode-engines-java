package io.github.cloudstars.lowcode.commons.editor;

import io.github.cloudstars.lowcode.CommonsEditorTestApplication;
import io.github.cloudstars.lowcode.commons.lang.config.ConfigParserUtils;
import io.github.cloudstars.lowcode.commons.lang.config.XDescriptor;
import io.github.cloudstars.lowcode.commons.lang.config.XDescriptorFactory;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsEditorTestApplication.class)
public class JsDescriptorLoaderTest {

    @Test
    public void test1() {
        String filePath = "descriptors/descriptor.js";
        XDescriptor descriptor = JsConfigDescriptorLoader.loadFromClassPath(filePath);
        Assert.assertNotNull(descriptor);

        String refFilePath = "descriptors/descriptorRef.js";
        XDescriptor descriptorRef = JsConfigDescriptorLoader.loadFromClassPath(refFilePath);
        Assert.assertNotNull(descriptorRef);
    }

    @Test
    public void test2() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config/SomeConfig1.json");
        XDescriptor descriptor = XDescriptorFactory.get("descriptor");
        Object configObject = ConfigParserUtils.fromJson(descriptor, configJson);
        Assert.assertNotNull(configObject);
        Assert.assertEquals(SomeConfig.class, configObject.getClass());
        SomeConfig someConfig = (SomeConfig) configObject;
        Assert.assertEquals("abc", someConfig.getX());
        List<SomeAttribute> items = someConfig.getItems();
        Assert.assertEquals(2, items.size());

        JsonTestUtils.assertDerivedFrom(configJson.toJsonString(), someConfig.toJson().toJsonString());
    }

}
