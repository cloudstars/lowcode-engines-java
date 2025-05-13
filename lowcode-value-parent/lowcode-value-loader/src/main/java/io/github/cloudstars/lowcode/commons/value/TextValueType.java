package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.value.type.TextValueTypeConfig;

/**
 * 文本数据格式实现（运行态）
 *
 * @author clouds
 */
@ValueTypeClass(name = "TEXT", valueTypeConfigClass = TextValueTypeConfig.class)
public class TextValueType extends AbstractValueType<TextValueTypeConfig, String> {

    public TextValueType(TextValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public String parseDefaultValue() throws InvalidDataException {
        Object defaultValueConfig = this.valueTypeConfig.getDefaultValue();
        if (defaultValueConfig != null) {
            if (defaultValueConfig instanceof String) {
                return (String) defaultValueConfig;
            }

            return defaultValueConfig.toString();
        }

        return null;
    }

    @Override
    public String parseValue(Object rawValue) throws InvalidDataException {
        if (rawValue == null) {
            return null;
        }

        return rawValue.toString();
    }

    @Override
    public void validate(String value) throws InvalidDataException {
        if (value == null) {
            return;
        }

        int valueLength = value.length();
        Integer minLength = this.valueTypeConfig.getMinLength();
        Integer maxLength = this.valueTypeConfig.getMaxLength();
        if (minLength != null && valueLength < minLength) {
            throw new InvalidDataException("数据[" + value + "]不足设定的最小长度:" + minLength);
        }
        if (maxLength != null && valueLength > maxLength) {
            throw new InvalidDataException("数据[" + value + "]超出了设定的最大长度:" + maxLength);
        }
    }

}
