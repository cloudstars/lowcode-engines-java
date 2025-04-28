package io.github.cloudstars.lowcode.commons.api.config.request;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * API请求配置
 *
 * @author clouds
 */
public class ApiRequestConfig extends AbstractConfig {

    // 媒体内容类型配置名称
    private static final String ATTR_CONTENT_TYPE = "contentType";

    /**
     * 媒体类型
     */
    private RequestContentTypeEnum contentType;

    /**
     * 数据格式配置
     */
    private XValueTypeConfig valueType;

    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    public ApiRequestConfig() {
    }

    public ApiRequestConfig(JsonObject configJson) {
        super(configJson);

        String contentTypeValue = (String) configJson.get(ATTR_CONTENT_TYPE);
        if (contentTypeValue != null) {
            this.contentType = RequestContentTypeEnum.valueOf(contentTypeValue.toUpperCase());
        }
        JsonObject valueTypeConfigJson = (JsonObject) configJson.get(XValueTypeConfig.ATTR);
        if (valueTypeConfigJson != null) {
            this.valueType = ValueTypeConfigFactory.newInstance(valueTypeConfigJson);
        }
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_CONTENT_TYPE, this.contentType);
        ConfigUtils.putJsonIfNotNull(configJson, XValueTypeConfig.ATTR, this.valueType);

        return configJson;
    }

}
