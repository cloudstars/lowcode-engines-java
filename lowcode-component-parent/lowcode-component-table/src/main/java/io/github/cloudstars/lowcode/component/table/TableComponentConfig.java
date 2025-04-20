package io.github.cloudstars.lowcode.component.table;

import io.github.cloudstars.lowcode.component.commons.XComponentConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

public class TableComponentConfig extends AbstractIdentifiedConfig implements XComponentConfig {

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
