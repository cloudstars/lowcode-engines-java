package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractObjectDataTypeConfig<V extends Object> extends AbstractDataTypeConfig<V> {

    protected AbstractObjectDataTypeConfig() {}

    /**
     * 对象值下面的属性列表
     */
    protected List<ObjectProperty> properties = new ArrayList<>();

    public AbstractObjectDataTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    public List<ObjectProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectProperty> properties) {
        this.properties = properties;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        List<JsonObject> propertiesJson = new ArrayList<>();
        for (ObjectProperty property : this.properties) {
            JsonObject propertyJson = property.getDataType().toJson();
            propertyJson.put("name", property.getName());
            propertiesJson.add(propertyJson);
        }
        configJson.put("properties", propertiesJson);

        return configJson;
    }
}
