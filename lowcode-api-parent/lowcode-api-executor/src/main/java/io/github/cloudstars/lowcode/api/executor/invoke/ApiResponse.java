package io.github.cloudstars.lowcode.api.executor.invoke;

import java.util.List;

/**
 * API响应
 *
 * @author clouds
 */
public class ApiResponse {

    /**
     * 响应状态
     */
    private int httpStatus;

    /**
     * 请求头部
     */
    private List<HttpHeader> headers;

    private String body;

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<HttpHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HttpHeader> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
