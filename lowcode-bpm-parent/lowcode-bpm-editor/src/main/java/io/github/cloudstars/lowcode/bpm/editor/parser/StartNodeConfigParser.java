package io.github.cloudstars.lowcode.bpm.editor.parser;

import io.github.cloudstars.lowcode.bpm.editor.config.start.StartNodeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 开始节点配置解析器
 *
 * @author clouds
 */
public class StartNodeConfigParser extends AbstractNodeConfigParser<StartNodeConfig> {

    @Override
    public StartNodeConfig fromJson(JsonObject nodeConfigJson) {
        StartNodeConfig nodeConfig = new StartNodeConfig();
        this.initCommonNode(nodeConfig, nodeConfigJson);
        return nodeConfig;
    }

}
