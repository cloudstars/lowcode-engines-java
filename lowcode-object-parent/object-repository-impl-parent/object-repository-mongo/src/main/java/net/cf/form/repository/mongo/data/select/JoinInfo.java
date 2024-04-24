package net.cf.form.repository.mongo.data.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinInfo {

    /**
     * 表名
     */
    private List<String> tableNames;
    /**
     * 主表 字段替换
     */
    private Map<String, String> mainReplaceMap;
    /**
     * 关联表 字段替换
     */
    private Map<String, String> slaveReplaceMap;

    public Map<String, String> getSlaveReplaceMap() {
        return slaveReplaceMap;
    }

    public void addSlaveReplace(String key, String value) {
        if (this.slaveReplaceMap == null) {
            this.slaveReplaceMap = new HashMap<>();
        }
        this.slaveReplaceMap.put(key, value);
    }

    public void addMainReplace(String key, String value) {
        if (this.mainReplaceMap == null) {
            this.mainReplaceMap = new HashMap<>();
        }
        this.mainReplaceMap.put(key, value);
    }

    public void addTableNames(String value) {
        if (this.tableNames == null) {
            this.tableNames = new ArrayList<>();
        }
        this.tableNames.add(value);
    }

    public Map<String, String> getMainReplaceMap() {
        return mainReplaceMap;
    }

    public List<String> getTableNames() {
        return tableNames;
    }
}
