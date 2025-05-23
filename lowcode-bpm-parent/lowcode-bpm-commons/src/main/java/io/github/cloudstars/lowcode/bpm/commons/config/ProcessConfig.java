package io.github.cloudstars.lowcode.bpm.commons.config;

import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 流程定义
 *
 * @author clouds
 */
public class ProcessConfig extends AbstractConfig {

    /**
     * 流程编号（自动生成）
     */
    private String key;

    /**
     * 流程代码（用户输入）
     */
    private String code;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 主分支
     */
    private BranchNodeConfig mainBranch;

    public ProcessConfig() {
    }

    public ProcessConfig(JsonObject configJson) {
        super(configJson);

        // 解析流程基本信息
        this.setKey((String) configJson.get("key"));
        this.setCode((String) configJson.get("code"));
        this.setName((String) configJson.get("name"));

        // 解析主分支
        JsonObject mainBranchJson = (JsonObject) configJson.get("mainBranch");
        BranchNodeConfig mainBranch = new BranchNodeConfig(mainBranchJson);
        this.setMainBranch(mainBranch);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BranchNodeConfig getMainBranch() {
        return mainBranch;
    }

    public void setMainBranch(BranchNodeConfig mainBranch) {
        this.mainBranch = mainBranch;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", this.key);
        jsonObject.put("code", this.code);
        jsonObject.put("name", this.name);
        jsonObject.put("mainBranch", this.mainBranch.toJson());

        return jsonObject;
    }

}
