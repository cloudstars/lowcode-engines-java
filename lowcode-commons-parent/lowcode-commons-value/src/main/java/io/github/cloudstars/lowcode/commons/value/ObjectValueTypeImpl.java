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
 * 对象数据格式
 *
 * @author clouds
 */
@ValueTypeClass(name = "OBJECT", valueTypeConfigClass = ObjectValueTypeConfig.class)
public class ObjectValueTypeImpl extends AbstractValueTypeImpl<ObjectValueTypeConfig, Map<String, Object>> {

    public ObjectValueTypeImpl(ObjectValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public Map<String, Object> parseDefaultValue() throws InvalidDataException {
        Object defaultValueConfig = this.valueTypeConfig.getDefaultValue();
        if (defaultValueConfig == null) {
            return this.getDefaultValueFromProperties();
        } else {
            if (defaultValueConfig instanceof Map) {
                return this.getDefaultValueFromMap((Map) defaultValueConfig);
            } else {
                throw new InvalidDataException("非法的Object默认值数据格式，请检查：" + JsonUtils.toJsonString(defaultValueConfig));
            }
        }
    }

    @Override
    public Map<String, Object> mergeDefaultValue(Object rawValue) {
        if (rawValue == null) {
            return this.parseDefaultValue();
        }

        if (rawValue instanceof Map) {
            Map rawValueMap = (Map) rawValue;
            List<ObjectPropertyConfig> properties = this.valueTypeConfig.getProperties();
            if (properties != null && properties.size() > 0) {
                Object defaultValueConfig = this.valueTypeConfig.getDefaultValue();
                if (defaultValueConfig instanceof Map) {
                    this.parsePropertyValuesToMap((Map) defaultValueConfig, rawValueMap);
                }
            }
            return rawValueMap;
        }

        return JsonUtils.parseObject(JsonUtils.toJsonString(rawValue), HashMap.class);
    }

    /**
     * 解析默认值
     *
     * @param mapValue 默认值配置
     */
    private Map<String, Object> getDefaultValueFromMap(Map mapValue) {
        Map<String, Object> targetMap = mapValue;
        List<ObjectPropertyConfig> properties = this.valueTypeConfig.getProperties();
        if (properties != null && properties.size() > 0) {
            targetMap = new HashMap<>();
            this.parsePropertyValuesToMap(mapValue, targetMap);
        }

        return targetMap;
    }

    /**
     * 根据属性列表从源Map数据中解析属性值，并放入目标Map数据中
     *
     * @param valueMap 源Map数据
     * @param targetMap 目标Map数据
     */
    private void parsePropertyValuesToMap(Map<String, Object> valueMap, Map<String, Object> targetMap) {
        // 过滤掉没有定义的属性，并把属性的值转为正确的数据格式
        List<ObjectPropertyConfig> properties = this.valueTypeConfig.getProperties();
        for (ObjectPropertyConfig property : properties) {
            String propertyName = property.getName();
            if (valueMap.containsKey(propertyName)) {
                Object rawPropertyValue = valueMap.get(propertyName);
                XValueType valueType = ValueTypeFactory.newInstance(property.getValueType());
                Object targetPropertyValue = valueType.parseValue(rawPropertyValue);
                targetMap.put(propertyName, targetPropertyValue);
            }
        }
    }

    /**
     * 从属性列表中解析默认值
     *
     * @return 默认值
     */
    private Map<String, Object> getDefaultValueFromProperties() {
        List<ObjectPropertyConfig> properties = this.valueTypeConfig.getProperties();
        if (properties == null || properties.size() == 0) {
            return null;
        }

        Map<String, Object> targetMap = null;
        for (ObjectPropertyConfig property : properties) {
            String propertyName = property.getName();
            Object propertyDefaultValue = property.getValueType().getDefaultValue();
            if (propertyDefaultValue != null) { // 属性中配置了默认值
                XValueType valueType = ValueTypeFactory.newInstance(property.getValueType());
                Object targetPropertyValue = valueType.parseDefaultValue();
                if (targetPropertyValue != null) {
                    if (targetMap == null) { // 懒初始化
                        targetMap = new HashMap<>();
                    }
                    targetMap.put(propertyName, targetPropertyValue);
                }
            }
        }

        return targetMap;
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
