package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.CommonsApiTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsApiTestApplication.class)
public class ApiConfigParserTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("config/options-api.json");
        ApiConfig apiConfig = new ApiConfig(configJson);
        JsonTestUtils.assertDerivedFrom(configJson.toJsonString(), apiConfig.toJson().toJsonString());
    }

}
