package io.github.cloudstars.lowcode.commons.lang.value;

import io.github.cloudstars.lowcode.commons.lang.DataType;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象数据格式
 *
 * @author clouds
 */
public class ObjectValueType extends AbstractValueType implements ValueType {

    /**
     * 对象下的属性列表
     */
    private List<ObjectProperty> properties;

    public ObjectValueType() {
    }

    public ObjectValueType(List<ObjectProperty> properties) {
        this.properties = properties;
    }

    public ObjectValueType(boolean isArray) {
        super(isArray);
    }

    public ObjectValueType(boolean isArray, List<ObjectProperty> properties) {
        super(isArray);
        this.properties = properties;
    }

    @Override
    public DataType getDataType() {
        return DataType.OBJECT;
    }

    public List<ObjectProperty> getProperties() {
        return properties;
    }

    @Override
    protected void validateNonNullValue(Object value) throws InvalidDataFormatException {
        if (this.properties != null) {
            for (ObjectProperty property : properties) {
                Object kv = ObjectUtils.getFieldValue(value, property.getName());
                property.getValueType().validate(kv);
            }
        }
    }

    @Override
    protected Object getCorrectNonNullValue(Object value) {
        if (this.properties != null) {
            final Map<String, Object> newFieldValues = new HashMap<>();
            for (ObjectProperty property : properties) {
                String pn = property.getName();
                Object kv = ObjectUtils.getFieldValue(value, pn);
                Object ckv = property.getValueType().getCorrectValue(kv);
                if (kv != ckv) { // 说明被调整了
                    newFieldValues.put(pn, ckv);
                }
            }

            if (!newFieldValues.isEmpty()) { // 说明value需要调整
                return ObjectUtils.clone(value, newFieldValues);
            }
        }

        return value;
    }

    /**
     * 对象下的属性
     *
     * @author clouds
     */
    public static class ObjectProperty {

        /**
         * 属性的名称
         */
        private String name;

        /**
         * 属性的数据格式
         */
        private ValueType valueType;

        public ObjectProperty() {
        }

        public ObjectProperty(String name, ValueType valueType) {
            this.name = name;
            this.valueType = valueType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ValueType getValueType() {
            return valueType;
        }

        public void setValueType(ValueType valueType) {
            this.valueType = valueType;
        }
    }
}
