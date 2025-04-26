package io.github.cloudstars.lowcode.commons.value.type;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文本数据格式配置
 *
 * @author clouds
 */
@ValueTypeConfigClass(name = "TEXT")
public class TextValueTypeConfig extends AbstractValueTypeConfig<String> {

    // 最小长度配置名称
    private static final String ATTR_MIN_LENGTH = "minLength";

    // 最大长度配置名称
    private static final String ATTR_MAX_LENGTH = "maxLength";

    /**
     * 最小长度
     */
    private Integer minLength;

    /**
     * 最大长度
     */
    private Integer maxLength;

    public TextValueTypeConfig() {
    }

    public TextValueTypeConfig(JsonObject configJson) {
        super(configJson);

        Object minLengthValue = configJson.get(ATTR_MIN_LENGTH);
        if (minLengthValue != null) {
            this.minLength = (Integer) minLengthValue;
        }

        Object maxLengthValue = configJson.get(ATTR_MAX_LENGTH);
        if (maxLengthValue != null) {
            this.maxLength = (Integer) maxLengthValue;
        }
    }

    @Override
    public DataTypeEnum getDataType() {
        return DataTypeEnum.STRING;
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

    /*@Override
    protected String parseDefaultValue(Object defaultValueConfig) {
        return defaultValueConfig != null ? defaultValueConfig.toString() : null;
    }*/

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        if (this.minLength != null) {
            configJson.put(ATTR_MIN_LENGTH, this.minLength);
        }
        if (this.maxLength != null) {
            configJson.put(ATTR_MAX_LENGTH, this.maxLength);
        }

        return configJson;
    }

}
