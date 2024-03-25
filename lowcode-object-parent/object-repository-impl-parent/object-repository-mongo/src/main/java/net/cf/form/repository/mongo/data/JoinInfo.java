package net.cf.form.repository.mongo.data;

import java.util.Map;

public class JoinInfo {

    /**
     * join 条件参数替换
     */
    private Map<String, String> conditionReplaceMap;

    public Map<String, String> getConditionReplaceMap() {
        return conditionReplaceMap;
    }

    public void setConditionReplaceMap(Map<String, String> conditionReplaceMap) {
        this.conditionReplaceMap = conditionReplaceMap;
    }
}
