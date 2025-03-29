package io.github.cloudstars.lowcode.bpm.commons.parser;

import io.github.cloudstars.lowcode.bpm.commons.config.end.EndNodeConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

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
