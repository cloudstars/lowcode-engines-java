package io.github.cloudstars.lowcode.object.commons.config;

import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 文本字段配置
 *
 * @author clouds
 */
public class TextFieldConfig extends AbstractFieldConfig {

    /**
     * 最小长度
     */

    private Integer minLength;

    /**
     * 最大长度
     */
    private Integer maxLength;

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
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        if (this.minLength != null) {
            configJson.put("minLength", this.minLength);
        }
        if (this.maxLength != null) {
            configJson.put("maxLength", this.maxLength);
        }
        return configJson;
    }
}
