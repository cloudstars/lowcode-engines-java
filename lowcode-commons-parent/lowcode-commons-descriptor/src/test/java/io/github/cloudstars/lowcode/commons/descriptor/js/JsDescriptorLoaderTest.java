package io.github.cloudstars.lowcode.commons.descriptor.js;

import io.github.cloudstars.lowcode.CommonsDescriptorTestApplication;
import io.github.cloudstars.lowcode.commons.descriptor.ConfigDescriptorFactory;
import io.github.cloudstars.lowcode.commons.descriptor.SomeConfig;
import io.github.cloudstars.lowcode.commons.descriptor.SomeConfigAttribute;
import io.github.cloudstars.lowcode.commons.descriptor.XConfigDescriptor;
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
@SpringBootTest(classes = CommonsDescriptorTestApplication.class)
public class JsDescriptorLoaderTest {

    @Test
    public void test1() {
        String filePath = "descriptors/descriptor.js";
        XConfigDescriptor descriptor = JsConfigDescriptorLoader.loadFromClassPath(filePath);
        Assert.assertNotNull(descriptor);

        String refFilePath = "descriptors/descriptorRef.js";
        XConfigDescriptor descriptorRef = JsConfigDescriptorLoader.loadFromClassPath(refFilePath);
        Assert.assertNotNull(descriptorRef);
    }

    @Test
    public void test2() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config/SomeConfig1.json");
        XConfigDescriptor descriptor = ConfigDescriptorFactory.get("descriptor");
        Object configObject = ConfigParserUtils.fromJson(descriptor, configJson);
        Assert.assertNotNull(configObject);
        Assert.assertEquals(SomeConfig.class, configObject.getClass());
        SomeConfig someConfig = (SomeConfig) configObject;
        Assert.assertEquals("abc", someConfig.getX());
        List<SomeConfigAttribute> items = someConfig.getItems();
        Assert.assertEquals(2, items.size());

        JsonTestUtils.assertDerivedFrom(configJson.toJsonString(), someConfig.toJson().toJsonString());
    }

}
