package net.cf.api.engine.util.valid;

import java.util.ArrayList;
import java.util.List;

/**
 * 校验结果
 * @author 80345746
 * @version v1.0
 * @date 2024/1/23 17:37
 */
public class ValidResult {
    private final List<String> errorMsgList = new ArrayList<>();
    private Boolean passed = Boolean.TRUE;

    public boolean isFail() {
        return Boolean.FALSE.equals(passed);
    }

    public void addErrorMsg(String msg) {
        errorMsgList.add(msg);
    }

    public List<String> getErrorMsgList() {
        return errorMsgList;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }
}
