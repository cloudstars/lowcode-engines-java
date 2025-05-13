package io.github.cloudstars.lowcode.commons.value;

/**
 * 数据校验工具类
 *
 * @author clouds
 */
public final class ValidationUtils {

    private ValidationUtils() {
    }

    /**
     * 数据校验
     *
     * @param value     数据
     * @param valueType 数据格式
     * @throws InvalidDataException 校验出错时抛出异常
     */
    public static void validate(Object value, XValueType valueType) throws InvalidDataException {
        if (value == null) {
            return;
        }

        valueType.validate(value);
    }

}
