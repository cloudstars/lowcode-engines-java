package net.cf.api.proxy.engine.entity;

import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author 80345746
 * @version v1.0
 * @date 2024/1/17 23:33
 */
public class HttpApiResponse {

    private Integer code;
    private Object body;
    private Map<String, Object> headers;

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
