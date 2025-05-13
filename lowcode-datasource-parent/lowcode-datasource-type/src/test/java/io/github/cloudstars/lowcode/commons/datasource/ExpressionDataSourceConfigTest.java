
package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.DataSourceTypeTestApplication;
import io.github.cloudstars.lowcode.commons.datasource.config.ExpressionDataSourceConfig;
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
@SpringBootTest(classes = DataSourceTypeTestApplication.class)
public class ExpressionDataSourceConfigTest {

    @Test
    public void test() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("datasource/expression-1.json");
        ExpressionDataSourceConfig config = new ExpressionDataSourceConfig(configJson);
        JsonTestUtils.assertEquals(configJson, config.toJson());
    }

}
