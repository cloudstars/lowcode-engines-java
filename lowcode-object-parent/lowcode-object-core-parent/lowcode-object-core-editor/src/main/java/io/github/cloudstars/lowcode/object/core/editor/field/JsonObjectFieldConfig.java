package io.github.cloudstars.lowcode.object.core.editor.field;

import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * Json字段配置
 *
 * @author clouds
 */
@ObjectFieldConfigClass(name = "JSON")
public class JsonObjectFieldConfig extends AbstractObjectFieldConfig {

    // 对象下属性列表的配置名称
    private static final String ATTR_PROPERTIES = "properties";

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

        this.setValueType(new ObjectValueTypeConfig(configJson));
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
