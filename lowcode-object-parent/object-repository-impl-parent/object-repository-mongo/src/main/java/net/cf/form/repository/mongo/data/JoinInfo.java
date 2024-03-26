package net.cf.form.repository.mongo.data;

import java.util.HashMap;
import java.util.Map;

public class JoinInfo {

    /**
     * join 条件参数替换
     */
    private Map<String, String> conditionReplaceMap;

    public Map<String, String> getConditionReplaceMap() {
        return conditionReplaceMap;
    }

    public void addConditionReplace(String key, String value) {
        if (this.conditionReplaceMap == null) {
            this.conditionReplaceMap = new HashMap<>();
        }
        this.conditionReplaceMap.put(key, value);
    }

    public void setConditionReplaceMap(Map<String, String> conditionReplaceMap) {
        this.conditionReplaceMap = conditionReplaceMap;
    }
}
