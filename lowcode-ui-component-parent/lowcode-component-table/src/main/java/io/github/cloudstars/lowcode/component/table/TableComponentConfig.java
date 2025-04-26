package io.github.cloudstars.lowcode.component.table;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

public class TableComponentConfig extends AbstractResourceConfig implements XComponentConfig {

    public TableComponentConfig() {
    }

    public TableComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
