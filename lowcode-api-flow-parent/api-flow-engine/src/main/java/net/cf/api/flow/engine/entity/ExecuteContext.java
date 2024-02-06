package net.cf.api.flow.engine.entity;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @Description:
 * @Author: Wu Yuefeng
 * @Date: Created on 2024/2/6
 */
public class ExecuteContext {

    private JSONObject config;

    private Map<String, Object> requestData;

    private Map<String, Object> responseData;

    public JSONObject getConfig() {
        return config;
    }

    public Map<String, Object> getRequestData() {
        return requestData;
    }

    public Map<String, Object> getResponseData() {
        return responseData;
    }

    public void setResponseData(Map<String, Object> responseData) {
        this.responseData = responseData;
    }

    public void setConfig(JSONObject config) {
        this.config = config;
    }

    public void setRequestData(Map<String, Object> requestData) {
        this.requestData = requestData;
    }
}
