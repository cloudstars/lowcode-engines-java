package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonConfigUtils;
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

        JsonArray propertiesConfigJson = (JsonArray) configJson.get(ATTR_PROPERTIES);
        if (propertiesConfigJson != null) { // 对象下可以不定义属性
            this.properties = JsonConfigUtils.fromJsonArray(propertiesConfigJson,
                    (propertyConfigJson) -> new ObjectValueProperty(propertyConfigJson)
            );
        }

        //this.defaultValue = this.parseDefaultValue(configJson);
    }

    /*@Override
    protected Object parseDefaultValue(Object defaultValueConfig) {
        // TODO 获取V的真实类型，将defaultValueConfig作为参数传递给V的构造函数
        return defaultValueConfig;
    }*/

    @Override
    public Object parseNonNullValue(Object nonNullValue) {
        // TODO 获取V的真实类型，再作一些数据格式的兼容性处理
        return nonNullValue;
    }

    @Override
    public void validateNonNullValue(Object objectValue) throws InvalidDataException {
        List<ObjectValueProperty> properties = this.properties;
        for (ObjectValueProperty property : properties) {
            String propertyName = property.getCode();
            Object propertyValue = ObjectUtils.getFieldValue(objectValue, propertyName);
            XValueTypeConfig propertyValueType = property.getValueType();
            if (propertyValue != null) {
                propertyValueType.validateNonNullValue(propertyValue);
            }
        }
    }

}
