package io.github.cloudstars.lowcode.api.executor.invoke;

import java.util.List;
import java.util.Map;

/**
 * 抽象的API请求
 *
 * @author clouds
 */
public abstract class AbstractApiRequest {

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
}
