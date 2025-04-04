package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.DataValidationUtils;
import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;

import java.util.List;
import java.util.Map;

/**
 * 对象数据格式配置
 *
 * @author clouds
 */
@DataTypeConfigClass(name = "JSON_OBJECT")
public class JsonObjectDataTypeConfig extends AbstractObjectDataTypeConfig<JsonObject> {

    public JsonObjectDataTypeConfig() {
    }

    public JsonObjectDataTypeConfig(JsonObject configJson) {
        super(configJson);

        Object propertiesConfig = configJson.get("properties");
        if (propertiesConfig != null) {
            JsonArray propertiesConfigJson = (JsonArray) propertiesConfig;
            propertiesConfigJson.forEach((propertyConfig) -> {
                JsonObject propertyConfigJson = (JsonObject) propertyConfig;
                ObjectProperty objectProperty = new ObjectProperty();
                objectProperty.setName(propertyConfigJson.get("name").toString());
                objectProperty.setDataType(DataTypeConfigFactory.newInstance(propertyConfigJson));
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
                Object propertyDefaultValue = property.getDataType().getDefaultValue();
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
            throw new InvalidDataException("对象数据格式的默认值不正确，请检查您的数据：" + JsonUtils.toJsonString(defaultValueConfig));
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
            throw new InvalidDataException("对象数据格式的值不正确，请检查您的数据：" + JsonUtils.toJsonString(nonNullValue));
        }

        return value;
    }

    @Override
    public void validate(JsonObject jsonObject) throws InvalidDataException {
        List<ObjectProperty> properties = this.properties;
        for (ObjectProperty property : properties) {
            String propertyName = property.getName();
            Object propertyValue = jsonObject.get(propertyName);
            DataTypeConfig propertyDataType = property.getDataType();
            DataValidationUtils.validate(propertyValue, propertyDataType);
        }
    }
    
}
