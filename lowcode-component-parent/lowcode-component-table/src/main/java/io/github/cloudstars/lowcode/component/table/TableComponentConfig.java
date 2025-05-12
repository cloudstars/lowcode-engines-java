package io.github.cloudstars.lowcode.component.table;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

public class TableComponentConfig extends AbstractComponentConfig implements XComponentConfig {

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
