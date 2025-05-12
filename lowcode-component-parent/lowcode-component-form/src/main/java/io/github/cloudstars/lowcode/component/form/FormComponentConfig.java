package io.github.cloudstars.lowcode.component.form;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.AbstractComponentConfig;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

public class FormComponentConfig extends AbstractComponentConfig implements XComponentConfig {

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
