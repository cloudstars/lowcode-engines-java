package io.github.cloudstars.lowcode.commons.data.type;

import io.github.cloudstars.lowcode.commons.data.InvalidDataException;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文本数据格式配置
 *
 * @author clouds
 */
@DataTypeConfigClass(type = "STRING")
public class StringDataTypeConfig extends AbstractDataTypeConfig<String> {

    /**
     * 最小长度
     */
    private Integer minLength;

    /**
     * 最大长度
     */
    private Integer maxLength;

    public StringDataTypeConfig() {
    }

    public StringDataTypeConfig(JsonObject configJson) {
        super(configJson);

        Object minLengthValue = configJson.get("minLength");
        if (minLengthValue != null) {
            this.minLength = (Integer) minLengthValue;
        }

        Object maxLengthValue = configJson.get("maxLength");
        if (maxLengthValue != null) {
            this.maxLength = (Integer) maxLengthValue;
        }

        // 默认值需要在所有属性解析完之后再解析
        this.defaultValue = this.parseDefaultValue(configJson);
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    protected String parseDefaultValue(Object defaultValueConfig) {
        return defaultValueConfig != null ? defaultValueConfig.toString() : null;
    }

    @Override
    public String parseNonNullValue(Object nonNullValue) {
        return nonNullValue.toString();
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();
        return jsonObject;
    }

    @Override
    public void validate(String nonNullValue) throws InvalidDataException {
        int valueLength = nonNullValue.length();
        if (this.minLength != null && valueLength < minLength) {
            throw new InvalidDataException("数据[" + nonNullValue + "]不足设定的最小长度:" + this.minLength);
        }
        if (this.maxLength != null && valueLength > maxLength) {
            throw new InvalidDataException("数据[" + nonNullValue + "]超出了设定的最大长度:" + this.maxLength);
        }
    }

}
