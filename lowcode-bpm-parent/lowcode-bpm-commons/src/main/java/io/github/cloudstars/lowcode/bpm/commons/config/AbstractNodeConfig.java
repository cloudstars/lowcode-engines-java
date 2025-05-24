package io.github.cloudstars.lowcode.bpm.commons.config;

import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

import java.util.List;

/**
 * 抽象流程节点定义
 *
 * @author clouds
 */
public abstract class AbstractNodeConfig extends AbstractConfig implements NodeConfig {

    protected static final String ATTR_CONDITION = "condition";
    protected static final String ATTR_BRANCH = "branch";

    /**
     * 类型
     */
    private String type;

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
    private AbstractNodeConfig prevNode;

    /**
     * 后一个节点
     */
    private AbstractNodeConfig nextNode;

    public AbstractNodeConfig() {
    }

    public AbstractNodeConfig(JsonObject configJson) {
        super(configJson);

        this.setType((String) configJson.get("type"));
        this.setKey((String) configJson.get("key"));
        this.setName((String) configJson.get("name"));
    }


    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public AbstractNodeConfig getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(AbstractNodeConfig prevNode) {
        this.prevNode = prevNode;
    }

    public AbstractNodeConfig getNextNode() {
        return nextNode;
    }

    public void setNextNode(AbstractNodeConfig nextNode) {
        this.nextNode = nextNode;
    }

    /**
     * 列表接受一个Visitor
     *
     * @param visitor
     * @param nodes
     */
    protected void visitList(BpmNodeVisitor visitor, List<AbstractNodeConfig> nodes) {
        for (AbstractNodeConfig node : nodes) {
            node.accept(visitor);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("nodeType", this.getNodeType().name());
        jsonObject.put("type", this.type);
        jsonObject.put("key", this.key);
        jsonObject.put("name", this.name);

        return jsonObject;
    }

}
