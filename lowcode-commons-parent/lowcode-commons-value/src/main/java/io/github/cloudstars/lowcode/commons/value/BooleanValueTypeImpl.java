package io.github.cloudstars.lowcode.commons.value;

import io.github.cloudstars.lowcode.commons.value.type.BooleanValueTypeConfig;

/**
 * 布尔数据格式实现
 *
 * @author clouds
 */
public class BooleanValueTypeImpl extends AbstractValueTypeImpl<BooleanValueTypeConfig, Boolean> {

    public BooleanValueTypeImpl(BooleanValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public Boolean parseDefaultValue(Object defaultValueConfig) throws InvalidDataException {
        if (defaultValueConfig instanceof String) {
            return this.parseStringBoolean((String) defaultValueConfig);
        }


        return (Boolean) defaultValueConfig;
    }

    @Override
    public Boolean parseValue(Object rawValue) throws InvalidDataException {
        if (rawValue instanceof String) {
            return this.parseStringBoolean((String) rawValue);
        }

        return (Boolean) rawValue;
    }


    /**
     * 解析字符串布尔格式
     *
     * @param booleanString 字符串布尔
     * @return Boolean
     */
    private Boolean parseStringBoolean(String booleanString) {
        return Boolean.parseBoolean((String) booleanString);
    }

    @Override
    public void validate(Boolean value) throws InvalidDataException {
    }

}
