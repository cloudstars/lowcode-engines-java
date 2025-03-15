
package io.github.cloudstars.lowcode.bpm.editor.config;

import io.github.cloudstars.lowcode.commons.editor.XConfig;
import io.github.cloudstars.lowcode.commons.utils.json.JsonObject;

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
