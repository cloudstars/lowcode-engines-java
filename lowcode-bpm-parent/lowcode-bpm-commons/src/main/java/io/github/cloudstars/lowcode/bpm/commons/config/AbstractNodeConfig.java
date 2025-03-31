package io.github.cloudstars.lowcode.bpm.commons.config;

import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.config.AbstractConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

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

    public AbstractNodeConfig() {
    }

    public AbstractNodeConfig(JsonObject configJson) {
        super(configJson);

        this.setSubType((String) configJson.get("subType"));
        this.setKey((String) configJson.get("key"));
        this.setName((String) configJson.get("name"));
    }


    @Override
    public String getSubType() {
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

    /**
     * 接受一个访问器的访问
     *
     * @param visitor BPM节点访问器
     */
    @Override
    public void accept(BpmNodeVisitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException("visitor is null.");
        } else {
            this.accept0(visitor);
        }
    }

    /**
     * 接受一个visitor
     *
     * @param visitor BPM节点访问器
     */
    protected abstract void accept0(BpmNodeVisitor visitor);

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("type", this.getType().name());
        jsonObject.put("subType", this.getSubType());
        jsonObject.put("key", this.key);
        jsonObject.put("name", this.name);

        return jsonObject;
    }

}
