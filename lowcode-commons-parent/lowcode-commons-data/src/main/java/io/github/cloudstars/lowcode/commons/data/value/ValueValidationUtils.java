package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.ObjectValueUtils;

import java.util.List;

/**
 * 数据校验工具类
 *
 * @author clouds
 */
public class ValueValidationUtils {

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
        List<ObjectProperty> properties = mixedValueType.getProperties();
        for (ObjectProperty property : properties) {
            Object fieldValue = ObjectValueUtils.getFieldValue(value, property.getName());
            validate(fieldValue, property.getValueType());
        }
    }

}
