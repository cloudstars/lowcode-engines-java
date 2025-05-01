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

    // 请求配置名称
    private static final String ATTR_REQUEST = "request";

    // 响应配置名称
    private static final String ATTR_RESPONSE = "response";

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

        JsonObject requestConfigJson = (JsonObject) configJson.get(ATTR_REQUEST);
        this.setRequest(new ApiRequestConfig(requestConfigJson));
        JsonObject responseConfigJson = (JsonObject) configJson.get(ATTR_RESPONSE);
        this.setResponse(new ApiResponseConfig(responseConfigJson));
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
        ConfigUtils.putJson(configJson, ATTR_REQUEST, request);
        ConfigUtils.putJson(configJson, ATTR_RESPONSE, response);

        return configJson;
    }

}
