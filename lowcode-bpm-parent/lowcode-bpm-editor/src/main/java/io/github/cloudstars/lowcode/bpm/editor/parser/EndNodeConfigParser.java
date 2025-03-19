package io.github.cloudstars.lowcode.bpm.editor.parser;

import io.github.cloudstars.lowcode.bpm.editor.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

/**
 * 结束节点配置解析器
 *
 * @author clouds
 */
public class EndNodeConfigParser extends AbstractNodeConfigParser<EndNodeConfig> {

    @Override
    public EndNodeConfig fromJson(JsonObject nodeConfigJson) {
        EndNodeConfig nodeConfig = new EndNodeConfig();
        this.initCommonNode(nodeConfig, nodeConfigJson);
        return nodeConfig;
    }

}
