package io.github.cloudstars.lowcode.component.layout;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

public class IconComponentConfig extends AbstractComponentConfig implements XComponentConfig {

    public IconComponentConfig() {
    }

    public IconComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
