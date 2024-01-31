package net.cf.form.engine.object;

/**
 * 数据校验结果
 *
 * @author clouds
 */
public class ValidationResult {

    private boolean success;

    private String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
