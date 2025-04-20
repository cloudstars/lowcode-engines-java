package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.value.type.config.ObjectValueProperty;
import io.github.cloudstars.lowcode.commons.value.type.config.ObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.config.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * 文本数据格式实现（运行态）
 *
 * @author clouds
 */
public class ObjectValueTypeImpl extends AbstractValueTypeImpl<ObjectValueTypeConfig, JsonObject<String, Object>> {


    public ObjectValueTypeImpl(ObjectValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public JsonObject parseValue(Object rawValue) {
        if (rawValue == null) {
            return null;
        }

        if (rawValue instanceof JsonObject) {
            return (JsonObject) rawValue;
        }

        if (rawValue instanceof Map) {
            return new JsonObject((Map<String, Object>) rawValue);
        }

        throw new InvalidDataException("解析Object数据出错：" + JsonUtils.toJsonString(rawValue));
    }

    @Override
    public void validate(JsonObject value) throws InvalidDataException {
        List<ObjectValueProperty> properties = this.valueTypeConfig.getProperties();
        for (ObjectValueProperty property : properties) {
            String propertyName = property.getCode();
            Object propertyValue = ObjectUtils.getFieldValue(value, propertyName);
            if (propertyValue != null) {
                XValueTypeConfig propertyValueTypeConfig = property.getValueType();
                XValueType valueType = ValueTypeFactory.newInstance(propertyValueTypeConfig);
                valueType.validate(propertyValue);
            }
        }
    }


}
