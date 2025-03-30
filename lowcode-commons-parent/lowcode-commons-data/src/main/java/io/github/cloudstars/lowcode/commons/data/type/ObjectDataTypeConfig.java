package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.DataValidationUtils;
import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.ObjectValueUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 对象数据格式配置
 *
 * @author clouds
 */
@DataTypeConfigClass(name = "OBJECT")
public class ObjectDataTypeConfig extends AbstractObjectDataTypeConfig<Object> {

    public ObjectDataTypeConfig() {
    }

    public ObjectDataTypeConfig(JsonObject configJson) {
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
        List<ObjectProperty> properties = this.properties;
        for (ObjectProperty property : properties) {
            String propertyName = property.getName();
            Object propertyValue = ObjectValueUtils.getFieldValue(objectValue, propertyName);
            DataTypeConfig propertyDataType = property.getDataType();
            DataValidationUtils.validate(propertyValue, propertyDataType);
        }
    }

}
