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

        ConfigUtils.getIfPresent(configJson, ATTR_CONTENT_TYPE, (v) -> {
            this.contentType = RequestContentTypeEnum.valueOf(((String) v).toUpperCase());
        });
        ConfigUtils.getIfPresent(configJson, XValueTypeConfig.ATTR, (v) -> {
            this.valueType = ValueTypeConfigFactory.newInstance((JsonObject) v);
        });
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_CONTENT_TYPE, this.contentType);
        ConfigUtils.putJsonIfNotNull(configJson, XValueTypeConfig.ATTR, this.valueType);

        return configJson;
    }

}
