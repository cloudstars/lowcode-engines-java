package io.github.cloudstars.lowcode.object.view.table;

import io.github.cloudstars.lowcode.ObjectViewTestApplication;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.test.util.JsonTestUtils;
import io.github.cloudstars.lowcode.object.view.table.config.TableViewConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ObjectViewTestApplication.class)
public class TableViewConfigParserTest {

    @Resource
    private TableViewConfigParser configParser;

    @Test
    public void test1() {
        JsonObject configJson = JsonUtils.loadJsonObjectFromClasspath("table/ordinary.json");
        TableViewConfig viewConfig = new TableViewConfig(configJson);
        JsonTestUtils.assertDerivedFrom(configJson.toJsonString(), viewConfig.toJson().toJsonString());
    }

}
