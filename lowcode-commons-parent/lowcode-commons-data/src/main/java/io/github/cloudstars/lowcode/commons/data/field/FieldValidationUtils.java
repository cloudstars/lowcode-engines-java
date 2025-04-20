package io.github.cloudstars.lowcode.commons.data.field;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;

/**
 * 数据校验工具类
 *
 * @author clouds
 */
public class FieldValidationUtils {

    /**
     * 字段数据校验
     *
     * @param value     数据
     * @param fieldValueConfig 数据格式的配置
     * @throws InvalidDataException 校验出错时抛出异常
     */
    public static void validate(Object value, FieldValueConfig fieldValueConfig) throws InvalidDataException {
        if (value == null) {
            if (fieldValueConfig.isRequired()) {
                throw new InvalidDataException("数据不能为空！");
            }
            return;
        }

        fieldValueConfig.getValueType().validateNonNullValue(value);
    }

}
