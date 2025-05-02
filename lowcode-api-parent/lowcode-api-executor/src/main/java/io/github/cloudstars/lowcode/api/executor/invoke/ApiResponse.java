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
    private int status;

    /**
     * 请求头部
     */
    private List<ApiHttpHeader> headers;

    private String body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ApiHttpHeader> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ApiHttpHeader> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
