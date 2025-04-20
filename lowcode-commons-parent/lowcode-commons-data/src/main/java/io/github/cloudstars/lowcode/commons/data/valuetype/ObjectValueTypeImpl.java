package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.valuetype.config.ObjectValueProperty;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.ObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.config.XValueTypeConfig;
import io.github.cloudstars.lowcode.commons.lang.util.ObjectUtils;

import java.util.List;

/**
 * 文本数据格式实现（运行态）
 *
 * @author clouds
 */
public class ObjectValueTypeImpl extends AbstractValueTypeImpl<ObjectValueTypeConfig, Object> {


    public ObjectValueTypeImpl(ObjectValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }
    @Override
    public Object parseValue(Object rawValue) {
        // TODO 获取V的真实类型，再作一些数据格式的兼容性处理
        return rawValue;
    }

    @Override
    public void validate(Object value) throws InvalidDataException {
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
