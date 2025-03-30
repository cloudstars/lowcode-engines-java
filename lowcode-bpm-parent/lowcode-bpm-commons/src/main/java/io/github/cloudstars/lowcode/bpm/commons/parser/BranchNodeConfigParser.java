package io.github.cloudstars.lowcode.bpm.commons.parser;

import io.github.cloudstars.lowcode.bpm.commons.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.NodeConfig;
import io.github.cloudstars.lowcode.bpm.commons.config.branch.BranchNodeConfig;
import io.github.cloudstars.lowcode.commons.lang.config.XConfigParser;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 分支节点配置解析器
 *
 * @author clouds
 */
@Deprecated
public class BranchNodeConfigParser implements XConfigParser<BranchNodeConfig> {

    @Override
    public BranchNodeConfig fromJson(JsonObject configJson) {
        BranchNodeConfig branchConfig = new BranchNodeConfig();
        Object nodesValue = configJson.get("nodes");
        if (nodesValue == null || !(nodesValue instanceof JsonArray)) {
            throw new RuntimeException("节点信息nodes不存在或不是数组格式，请检查：" + configJson);
        }

        List<NodeConfig> nodeConfigs = new ArrayList<>();
        branchConfig.setNodes(nodeConfigs);

        JsonArray nodes = (JsonArray) nodesValue;

        NodeConfigParser nodeConfigParser = new NodeConfigParser();
        nodes.forEach((node) -> {
            JsonObject nodeConfigJson = (JsonObject) node;
            AbstractNodeConfig nodeConfig = nodeConfigParser.fromJson(nodeConfigJson);
            if (nodeConfig != null) {
                nodeConfigs.add(nodeConfig);
            }
        });

        return branchConfig;
    }

}
