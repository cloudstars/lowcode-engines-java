package net.cf.form.engine.def.validate;

/**
 * 模型校验规划
 *
 * @author clouds
 */
public class ExpressionValidateRule {

    /**
     * 校验规则
     */
    private String expression;

    /**
     * 错误消息
     */
    private String errorMsg;

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
