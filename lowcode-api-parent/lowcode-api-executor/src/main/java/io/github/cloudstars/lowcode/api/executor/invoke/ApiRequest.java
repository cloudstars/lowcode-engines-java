package io.github.cloudstars.lowcode.api.executor.invoke;

import io.github.cloudstars.lowcode.commons.api.config.HttpMethod;

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
     * 请求参数
     */
    private Object body;

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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
