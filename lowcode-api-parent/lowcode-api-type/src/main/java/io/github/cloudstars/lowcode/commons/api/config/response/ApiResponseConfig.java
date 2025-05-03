package io.github.cloudstars.lowcode.commons.api.config.response;

import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;
import io.github.cloudstars.lowcode.commons.value.type.ValueTypeConfigFactory;
import io.github.cloudstars.lowcode.commons.value.type.XValueTypeConfig;

/**
 * API响应配置
 *
 * @author clouds
 */
public class ApiResponseConfig extends AbstractConfig {

    // 媒体内容类型配置名称
    protected static final String ATTR_CONTENT_TYPE = "contentType";

    /**
     * 媒体类型
     */
    private ResponseContentTypeEnum contentType;

    /**
     * 数据格式
     */
    private XValueTypeConfig valueType;

    public ApiResponseConfig() {
    }

    public ApiResponseConfig(JsonObject configJson) {
        super(configJson);

        ConfigUtils.consumeIfPresent(configJson, ATTR_CONTENT_TYPE, (e) -> {
            this.contentType = ResponseContentTypeEnum.valueOf(((String) e).toUpperCase());
        });
        ConfigUtils.consumeIfPresent(configJson, XValueTypeConfig.ATTR, (e) -> {
            this.valueType = ValueTypeConfigFactory.newInstance((JsonObject) e);
        });
    }

    public ResponseContentTypeEnum getContentType() {
        return contentType;
    }

    public void setContentType(ResponseContentTypeEnum contentType) {
        this.contentType = contentType;
    }

    public XValueTypeConfig getValueType() {
        return valueType;
    }

    public void setValueType(XValueTypeConfig valueType) {
        this.valueType = valueType;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_CONTENT_TYPE, this.contentType);
        ConfigUtils.putJsonIfNotNull(configJson, XValueTypeConfig.ATTR, this.valueType);

        return configJson;
    }


}
