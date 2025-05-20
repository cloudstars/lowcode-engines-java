package io.github.cloudstars.lowcode.component.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

/**
 * 表格展示组件配置
 *
 * @author clouds
 */
public class TableViewComponentConfig extends AbstractComponentConfig implements XComponentConfig {

    public TableViewComponentConfig() {
    }

    public TableViewComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
