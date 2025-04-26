package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.lang.json.JsonUtils;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;
import io.github.cloudstars.lowcode.commons.value.type.ObjectPropertyConfig;
import io.github.cloudstars.lowcode.commons.value.type.ObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象数据格式实现（运行态）
 *
 * @author clouds
 */
public class ObjectValueTypeImpl extends AbstractValueTypeImpl<ObjectValueTypeConfig, Map<String, Object>> {


    public ObjectValueTypeImpl(ObjectValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public Map<String, Object> parseDefaultValue(Object defaultValueConfig) throws InvalidDataException {
        List<ObjectPropertyConfig> properties = this.valueTypeConfig.getProperties();
        if (properties != null && properties.size() > 0) {
            if (defaultValueConfig == null) {
                return getDefaultValueFromProperties(properties);
            } else {
                if (defaultValueConfig instanceof Map) {
                    return getDefaultValue((Map) defaultValueConfig, properties);
                } else {
                    throw new InvalidDataException("非法的对象数据格式，请检查：" + JsonUtils.toJsonString(defaultValueConfig));
                }
            }
        }

        return (Map<String, Object>) defaultValueConfig;
    }

    /**
     * 解析默认值
     *
     * @param defaultValueConfig 默认值配置
     * @param properties         属性列表配置
     */
    private static Map<String, Object> getDefaultValue(Map defaultValueConfig, List<ObjectPropertyConfig> properties) {
        Map defaultValueConfigMap = defaultValueConfig;
        Map<String, Object> targetMap = new HashMap<>();
        for (ObjectPropertyConfig property : properties) {
            String propertyName = property.getName();
            if (defaultValueConfigMap.containsKey(propertyName)) {
                Object rawPropertyValue = defaultValueConfigMap.get(propertyName);
                Object targetPropertyValue = ValueTypeFactory.newInstance(property.getValueType()).parseDefaultValue(rawPropertyValue);
                targetMap.put(propertyName, targetPropertyValue);
            }
        }
        return targetMap;
    }

    /**
     * 从属性列表中解析默认值
     *
     * @param properties 属性列表配置
     * @return 默认值
     */
    private static Map<String, Object> getDefaultValueFromProperties(List<ObjectPropertyConfig> properties) {
        Map<String, Object> targetMap = null;
        for (ObjectPropertyConfig property : properties) {
            String propertyName = property.getName();
            Object propertyDefaultValue = property.getValueType().getDefaultValue();
            if (propertyDefaultValue != null) { // 属性中配置了默认值
                Object targetPropertyValue = ValueTypeFactory.newInstance(property.getValueType()).parseDefaultValue(propertyDefaultValue);
                if (targetMap == null) { // 懒初始化
                    targetMap = new HashMap<>();
                }
                targetMap.put(propertyName, targetPropertyValue);
            }

        }

        return ObjectUtils.getOrDefault(targetMap, null);
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
    public void validate(Map<String, Object> value) throws InvalidDataException {
        List<ObjectPropertyConfig> properties = this.valueTypeConfig.getProperties();
        for (ObjectPropertyConfig property : properties) {
            String propertyName = property.getName();
            Object propertyValue = ObjectUtils.getFieldValue(value, propertyName);
            if (propertyValue != null) {
                XValueTypeConfig propertyValueTypeConfig = property.getValueType();
                XValueType valueType = ValueTypeFactory.newInstance(propertyValueTypeConfig);
                valueType.validate(propertyValue);
            }
        }
    }


}
