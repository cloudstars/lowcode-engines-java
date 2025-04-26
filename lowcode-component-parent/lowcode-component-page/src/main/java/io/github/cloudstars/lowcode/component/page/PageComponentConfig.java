package io.github.cloudstars.lowcode.component.page;

import io.github.cloudstars.lowcode.commons.config.AbstractResourceConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

public class PageComponentConfig extends AbstractResourceConfig implements XComponentConfig {

    public PageComponentConfig() {
    }

    public PageComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
