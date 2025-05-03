package io.github.cloudstars.lowcode.api.executor.invoke;

import java.util.List;

/**
 * API Get请求
 *
 * @author clouds
 */
public class ApiGetRequest extends AbstractApiRequest {

    /**
     * 请求地址
     */
    private String url;

    /**
     * 请求头部
     */
    private List<ApiHttpHeader> headers;

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

}
