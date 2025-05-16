package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.config.GlobalAttrNames;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Map;

/**
 * 对象数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OBJECT", valueClass = Map.class)
public class MapValueTypeConfig extends AbstractMapValueTypeConfig {

    public MapValueTypeConfig() {
    }

    public MapValueTypeConfig(JsonObject configJson) {
        super(configJson);

        this.properties = ConfigUtils.getList(configJson, GlobalAttrNames.ATTR_PROPERTIES, (v) -> new MapPropertyConfig(v));
        ConfigUtils.consumeIfPresent(configJson, ATTR_PROPERTIES, (v) -> {
            this.properties = ConfigUtils.fromJsonArray((JsonArray) v,
                    (propertyConfigJson) -> new MapPropertyConfig(propertyConfigJson)
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
