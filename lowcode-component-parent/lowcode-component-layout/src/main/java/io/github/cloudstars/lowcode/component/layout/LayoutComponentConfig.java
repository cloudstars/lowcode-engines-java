package io.github.cloudstars.lowcode.component.layout;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

public class LayoutComponentConfig extends AbstractComponentConfig implements XComponentConfig {

    public LayoutComponentConfig() {
    }

    public LayoutComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
