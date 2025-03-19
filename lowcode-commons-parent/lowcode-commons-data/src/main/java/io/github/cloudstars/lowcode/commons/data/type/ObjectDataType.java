package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.data.ObjectValueUtils;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

import java.util.List;
import java.util.Map;

/**
 * 对象数据格式
 *
 * @author clouds
 */
@DataTypeClassAnno(name = "OBJECT")
public class ObjectDataType implements MixedDataType {

    /**
     * 对象下的属性，可为空
     */
    private List<DataProperty> properties;

    public ObjectDataType() {
    }

    public ObjectDataType(JsonObject configJson) {

    }

    public ObjectDataType(List<DataProperty> properties) {
        this.properties = properties;
    }

    @Override
    public String getName() {
        return "OBJECT";
    }

    @Override
    public StoreValueType getDbValueType() {
        return StoreValueType.OBJECT;
    }

    @Override
    public void validate(Object data) throws InvalidDataException {
        List<DataProperty> properties = this.getProperties(null);
        for (DataProperty property : properties) {
            Object fieldValue = ObjectValueUtils.getFieldValue(data, property.getName());
            //property.getDataType().validate(fieldValue);
        }
    }

    @Override
    public JsonObject toJson() {
        return null;
    }

    public List<DataProperty> getProperties(Map<String, Object> options) {
        return this.properties;
    }

}
