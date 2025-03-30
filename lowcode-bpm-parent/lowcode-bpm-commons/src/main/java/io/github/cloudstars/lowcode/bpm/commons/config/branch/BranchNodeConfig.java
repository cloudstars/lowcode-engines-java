package io.github.cloudstars.lowcode.bpm.commons.config.branch;

import io.github.cloudstars.lowcode.bpm.commons.config.*;
import io.github.cloudstars.lowcode.bpm.commons.parser.NodeConfigParser;
import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 分支节点配置
 *
 * @author clouds
 */
@NodeConfigClass(name = "BRANCH")
public class BranchNodeConfig extends AbstractNodeConfig {

    /**
     * 分支下面的节点列表
     */
    private List<NodeConfig> nodes;

    public BranchNodeConfig() {
    }

    public BranchNodeConfig(JsonObject configJson) {
        super(configJson);

        Object nodesValue = configJson.get("nodes");
        if (nodesValue == null || !(nodesValue instanceof JsonArray)) {
            throw new RuntimeException("节点信息nodes不存在或不是数组格式，请检查：" + configJson);
        }

        List<NodeConfig> nodeConfigs = new ArrayList<>();
        this.setNodes(nodeConfigs);

        JsonArray nodes = (JsonArray) nodesValue;

        NodeConfigParser nodeConfigParser = new NodeConfigParser();
        nodes.forEach((node) -> {
            JsonObject nodeConfigJson = (JsonObject) node;
            NodeConfig nodeConfig = NodeConfigFactory.newInstance(nodeConfigJson);
            if (nodeConfig != null) {
                nodeConfigs.add(nodeConfig);
            }
        });
    }

    public List<NodeConfig> getNodes() {
        return nodes;
    }

    public void setNodes(List<NodeConfig> nodes) {
        this.nodes = nodes;
    }


    @Override
    protected void accept0(BpmNodeVisitor visitor) {
        if (this.nodes != null) {
            // 循环访问每一个子节点
            this.nodes.forEach((node) -> {
                node.accept(visitor);
            });
        }
    }

    @Override
    protected NodeTypeEnum getType() {
        return NodeTypeEnum.BRANCH;
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = super.toJson();

        // 添加节点配置
        JsonArray nodeJsonArray = new JsonArray();
        this.nodes.forEach(node -> {
            nodeJsonArray.add(node.toJson());
        });
        jsonObject.put("nodes", nodeJsonArray);

        return jsonObject;
    }

}
