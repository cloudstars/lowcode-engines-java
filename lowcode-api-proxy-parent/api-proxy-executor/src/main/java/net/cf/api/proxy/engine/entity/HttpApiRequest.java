package net.cf.api.proxy.engine.entity;

import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 23:21
 */
public class HttpApiRequest {

    private final HttpMethod method;
    private final String url;
    private final Map<String, Object> queries;
    private final Map<String, Object> headers;
    private final Object body;

    public HttpApiRequest(HttpMethod method, String url, Map<String, Object> queries, Map<String, Object> headers, Object body) {
        this.method = method;
        this.url = url;
        this.queries = queries;
        this.headers = headers;
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Object getBody() {
        return body;
    }

    public Map<String, Object> getQueries() {
        return queries;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public static HttpApiRequestBuilder builder() {
        return new HttpApiRequestBuilder();
    }

    public static class HttpApiRequestBuilder {
        private HttpMethod method;
        private String url;
        private Map<String, Object> queries;
        private Map<String, Object> headers;
        private Object body;
        HttpApiRequestBuilder() {}

        public HttpApiRequestBuilder method(HttpMethod httpMethod) {
            this.method = httpMethod;
            return this;
        }
        public HttpApiRequestBuilder url(String url) {
            this.url = url;
            return this;
        }
        public HttpApiRequestBuilder queries(Map<String, Object> queries) {
            this.queries = queries;
            return this;
        }
        public HttpApiRequestBuilder  headers(Map<String, Object> httpHeaders) {
            this.headers = httpHeaders;
            return this;
        }
        public HttpApiRequestBuilder body(Object body) {
            this.body = body;
            return this;
        }
        public HttpApiRequest build() {
            if (Objects.isNull(this.headers)) {
                this.headers = new HashMap<>(8);
            }
            if (Objects.isNull(this.queries)) {
                this.queries = new HashMap<>(8);
            }
            HttpApiRequest httpApiRequest = new HttpApiRequest(this.method, this.url, this.queries, this.headers, this.body);
            return httpApiRequest;
        }
    }

}
