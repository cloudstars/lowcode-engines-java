package io.github.cloudstars.lowcode.commons.data.value;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文本数据格式配置
 *
 * @author clouds
 */
@DataTypeConfigClass(name = "TEXT")
public class TextValueTypeConfig extends AbstractValueTypeConfig<String> {

    /**
     * 最小长度
     */
    private Integer minLength;

    /**
     * 最大长度
     */
    private Integer maxLength;

    public TextValueTypeConfig(JsonObject configJson) {
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

    @Override
    public StoreValueType getStoreDataType() {
        return StoreValueType.TEXT;
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

}
