package io.github.cloudstars.lowcode.api.executor;

/**
 * API执行结果
 *
 * @author clouds
 */
public class ApiResult {

    /**
     * 是否执行成功的标识
     */
    private boolean success;

    /**
     * 执行的错误消息
     */
    private String errorMsg;

    /**
     * 执行的结果
     */
    private Object body;

    public boolean isSuccess() {
        return success;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * 设置执行的正确结果
     *
     * @param result 结果内容
     */
    public void success(String result) {
        this.success = true;
        this.body = result;
    }

    /**
     * 设置执行结果错误消息
     *
     * @param errorMsg 错误消息
     */
    public void error(String errorMsg) {
        this.success = false;
        this.errorMsg = errorMsg;
    }

}
