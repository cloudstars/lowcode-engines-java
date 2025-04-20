package io.github.cloudstars.lowcode.commons.data.valuetype;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数字数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "NUMBER")
public class NumberValueTypeConfig extends AbstractValueTypeConfig<Number> {

    // 精度配置名称
    private static final String ATTR_PRECISION = "precision";

    // 最小值配置名称
    private static final String ATTR_MIN_VALUE = "minValue";

    // 最大值配置名称
    private static final String ATTR_MAX_VALUE = "maxValue";

    /**
     * 精度
     */
    private Integer precision;

    /**
     * 最小值
     */
    private Number minValue;

    /**
     * 最大值
     */
    private Number maxValue;

    public NumberValueTypeConfig() {
    }

    public NumberValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object minValueValue = configJson.get(ATTR_MIN_VALUE);
        if (minValueValue != null) {
            this.minValue = (Number) minValueValue;
        }

        Object maxValueValue = configJson.get(ATTR_MAX_VALUE);
        if (maxValueValue != null) {
            this.maxValue = (Number) maxValueValue;
        }

        Object precisionValue = configJson.get(ATTR_PRECISION);
        if (precisionValue != null) {
            this.precision = (Integer) precisionValue;
        }

        // 默认值需要在所有属性解析完之后再解析
        // this.defaultValue = this.parseDefaultValue(configJson);
    }

    @Override
    public DataTypeEnum getDataType() {
        if (this.precision == null || this.precision == 0) {
            if (this.maxValue != null) {
                long mv = Long.parseLong(this.maxValue.toString());
                if (mv > Integer.MAX_VALUE) {
                    return DataTypeEnum.LONG;
                }
            }

            return DataTypeEnum.INTEGER;
        }

        return DataTypeEnum.DECIMAL;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Number getMinValue() {
        return minValue;
    }

    public void setMinValue(Number minValue) {
        this.minValue = minValue;
    }

    public Number getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Number maxValue) {
        this.maxValue = maxValue;
    }

    /*@Override
    protected Number parseDefaultValue(Object defaultValueConfig) {
        if (defaultValueConfig != null) {
            getActualValue(defaultValueConfig);
        }

        return null;
    }*/

    /*private Number getActualValue(Object value) {
        if (this.precision == 0) {
            this.defaultValue = (Integer) value;
        } else {
            this.defaultValue = (Double) value;
        }

        return (Number) value;
    }*/

    @Override
    public Number parseNonNullValue(Object nonNullValue) {
        Number value = null;
        if (this.precision == 0) {
            value = (Integer) nonNullValue;
        } else {
            value = (Double) nonNullValue;
        }

        return value;
    }

    @Override
    public void validateNonNullValue(Number nonNullValue) throws InvalidDataException {
        if (this.minValue != null && this.compare(nonNullValue, minValue) < 0) {
            throw new InvalidDataException("数据[" + nonNullValue + "]不足设定的最小值:" + this.minValue);
        }
        if (this.maxValue != null && this.compare(nonNullValue, maxValue) > 0) {
            throw new InvalidDataException("数据[" + nonNullValue + "]超出了设定的最大值:" + this.maxValue);
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
        if (this.precision == 0) {
            return n1.intValue() - n2.intValue();
        }

        double mistake = 1 / Math.pow(10, this.precision + 1);
        double d = n1.doubleValue() - n2.doubleValue();
        return (Math.abs(d) < mistake) ? 0 : ((d < 0) ? -1 : 1);
    }


    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(ATTR_PRECISION, this.precision);
        configJson.putIfNotNull(ATTR_MIN_VALUE, this.minValue);
        configJson.putIfNotNull(ATTR_MAX_VALUE, this.maxValue);
        return configJson;
    }

}
