package io.github.cloudstars.lowcode.bpm.commons.config.branch;

import io.github.cloudstars.lowcode.bpm.commons.config.*;
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
@NodeConfigClass(type = "DEFAULT.BRANCH")
public class BranchNodeConfig extends AbstractNodeConfig {

    /**
     * 分支下面的节点列表
     */
    private List<AbstractNodeConfig> nodes;

    public BranchNodeConfig() {
    }

    public BranchNodeConfig(JsonObject configJson) {
        super(configJson);

        Object nodesValue = configJson.get("nodes");
        if (nodesValue == null || !(nodesValue instanceof JsonArray)) {
            throw new RuntimeException("节点信息nodes不存在或不是数组格式，请检查：" + configJson);
        }

        List<AbstractNodeConfig> nodeConfigs = new ArrayList<>();
        JsonArray nodes = (JsonArray) nodesValue;
        nodes.forEach((node) -> {
            JsonObject nodeConfigJson = (JsonObject) node;
            AbstractNodeConfig nodeConfig = NodeConfigFactory.newInstance(nodeConfigJson);
            if (nodeConfig != null) {
                nodeConfigs.add(nodeConfig);
            }
        });
        this.setNodes(nodeConfigs);
    }

    public List<AbstractNodeConfig> getNodes() {
        return nodes;
    }

    public void setNodes(List<AbstractNodeConfig> nodes) {
        this.nodes = nodes;

        // 建立前后节点关系
        int nodeSize = nodes.size();
        if (nodeSize > 0) {
            // 第一个节点的前一个节点和最后一个节点的下一个节点都是当前分支节点
            AbstractNodeConfig firstNode = nodes.get(0);
            firstNode.setPrevNode(this);
            AbstractNodeConfig lastNode = nodes.get(nodeSize - 1);
            lastNode.setNextNode(this);

            // 每一个节点的前后端按位置顺序建立前后关联
            AbstractNodeConfig prev = firstNode;
            for (int i = 1; i < nodeSize; i++) {
                AbstractNodeConfig anode = nodes.get(i);
                prev.setNextNode(anode);
                anode.setPrevNode(prev);
                prev = anode;
            }
        }
    }


    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (this.nodes != null) {
            // 循环访问每一个子节点
            this.visitList(visitor, this.nodes);
        }
    }

    @Override
    public NodeTypeEnum getNodeType() {
        return NodeTypeEnum.BRANCH;
    }

    @Override
    public JsonObject toJson() {
        JsonObject configJson = super.toJson();

        // 添加节点配置
        JsonArray nodeJsonArray = new JsonArray();
        this.nodes.forEach(node -> {
            nodeJsonArray.add(node.toJson());
        });
        configJson.put("nodes", nodeJsonArray);

        return configJson;
    }

}
