package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 文本数据格式
 *
 * @author clouds
 */
@DataTypeClassAnno(name = "TEXT")
public class TextDataType implements DataType {

    /**
     * 最大长度
     */
    private Integer maxLength;

    public TextDataType() {
    }

    public TextDataType(JsonObject configJson) {
        Object maxLengthValue = configJson.get("maxLength");
        if (maxLengthValue != null) {
            this.maxLength = (Integer) maxLengthValue;
        }
    }

    @Override
    public String getName() {
        return "TEXT";
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

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

}
