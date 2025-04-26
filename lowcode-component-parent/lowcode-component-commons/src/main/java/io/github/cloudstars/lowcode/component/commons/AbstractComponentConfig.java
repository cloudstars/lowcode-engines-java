package io.github.cloudstars.lowcode.component.commons;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

public class AbstractComponentConfig extends AbstractTypedConfig {



    public AbstractComponentConfig() {
    }

    public AbstractComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        return configJson;
    }
}
