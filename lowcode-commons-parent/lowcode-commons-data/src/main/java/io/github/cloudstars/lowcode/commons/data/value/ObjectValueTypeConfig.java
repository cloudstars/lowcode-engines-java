package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.data.type.StoreValueType;
import io.github.cloudstars.lowcode.commons.lang.value.InvalidDataFormatException;
import io.github.cloudstars.lowcode.commons.utils.json.JsonArray;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;
import io.github.cloudstars.lowcode.commons.utils.json.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 对象数据格式配置
 *
 * @author clouds
 */
@DataTypeConfigClass(name = "OBJECT")
public class ObjectValueTypeConfig extends AbstractValueTypeConfig<JsonObject> {

    /**
     * 对象值下面的属性列表
     */
    private List<ObjectProperty> properties = new ArrayList<>();


    public ObjectValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object propertiesConfig = configJson.get("properties");
        if (propertiesConfig != null) {
            JsonArray propertiesConfigJson = (JsonArray) propertiesConfig;
            propertiesConfigJson.forEach((propertyConfig) -> {
                JsonObject propertyConfigJson = (JsonObject) propertyConfig;
                ObjectProperty objectProperty = new ObjectProperty();
                objectProperty.setName(propertyConfigJson.get("name").toString());
                objectProperty.setValueType(ValueTypeFactory.newInstance(propertyConfigJson));
                this.properties.add(objectProperty);
            });
        }

        this.defaultValue = this.parseDefaultValue(configJson);
    }

    @Override
    protected JsonObject parseDefaultValue(Object defaultValueConfig) {
        JsonObject defaultValue = null;
        if (defaultValueConfig == null) {
            // 如果对象自身没有定义默认值，则计算对象下属性的默认值
            JsonObject propertiesDefaultValue = new JsonObject();
            for (ObjectProperty property : this.properties) {
                Object propertyDefaultValue = property.getValueType().getDefaultValue();
                if (propertyDefaultValue != null) {
                    propertiesDefaultValue.put(property.getName(), propertiesDefaultValue);
                }
            }

            // 如果对象下的属性存在默认值，那么合并到对象的默认值中
            if (propertiesDefaultValue.size() > 0) {
                defaultValue = propertiesDefaultValue;
            }
        } else if (defaultValueConfig instanceof JsonObject) {
            defaultValue = (JsonObject) defaultValueConfig;
        } else if (defaultValueConfig instanceof Map) {
            defaultValue = new JsonObject((Map<String, Object>) defaultValueConfig);
        } else {
            throw new InvalidDataFormatException("对象数据格式的默认值不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
        }

        return defaultValue;
    }

    @Override
    public JsonObject parseNonNullValue(Object nonNullValue) {
        JsonObject value = null;
        if (nonNullValue instanceof JsonObject) {
            value = (JsonObject) nonNullValue;
        } else if (nonNullValue instanceof Map) {
            value = new JsonObject((Map<String, Object>) value);
        } else {
            throw new InvalidDataFormatException("对象数据格式的值不正确，请检查您的数据：" + JsonUtils.toJsonString(nonNullValue));
        }

        return value;
    }

    @Override
    public StoreValueType getStoreDataType() {
        return StoreValueType.OBJECT;
    }

    public List<ObjectProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ObjectProperty> properties) {
        this.properties = properties;
    }

}
