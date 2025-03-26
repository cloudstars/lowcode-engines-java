package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.commons.data.valuetype.ObjectValueProperty;
import io.github.cloudstars.lowcode.commons.data.valuetype.ObjectValueTypeConfig;
import io.github.cloudstars.lowcode.commons.data.valuetype.ValueTypeConfig;

import java.util.List;

/**
 * 数据校验工具类
 *
 * @author clouds
 */
public class DataValidationUtils {

    /**
     * 数据校验
     *
     * @param value     数据
     * @param valueType 数据的数值格式
     * @throws InvalidDataException 校验出错时抛出异常
     */
    public static void validate(Object value, ValueTypeConfig valueType) throws InvalidDataException {
        if (value == null) {
            if (valueType.isRequired()) {
                throw new InvalidDataException("数据不能为空！");
            }
            return;
        }

        ObjectValueTypeConfig mixedValueType = (ObjectValueTypeConfig) valueType;
        List<ObjectValueProperty> properties = mixedValueType.getProperties();
        for (ObjectValueProperty property : properties) {
            Object fieldValue = ObjectValueUtils.getFieldValue(value, property.getName());
            validate(fieldValue, property.getValueType());
        }
    }

}
