package io.github.cloudstars.lowcode.bpm.commons.config;

import io.github.cloudstars.lowcode.bpm.commons.visitor.BpmNodeVisitor;
import io.github.cloudstars.lowcode.commons.lang.config.XConfig;
import io.github.cloudstars.lowcode.commons.lang.json.JsonObject;

/**
 * 抽象流程节点定义
 *
 * @author clouds
 */
public abstract class AbstractNodeConfig implements XConfig {

    /**
     * 节点编号（自动生成）
     */
    private String key;

    /**
     * 节点名称
     */
    private String name;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    /**
     * 获取节点的类型
     *
     * @return
     */
    protected abstract NodeType getType();

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", this.key);
        jsonObject.put("name", this.name);
        jsonObject.put("type", this.getType());

        return jsonObject;
    }

}
