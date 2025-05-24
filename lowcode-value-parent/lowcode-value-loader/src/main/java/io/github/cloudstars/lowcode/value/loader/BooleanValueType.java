package io.github.cloudstars.lowcode.value.loader;

import io.github.cloudstars.lowcode.value.type.BooleanValueTypeConfig;

/**
 * 布尔数据格式实现
 *
 * @author clouds
 */
@ValueTypeClass(name = "BOOLEAN", valueTypeConfigClass = BooleanValueTypeConfig.class)
public class BooleanValueType extends AbstractValueType<BooleanValueTypeConfig, Boolean> {

    public BooleanValueType(BooleanValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);
    }

    @Override
    public Boolean parseDefaultValue() throws InvalidDataException {
        Object defaultValueConfig = this.valueTypeConfig.getDefaultValue();
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
