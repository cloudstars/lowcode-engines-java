package io.github.cloudstars.lowcode.value.loader;

import io.github.cloudstars.lowcode.value.type.NumberValueTypeConfig;

/**
 * 数字数据格式实现
 *
 * @author clouds
 */
@ValueTypeClass(name = "NUMBER", valueTypeConfigClass = NumberValueTypeConfig.class)
public class NumberValueType extends AbstractValueType<NumberValueTypeConfig, Number> {

    /**
     * 精度值
     */
    private int precision;

    public NumberValueType(NumberValueTypeConfig valueTypeConfig) {
        super(valueTypeConfig);

        Integer precisionConfig = this.valueTypeConfig.getPrecision();
        this.precision = precisionConfig == null ? 0 : precisionConfig.intValue();
    }

    @Override
    public Number parseDefaultValue() throws InvalidDataException {
        Object defaultValueConfig = this.valueTypeConfig.getDefaultValue();
        if (defaultValueConfig != null) {
            Number defaultValue = null;
            if (defaultValueConfig instanceof Integer) {
                defaultValue = (Integer) defaultValueConfig;
            } else if (defaultValueConfig instanceof Double) {
                defaultValue = (Double) defaultValueConfig;
            }

            return defaultValue;
        }

        return null;
    }

    @Override
    public Number parseValue(Object rawValue) throws InvalidDataException {
        if (rawValue == null) {
            return null;
        }

        Number value = null;
        if (this.precision == 0) {
            value = (Integer) rawValue;
        } else {
            value = (Double) rawValue;
        }

        return value;
    }

    @Override
    public void validate(Number value) throws InvalidDataException {
        if (value == null) {
            return;
        }

        Number minValue = this.valueTypeConfig.getMinValue();
        if (minValue != null && this.compare(value, minValue) < 0) {
            throw new InvalidDataException("数据[" + value + "]不足设定的最小值:" + minValue);
        }
        Number maxValue = this.valueTypeConfig.getMaxValue();
        if (maxValue != null && this.compare(value, maxValue) > 0) {
            throw new InvalidDataException("数据[" + value + "]超出了设定的最大值:" + maxValue);
        }
    }


    /**
     * 比较两个数字
     *
     * @param n1 第一个数字
     * @param n2 第一个数字
     * @return 当第1个数字小于第2个数字时返回负数, 当第1个数字大于第2个数字时反回正数，否则返回0
     */
    private int compare(Number n1, Number n2) {
        Integer precisionConfig = this.valueTypeConfig.getPrecision();
        int precision = precisionConfig == null ? 0 : precisionConfig.intValue();
        if (precision == 0) {
            return n1.intValue() - n2.intValue();
        }

        double mistake = 1 / Math.pow(10, precision + 1);
        double d = n1.doubleValue() - n2.doubleValue();
        return (Math.abs(d) < mistake) ? 0 : ((d < 0) ? -1 : 1);
    }


}
