package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 日期数据格式
 *
 * @author clouds
 */
public class DateDataType implements DataType {

    /**
     * 日期格式
     */
    private String dateFormat;

    @Override
    public String getName() {
        return "DATE";
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

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

}
