package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.CommonsDataSourceLoaderTestApplication;
import io.github.cloudstars.lowcode.commons.datasource.config.StaticDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommonsDataSourceLoaderTestApplication.class)
public class StaticDataSourceLoaderTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("datasource/static-text1.json");
        StaticDataSourceConfig dataSourceConfig = new StaticDataSourceConfig(configJson);
        StaticDataSourceLoader loader = new StaticDataSourceLoader(dataSourceConfig);
        Object data = loader.loadData(null);
        Assert.assertEquals(String.class, data.getClass());
        Assert.assertEquals("这是B的内容", data);
    }

}
