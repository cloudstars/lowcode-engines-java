package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象的数据格式配置
 *
 * @author clouds
 */
public abstract class AbstractObjectValueTypeConfig<V extends Object> extends AbstractValueTypeConfig<V> {

    protected AbstractObjectValueTypeConfig() {}

    /**
     * 对象值下面的属性列表
     */
    protected List<ObjectValueProperty> properties = new ArrayList<>();

    public AbstractObjectValueTypeConfig(JsonObject configJson) {
        super(configJson);
    }

    public List<ObjectValueProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectValueProperty> properties) {
        this.properties = properties;
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.OBJECT;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        List<JsonObject> propertiesJson = new ArrayList<>();
        for (ObjectValueProperty property : this.properties) {
            JsonObject propertyJson = property.getValueType().toJson();
            propertyJson.put("name", property.getName());
            propertiesJson.add(propertyJson);
        }
        configJson.put("properties", propertiesJson);

        return configJson;
    }
}
