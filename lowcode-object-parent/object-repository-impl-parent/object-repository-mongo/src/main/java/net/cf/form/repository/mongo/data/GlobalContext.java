package net.cf.form.repository.mongo.data;

import java.util.Map;

/**
 * 传入解析的全局上下文
 */
public class GlobalContext {

    private Map<String, Object> dataMap;

    private boolean enableVariable = false;

    private JoinInfo joinInfo;

    public GlobalContext() {
    }

    public GlobalContext(Map<String, Object> dataMap) {
        setDataMap(dataMap);
    }

    public static GlobalContext getDefault() {
        return new GlobalContext();
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        if (MongoUtils.isVariableEnable(dataMap)) {
            this.dataMap = dataMap;
            this.enableVariable = true;
        }

    }

    public boolean isEnableVariable() {
        return enableVariable;
    }

    public void setEnableVariable(boolean enableVariable) {
        this.enableVariable = enableVariable;
    }


    public JoinInfo getJoinInfo() {
        return joinInfo;
    }

    public void setJoinInfo(JoinInfo joinInfo) {
        this.joinInfo = joinInfo;
    }

}
