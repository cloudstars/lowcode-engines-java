package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.data.DataValidationUtils;
import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.util.List;

/**
 * 对象数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "OBJECT")
public class ObjectValueTypeConfig extends AbstractObjectValueTypeConfig<Object> {

    public ObjectValueTypeConfig() {
    }

    public ObjectValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object propertiesConfig = configJson.get("properties");
        if (propertiesConfig != null) {
            JsonArray propertiesConfigJson = (JsonArray) propertiesConfig;
            propertiesConfigJson.forEach((propertyConfig) -> {
                JsonObject propertyConfigJson = (JsonObject) propertyConfig;
                ObjectValueProperty objectProperty = new ObjectValueProperty();
                objectProperty.setName(propertyConfigJson.get("name").toString());
                objectProperty.setValueType(ValueTypeConfigFactory.newInstance(propertyConfigJson));
                this.properties.add(objectProperty);
            });
        }

        this.defaultValue = this.parseDefaultValue(configJson);
    }

    @Override
    protected Object parseDefaultValue(Object defaultValueConfig) {
        // TODO 获取V的真实类型，将defaultValueConfig作为参数传递给V的构造函数
        return defaultValueConfig;
    }

    @Override
    public Object parseNonNullValue(Object nonNullValue) {
        // TODO 获取V的真实类型，再作一些数据格式的兼容性处理
        return nonNullValue;
    }

    @Override
    public void validate(Object objectValue) throws InvalidDataException {
        List<ObjectValueProperty> properties = this.properties;
        for (ObjectValueProperty property : properties) {
            String propertyName = property.getName();
            Object propertyValue = ObjectUtils.getFieldValue(objectValue, propertyName);
            ValueTypeConfig propertyDataType = property.getValueType();
            DataValidationUtils.validate(propertyValue, propertyDataType);
        }
    }

}
