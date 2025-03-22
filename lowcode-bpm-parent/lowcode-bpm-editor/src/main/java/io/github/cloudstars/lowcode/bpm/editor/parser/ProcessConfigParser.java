package io.github.cloudstars.lowcode.bpm.editor.parser;

import io.github.cloudstars.lowcode.bpm.editor.config.ProcessConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.commons.editor.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 流程配置解析器
 *
 * @author clouds
 */
public class ProcessConfigParser implements XConfigParser<ProcessConfig> {

    @Override
    public ProcessConfig fromJson(JsonObject configJson) {
        ProcessConfig processConfig = new ProcessConfig();

        // 解析流程基本信息
        processConfig.setKey((String) configJson.get("key"));
        processConfig.setCode((String) configJson.get("code"));
        processConfig.setName((String) configJson.get("name"));

        // 解析主分支
        JsonObject mainBranchJson = (JsonObject) configJson.get("mainBranch");
        BranchNodeConfigParser branchNodeConfigParser = new BranchNodeConfigParser();
        BranchNodeConfig mainBranch = branchNodeConfigParser.fromJson(mainBranchJson);
        processConfig.setMainBranch(mainBranch);

        return processConfig;
    }

}
