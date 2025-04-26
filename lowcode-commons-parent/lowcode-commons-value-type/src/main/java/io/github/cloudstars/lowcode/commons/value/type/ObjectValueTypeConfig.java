package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.Map;

/**
 * 对象数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OBJECT")
public class ObjectValueTypeConfig extends AbstractObjectValueTypeConfig<Map<String, Object>> {

    public ObjectValueTypeConfig() {
    }

    public ObjectValueTypeConfig(JsonObject configJson) {
        super(configJson);

        JsonArray propertiesConfigJson = (JsonArray) configJson.get(ATTR_PROPERTIES);
        if (propertiesConfigJson != null) { // 对象下可以不定义属性
            this.properties = JsonConfigUtils.fromJsonArray(propertiesConfigJson,
                    (propertyConfigJson) -> new ObjectPropertyConfig(propertyConfigJson)
            );
        }
    }

}
