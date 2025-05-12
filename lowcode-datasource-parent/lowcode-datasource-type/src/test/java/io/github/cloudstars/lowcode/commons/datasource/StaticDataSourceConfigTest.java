package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.CommonsDataSourceTestApplication;
import io.github.cloudstars.lowcode.commons.datasource.config.StaticDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataSourceTestApplication.class)
public class StaticDataSourceConfigTest {

    @Test
    public void testText() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("datasource/static-text1.json");
        StaticDataSourceConfig dataSourceConfig = new StaticDataSourceConfig(configJson);
        JsonTestUtils.assertEquals(configJson, dataSourceConfig.toJson());
    }


    @Test
    public void testList() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("datasource/static-list1.json");
        StaticDataSourceConfig dataSourceConfig = new StaticDataSourceConfig(configJson);
        JsonTestUtils.assertEquals(configJson, dataSourceConfig.toJson());
    }

}
