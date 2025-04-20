package io.github.cloudstars.lowcode.commons.data.valuetype.config;

import io.github.cloudstars.lowcode.commons.data.valuetype.DataTypeEnum;
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
        return DataTypeEnum.NUMBER;
        /*if (this.precision == null || this.precision == 0) {
            if (this.maxValue != null) {
                long mv = Long.parseLong(this.maxValue.toString());
                if (mv > Integer.MAX_VALUE) {
                    return DataTypeEnum.LONG;
                }
            }

            return DataTypeEnum.INTEGER;
        }

        return DataTypeEnum.DECIMAL;*/
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
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        configJson.putIfNotNull(ATTR_PRECISION, this.precision);
        configJson.putIfNotNull(ATTR_MIN_VALUE, this.minValue);
        configJson.putIfNotNull(ATTR_MAX_VALUE, this.maxValue);
        return configJson;
    }

}
