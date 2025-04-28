package io.github.cloudstars.lowcode.api.executor;

/**
 * API执行结果
 *
 * @author clouds
 */
public class ApiResult {

    private boolean success;

    private Object body;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
