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

    // HTTP方法配置名称
    private static final String ATTR_HTTP_METHOD = "httpMethod";

    // HTTP服务地址
    private static final String ATTR_SERVICE_PATH = "servicePath";

    // 媒体内容类型配置名称
    private static final String ATTR_CONTENT_TYPE = "contentType";

    /**
     * HTTP方法
     */
    private HttpMethod httpMethod;

    /**
     * 服务名称
     */
    private String servicePath;

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

        ConfigUtils.getIfPresent(configJson, ATTR_HTTP_METHOD, (v) -> {
            this.httpMethod = HttpMethod.valueOf(((String) v).toUpperCase());
        });
        this.servicePath = ConfigUtils.getString(configJson, ATTR_SERVICE_PATH);
        ConfigUtils.getIfPresent(configJson, ATTR_CONTENT_TYPE, (v) -> {
            this.contentType = RequestContentTypeEnum.valueOf(((String) v).toUpperCase());
        });
        ConfigUtils.getIfPresent(configJson, XValueTypeConfig.ATTR, (v) -> {
            this.valueType = ValueTypeConfigFactory.newInstance((JsonObject) v);
        });
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setServicePath(String servicePath) {
        this.servicePath = servicePath;
    }

    public RequestContentTypeEnum getContentType() {
        return contentType;
    }

    public void setContentType(RequestContentTypeEnum contentType) {
        this.contentType = contentType;
    }

    @Override
    public JsonObject<String, Object> toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_HTTP_METHOD, this.httpMethod);
        ConfigUtils.put(configJson, ATTR_SERVICE_PATH, this.servicePath);
        ConfigUtils.putIfNotNull(configJson, ATTR_CONTENT_TYPE, this.contentType);
        ConfigUtils.putJsonIfNotNull(configJson, XValueTypeConfig.ATTR, this.valueType);

        return configJson;
    }

}
