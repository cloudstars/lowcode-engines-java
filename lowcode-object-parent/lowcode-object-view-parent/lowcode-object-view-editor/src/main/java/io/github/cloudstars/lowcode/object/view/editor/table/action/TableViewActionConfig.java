package io.github.cloudstars.lowcode.object.view.editor.table.action;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.object.view.editor.AbstractObjectViewActionConfig;

/**
 * 表格视图操作配置
 *
 * @author clouds
 */
public class TableViewActionConfig extends AbstractObjectViewActionConfig {

    public TableViewActionConfig() {
    }

    public TableViewActionConfig(JsonObject configJson) {
        super(configJson);
    }

}
