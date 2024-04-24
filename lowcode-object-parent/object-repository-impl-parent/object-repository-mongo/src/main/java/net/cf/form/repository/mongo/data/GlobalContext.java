package net.cf.form.repository.mongo.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 传入解析的全局上下文
 */
public class GlobalContext {

    // 变量
    private Map<String, Object> dataMap;

    // 是否启用变量
    private boolean enableVariable = false;


    // join内容
    private JoinInfo joinInfo;

    // mongo操作，默认为查询
    private MongoMode mongoMode = MongoMode.QUERY;

    // expr所处位置
    private PositionEnum positionEnum;

    private Map<String, String> existAliasMap = new HashMap<>();


    public GlobalContext(PositionEnum positionEnum) {
        this.positionEnum = positionEnum;
    }

    public GlobalContext(Map<String, Object> dataMap, PositionEnum positionEnum) {
        setDataMap(dataMap);
        this.positionEnum = positionEnum;
    }

    public static GlobalContext getDefault() {
        return new GlobalContext(PositionEnum.DEFAULT);
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

    public MongoMode getMongoMode() {
        return mongoMode;
    }

    public void setMongoMode(MongoMode mongoMode) {
        this.mongoMode = mongoMode;
    }

    public PositionEnum getPositionEnum() {
        return positionEnum;
    }

    public void setPositionEnum(PositionEnum positionEnum) {
        this.positionEnum = positionEnum;
    }

    public Map<String, String> getExistAliasMap() {
        return existAliasMap;
    }

    public void setExistAliasMap(Map<String, String> existAliasMap) {
        this.existAliasMap = existAliasMap;
    }
}
