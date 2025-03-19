package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 布尔数据格式
 *
 * @author clouds
 */
public class BooleanDataType implements DataType {

    @Override
    public String getName() {
        return "BOOLEAN";
    }

    @Override
    public StoreValueType getDbValueType() {
        return null;
    }

    @Override
    public JsonObject toJson() {
        return null;
    }

    @Override
    public void validate(Object data) throws InvalidDataException {

    }

}
