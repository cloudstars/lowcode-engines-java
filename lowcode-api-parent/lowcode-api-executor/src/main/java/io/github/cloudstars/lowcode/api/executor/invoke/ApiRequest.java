package io.github.cloudstars.lowcode.api.executor.invoke;

import io.github.cloudstars.lowcode.commons.api.config.request.HttpMethod;

import java.util.List;
import java.util.Map;

/**
 * API请求
 *
 * @author clouds
 */
public class ApiRequest {

    /**
     * 请求方法
     */
    private HttpMethod method;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求头部
     */
    private List<ApiHttpHeader> headers;

    /**
     * 查询参数
     */
    private Map<String, Object> queryParams;

    /**
     * 请求参数
     */
    private Object body;

    /**
     * 请求的表单项
     */
    private Map<String, List<Object>> formItems;

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ApiHttpHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ApiHttpHeader> headers) {
        this.headers = headers;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<String, List<Object>> getFormItems() {
        return formItems;
    }

    public void setFormItems(Map<String, List<Object>> formItems) {
        this.formItems = formItems;
    }
}
