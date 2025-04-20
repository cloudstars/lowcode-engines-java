package io.github.cloudstars.lowcode.form.commons.field;

import io.github.cloudstars.lowcode.commons.data.valuetype.config.ObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * Json字段配置
 *
 * @author clouds
 */
@FieldConfigClass(name = "JSON")
public class JsonFieldConfig extends AbstractFieldConfig {

    // 对象下属性列表的配置名称
    private static final String ATTR_PROPERTIES = "properties";

    /**
     * 对象下的属性
     */
    private List<AbstractFieldConfig> properties;

    public List<AbstractFieldConfig> getProperties() {
        return properties;
    }

    public void setProperties(List<AbstractFieldConfig> properties) {
        this.properties = properties;
    }

    public JsonFieldConfig() {
    }

    public JsonFieldConfig(JsonObject configJson) {
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
