package io.github.cloudstars.lowcode.commons.api.config;

import io.github.cloudstars.lowcode.commons.api.config.request.ApiRequestConfig;
import io.github.cloudstars.lowcode.commons.api.config.response.ApiResponseConfig;
import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.config.ConfigUtils;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * API配置
 *
 * @author clouds
 */
public class ApiConfig extends AbstractConfig {

    // HTTP方法配置名称
    private static final String ATTR_HTTP_METHOD = "httpMethod";

    // HTTP服务地址
    private static final String ATTR_SERVICE_PATH = "servicePath";

    // 请求配置名称
    private static final String ATTR_REQUEST = "request";

    // 响应配置名称
    private static final String ATTR_RESPONSE = "response";

    /**
     * HTTP方法
     */
    private HttpMethod httpMethod;

    /**
     * 服务名称
     */
    private String servicePath;

    /**
     * 请求配置
     */
    private ApiRequestConfig request;

    /**
     * 响应配置
     */
    private ApiResponseConfig response;

    public ApiConfig() {
    }

    public ApiConfig(JsonObject configJson) {
        super(configJson);

        String httpMethodValue = (String) configJson.get(ATTR_HTTP_METHOD);
        if (httpMethodValue != null) {
            this.httpMethod = HttpMethod.valueOf(httpMethodValue.toUpperCase());
        }
        this.servicePath = (String) configJson.get(ATTR_SERVICE_PATH);
        JsonObject requestConfigJson = (JsonObject) configJson.get(ATTR_REQUEST);
        this.setRequest(new ApiRequestConfig(requestConfigJson));
        JsonObject responseConfigJson = (JsonObject) configJson.get(ATTR_RESPONSE);
        this.setResponse(new ApiResponseConfig(responseConfigJson));
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

    public ApiRequestConfig getRequest() {
        return request;
    }

    public void setRequest(ApiRequestConfig request) {
        this.request = request;
    }

    public ApiResponseConfig getResponse() {
        return response;
    }

    public void setResponse(ApiResponseConfig response) {
        this.response = response;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();
        ConfigUtils.putIfNotNull(configJson, ATTR_HTTP_METHOD, this.httpMethod);
        ConfigUtils.put(configJson, ATTR_SERVICE_PATH, this.servicePath);
        ConfigUtils.putJsonIfNotNull(configJson, ATTR_REQUEST, request);
        ConfigUtils.putJsonIfNotNull(configJson, ATTR_RESPONSE, response);

        return configJson;
    }

}
