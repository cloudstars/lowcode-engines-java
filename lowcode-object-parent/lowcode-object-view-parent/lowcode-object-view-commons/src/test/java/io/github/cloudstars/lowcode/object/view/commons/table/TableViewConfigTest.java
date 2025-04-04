package io.github.cloudstars.lowcode.object.view.commons.table;

import io.github.cloudstars.lowcode.ObjectViewCommonsTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.object.view.commons.config.table.TableViewConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectViewCommonsTestApplication.class)
public class TableViewConfigTest {

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("table/ordinary.json");
        TableViewConfig viewConfig = new TableViewConfig(configJson);
        JsonTestUtils.assertDerivedFrom(configJson.toJsonString(), viewConfig.toJson().toJsonString());
    }

}
