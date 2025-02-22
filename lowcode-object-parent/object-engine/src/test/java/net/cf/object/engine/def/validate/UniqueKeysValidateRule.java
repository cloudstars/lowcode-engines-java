package net.cf.object.engine.def.validate;

/**
 * 模型唯一性校验规则
 *
 * @author clouds
 */
public class UniqueKeysValidateRule {

    /**
     * 唯一键的第一个字段
     */
    private String firstKey;

    /**
     * 唯一键的第二个字段
     */
    private String secondKey;

    /**
     * 唯一键的第三个字段
     */
    private String thirdKey;

    private String errorMsg;

    public String getFirstKey() {
        return firstKey;
    }

    public void setFirstKey(String firstKey) {
        this.firstKey = firstKey;
    }

    public String getSecondKey() {
        return secondKey;
    }

    public void setSecondKey(String secondKey) {
        this.secondKey = secondKey;
    }

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
