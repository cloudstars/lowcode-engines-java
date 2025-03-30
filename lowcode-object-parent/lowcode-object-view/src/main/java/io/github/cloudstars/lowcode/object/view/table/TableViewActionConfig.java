package io.github.cloudstars.lowcode.object.view.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.config.AbstractViewActionConfig;

/**
 * 表格视图操作配置
 *
 * @author clouds
 */
public class TableViewActionConfig extends AbstractViewActionConfig {

    public TableViewActionConfig() {
    }

    public TableViewActionConfig(JsonObject configJson) {
        super(configJson);
    }

}
