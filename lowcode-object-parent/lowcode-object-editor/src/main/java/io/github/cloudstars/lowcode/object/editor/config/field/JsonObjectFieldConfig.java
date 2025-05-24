package io.github.cloudstars.lowcode.object.editor.config.field;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.value.type.MapValueTypeConfig;
import io.github.cloudstars.lowcode.object.commons.field.AbstractObjectFieldConfig;
import io.github.cloudstars.lowcode.object.commons.field.ObjectFieldConfigClass;

import java.util.List;

/**
 * Json字段配置
 *
 * @author clouds
 */
@ObjectFieldConfigClass(name = "JSON")
public class JsonObjectFieldConfig extends AbstractObjectFieldConfig {

    /**
     * 对象下的属性
     */
    private List<AbstractObjectFieldConfig> properties;

    public List<AbstractObjectFieldConfig> getProperties() {
        return properties;
    }

    public void setProperties(List<AbstractObjectFieldConfig> properties) {
        this.properties = properties;
    }

    public JsonObjectFieldConfig() {
    }

    public JsonObjectFieldConfig(JsonObject configJson) {
        super(configJson);

        /*Object propertiesValue = configJson.get(ATTR_PROPERTIES);
        if (propertiesValue != null) {
            this.properties = new ArrayList<>();
            JsonArray propertiesConfigJson = (JsonArray) propertiesValue;
            propertiesConfigJson.forEach((p) -> {
                this.properties.add(FieldConfigFactory.newInstance((JsonObject) p));
            });
        }*/

        this.setValueType(new MapValueTypeConfig(configJson));
    }


    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        /*if (this.properties != null) {
            configJson.put(ATTR_PROPERTIES, JsonConfigUtils.toJsonArray(this.properties));
        }*/

        return configJson;
    }

}
