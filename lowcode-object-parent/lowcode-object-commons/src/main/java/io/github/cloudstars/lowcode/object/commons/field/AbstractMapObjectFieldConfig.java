package io.github.cloudstars.lowcode.object.commons.field;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.MapPropertyConfig;

import java.util.List;

public abstract class AbstractMapObjectFieldConfig extends AbstractObjectFieldConfig implements XMapObjectFieldConfig {

    private List<MapPropertyConfig> properties;

    public AbstractMapObjectFieldConfig() {
    }

    public AbstractMapObjectFieldConfig(JsonObject configJson) {
        super(configJson);
    }

    @Override
    public List<MapPropertyConfig> getProperties() {
        return this.properties;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putArrayIfNotNull(configJson, GlobalAttrNames.ATTR_PROPERTIES, this.properties);

        return configJson;
    }

}
