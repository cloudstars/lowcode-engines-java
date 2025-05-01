package io.github.cloudstars.lowcode.api.executor.invoke;

import io.github.cloudstars.lowcode.commons.api.config.response.ResponseContentTypeEnum;

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

    private ResponseContentTypeEnum contentType;

    private String body;

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ResponseContentTypeEnum getContentType() {
        return contentType;
    }

    public void setContentType(ResponseContentTypeEnum contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
