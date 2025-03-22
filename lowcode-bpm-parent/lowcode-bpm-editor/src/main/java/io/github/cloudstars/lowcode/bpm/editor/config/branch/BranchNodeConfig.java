package io.github.cloudstars.lowcode.bpm.editor.config.branch;

import io.github.cloudstars.lowcode.bpm.editor.config.AbstractNodeConfig;
import io.github.cloudstars.lowcode.bpm.editor.config.NodeType;
import io.github.cloudstars.lowcode.bpm.editor.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.json.JsonArray;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 分支节点配置
 *
 * @author clouds
 */
public class BranchNodeConfig extends AbstractNodeConfig {

    /**
     * 分支所以层级（从0开始）
     */
    private int level = 0;

    /**
     * 分支下面的节点
     */
    private List<AbstractNodeConfig> nodes;

    public List<AbstractNodeConfig> getNodes() {
        return nodes;
    }

    public void setNodes(List<AbstractNodeConfig> nodes) {
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
    protected NodeType getType() {
        return NodeType.BRANCH;
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
