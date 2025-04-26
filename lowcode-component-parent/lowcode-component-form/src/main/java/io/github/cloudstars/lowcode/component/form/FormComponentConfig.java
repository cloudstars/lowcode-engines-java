package io.github.cloudstars.lowcode.component.form;

import io.github.cloudstars.lowcode.commons.config.AbstractTypedConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.component.commons.XComponentConfig;

public class FormComponentConfig extends AbstractTypedConfig implements XComponentConfig {

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
