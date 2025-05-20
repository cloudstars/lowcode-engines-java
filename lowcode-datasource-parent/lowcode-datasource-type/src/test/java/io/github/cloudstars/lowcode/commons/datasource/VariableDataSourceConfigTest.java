
package io.github.cloudstars.lowcode.commons.datasource;

import io.github.cloudstars.lowcode.DataSourceTypeTestApplication;
import io.github.cloudstars.lowcode.commons.datasource.config.VariableDataSourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataSourceTypeTestApplication.class)
public class VariableDataSourceConfigTest {

    @Test
    public void testText() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("datasource/variable-text1.json");
        VariableDataSourceConfig dataSourceConfig = new VariableDataSourceConfig(configJson);
        JsonTestUtils.assertEquals(configJson, dataSourceConfig.toJson());
    }

}
