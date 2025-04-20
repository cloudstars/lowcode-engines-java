package io.github.cloudstars.lowcode.component.form;

import io.github.cloudstars.lowcode.component.commons.XComponentConfig;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractIdentifiedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

public class FormComponentConfig extends AbstractIdentifiedConfig implements XComponentConfig {

    public FormComponentConfig() {
    }

    public FormComponentConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public JsonObject toJson() {
        return super.toJson();
    }

}
