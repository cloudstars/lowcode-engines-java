package io.github.cloudstars.lowcode.commons.descriptor;

import io.github.cloudstars.lowcode.CommonsDescriptorTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDescriptorTestApplication.class)
public class ConfigDescriptorUtilsTest {

    @Test
    public void testFromJson() {
        XConfigDescriptor<SomeConfig> descriptor = new SomeConfigDescriptor();
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config/SomeConfig1.json");
        SomeConfig config = ConfigDescriptorUtils.build(descriptor, configJson);
        JsonObject configJsonOutput = ConfigDescriptorUtils.toJson(descriptor, config);
        JsonTestUtils.assertEquals(configJson, configJsonOutput);
    }

}
