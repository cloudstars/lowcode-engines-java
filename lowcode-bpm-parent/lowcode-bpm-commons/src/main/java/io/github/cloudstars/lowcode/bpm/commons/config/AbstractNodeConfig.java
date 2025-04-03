package io.github.cloudstars.lowcode.bpm.commons.config;

import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 抽象流程节点定义
 *
 * @author clouds
 */
public abstract class AbstractNodeConfig extends AbstractConfig implements NodeConfig {

    /**
     * 子类型
     */
    private String subType;

    /**
     * 节点编号（自动生成）
     */
    private String key;

    /**
     * 节点名称
     */
    private String name;

    /**
     * 前一个节点
     */
    private NodeConfig prevNode;

    /**
     * 后一个节点
     */
    private NodeConfig nextNode;

    public AbstractNodeConfig() {
    }

    public AbstractNodeConfig(JsonObject configJson) {
        super(configJson);

        this.setSubType((String) configJson.get("subType"));
        this.setKey((String) configJson.get("key"));
        this.setName((String) configJson.get("name"));
    }


    @Override
    public String getType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeConfig getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(NodeConfig prevNode) {
        this.prevNode = prevNode;
    }

    public NodeConfig getNextNode() {
        return nextNode;
    }

    public void setNextNode(NodeConfig nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (visitor.visit(this)) {
            visitor.endVisit(this);
        }
    }

    /**
     * 列表接受一个Visitor
     *
     * @param visitor
     * @param nodes
     */
    protected void listAccept(List<AbstractNodeConfig> nodes, BpmNodeVisitor visitor) {
        for (AbstractNodeConfig node : nodes) {
            node.accept(visitor);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type", this.getNodeType().name());
        jsonObject.put("subType", this.subType);
        jsonObject.put("key", this.key);
        jsonObject.put("name", this.name);

        return jsonObject;
    }

}
