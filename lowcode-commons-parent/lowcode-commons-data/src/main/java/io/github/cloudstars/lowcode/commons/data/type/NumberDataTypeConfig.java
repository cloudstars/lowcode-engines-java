package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 数字数据格式配置
 *
 * @author clouds
 */
@DataTypeConfigClass(name = "NUMBER")
public class NumberDataTypeConfig extends AbstractDataTypeConfig<Number> {

    /**
     * 精度
     */
    private int precision = 0;

    /**
     * 最小值
     */
    private Number minValue;

    /**
     * 最大值
     */
    private Number maxValue;

    public NumberDataTypeConfig() {
    }

    public NumberDataTypeConfig(JsonObject configJson) {
        super(configJson);

        Object minValueValue = configJson.get("minValue");
        if (minValueValue != null) {
            this.minValue = this.getActualValue(minValueValue);
        }

        Object maxValueValue = configJson.get("maxValue");
        if (maxValueValue != null) {
            this.maxValue = this.getActualValue(maxValueValue);
        }

        Object precisionValue = configJson.get("precision");
        if (precisionValue != null) {
            this.precision = (Integer) precisionValue;
        }

        // 默认值需要在所有属性解析完之后再解析
        this.defaultValue = this.parseDefaultValue(configJson);
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

    @Override
    protected Number parseDefaultValue(Object defaultValueConfig) {
        if (defaultValueConfig != null) {
            getActualValue(defaultValueConfig);
        }

        return null;
    }

    private Number getActualValue(Object value) {
        if (this.precision == 0) {
            this.defaultValue = (Integer) value;
        } else {
            this.defaultValue = (Double) value;
        }

        return (Number) value;
    }

    @Override
    public Number parseNonNullValue(Object nonNullValue) {
        return null;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.put("precision", this.precision);
        if (this.minValue != null) {
            configJson.put("minValue", this.minValue);
        }
        if (this.maxValue != null) {
            configJson.put("maxValue", this.maxValue);
        }
        return configJson;
    }

    @Override
    public void validate(Number nonNullValue) throws InvalidDataException {
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
        return (Math.abs(d) < mistake) ? 0 : ((d < 0) ? - 1 : 1);
    }

}
