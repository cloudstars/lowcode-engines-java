package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Map;

/**
 * 对象数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OBJECT", valueClass = Map.class)
public class ObjectValueTypeConfig extends AbstractObjectValueTypeConfig {

    public ObjectValueTypeConfig() {
    }

    public ObjectValueTypeConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consumeIfPresent(configJson, ATTR_PROPERTIES, (v) -> {
            this.properties = ConfigUtils.fromJsonArray((JsonArray) v,
                    (propertyConfigJson) -> new ObjectPropertyConfig(propertyConfigJson)
            );
        });
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putArrayIfNotNull(configJson, ATTR_PROPERTIES, this.properties);

        return configJson;
    }

}
