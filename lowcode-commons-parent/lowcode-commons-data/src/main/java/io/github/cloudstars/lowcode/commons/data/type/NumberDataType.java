package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

import java.math.BigDecimal;

/**
 * 数字数据格式
 *
 * @author clouds
 */
public class NumberDataType implements DataType {


    /**
     * 最小值
     */
    private BigDecimal minValue;

    /**
     * 最大值
     */
    private BigDecimal maxValue;

    @Override
    public String getName() {
        return "NUMBER";
    }

    @Override
    public StoreValueType getDbValueType() {
        return null;
    }

    @Override
    public void validate(Object data) throws InvalidDataException {

    }

    @Override
    public JsonObject toJson() {
        return null;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }
}
