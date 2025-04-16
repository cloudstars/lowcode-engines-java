package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.commons.data.type.ValueTypeConfig;

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
     * @param valueTypeConfig 数据格式的配置
     * @throws InvalidDataException 校验出错时抛出异常
     */
    public static void validate(Object value, ValueTypeConfig valueTypeConfig) throws InvalidDataException {
        if (value == null) {
            if (valueTypeConfig.isRequired()) {
                throw new InvalidDataException("数据不能为空！");
            }
            return;
        }

        valueTypeConfig.validate(value);
    }

}
