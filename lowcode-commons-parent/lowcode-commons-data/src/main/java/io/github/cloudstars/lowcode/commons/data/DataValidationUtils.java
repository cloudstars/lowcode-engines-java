package io.github.cloudstars.lowcode.commons.data;

import io.github.cloudstars.lowcode.commons.data.type.DataTypeConfig;

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
     * @param dataTypeConfig 数据格式的配置
     * @throws InvalidDataException 校验出错时抛出异常
     */
    public static void validate(Object value, DataTypeConfig dataTypeConfig) throws InvalidDataException {
        if (value == null) {
            if (dataTypeConfig.isRequired()) {
                throw new InvalidDataException("数据不能为空！");
            }
            return;
        }

        dataTypeConfig.validate(value);
    }

}
