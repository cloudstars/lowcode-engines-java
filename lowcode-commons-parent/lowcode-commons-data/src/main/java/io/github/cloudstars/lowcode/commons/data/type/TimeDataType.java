package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 时间数据格式
 *
 * @author clouds
 */
public class TimeDataType implements DataType {

    /**
     * 时间格式
     */
    private String timeFormat;

    @Override
    public String getName() {
        return "TIME";
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


    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }
}
