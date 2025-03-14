package io.github.cloudstars.lowcode.bpm.editor.config;

import io.github.cloudstars.lowcode.commons.editor.XConfig;

/**
 * 流程定义
 *
 * @author clouds
 */
public class ProcessConfig implements XConfig {

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
     * 主分支配置
     */
    private AbstractBranchNodeConfig mainBranch;

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

    public AbstractBranchNodeConfig getMainBranch() {
        return mainBranch;
    }

    public void setMainBranch(AbstractBranchNodeConfig mainBranch) {
        this.mainBranch = mainBranch;
    }

    @Override
    public String toJsonString() {
        return null;
    }

}
